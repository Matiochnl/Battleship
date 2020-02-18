import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ocean {
    private Square[][] map;
    private Map<String, Ship> ships;
    private boolean isGameOver;

    public Ocean() {
        this.map = new Square[10][10];
        this.ships = new HashMap<String, Ship>();
        this.isGameOver = false;
        this.createSquareMap();
    }

    public void createSquareMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.map[i][j] = new Square();
            }
        }
    }

    public Square getSquare(int x, int y) {
        return this.map[x][y];
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void setIsGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    private boolean checkIfAllShipsSunk() {
        for (Map.Entry<String, Ship> entry : this.ships.entrySet()) {
            if (!entry.getValue().isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void handleShoot(Coordinates coordinates) {
        Square square = getSquare(coordinates.getX(), coordinates.getY());
        if (square.getShipName().equals("ocean")) {
            square.setOpponentStatus(".");
        } else {
            this.ships.get(square.getShipName()).markHit(square);
            if (checkIfAllShipsSunk()) {
                this.isGameOver = true;
            }
        }
    }

    public void addShip(String shipName, Ship ship) {
        this.ships.put(shipName, ship);
    }

    public void changeGameStatus() {
        if (checkIfAllShipsSunk()) {
            setIsGameOver(true);
        }
    }

    public boolean testShipPlacement(Coordinates coordinates, int shipLength, String direction) {
        if (checkIfShipInMap(coordinates, shipLength, direction)) {
            final int EXTRA_SQUARES = 2;
            int totalLinesToCheck = getLinesToCheck(direction, shipLength, EXTRA_SQUARES);
            int squaresInLineToCheck = getSquaresToCheck(direction, shipLength, EXTRA_SQUARES);
            int y = coordinates.getX() - 1;
            int x = coordinates.getY() - 1;
            for (int startY = 0; startY < totalLinesToCheck; startY++) {
                for (int startX = 0; startX < squaresInLineToCheck; startX++) {
                    if (0 <= y && y <10 && 0 <= x && x < 10) {
                        if (!(getSquare(y, x).getShipName().equals("ocean"))) {
                            // or StartY, StartX
                            return false;
                        }
                    }
                    x++;
                }
                x = coordinates.getY() - 1;
                y++;
            }
            return true;
        }
        return false;
    }

    private int getLinesToCheck(String direction, int shipLength, int extraSquares) {
        final int HORIZONTAL_LINES_TO_CHECK = 3;
        if (direction.equalsIgnoreCase("h")) {
            return HORIZONTAL_LINES_TO_CHECK;
        } else {
            return shipLength + extraSquares;
        }
    }

    private int getSquaresToCheck(String direction, int shipLength, int extraSquares) {
        final int VERTICAL_SQUARES_TO_CHECK = 3;
        if (direction.equalsIgnoreCase("h")) {
            return shipLength + extraSquares;
        } else {
            return VERTICAL_SQUARES_TO_CHECK;
        }
    }

    private boolean checkIfShipInMap(Coordinates coordinates, int length, String direction) {
        final int BORDER = 10;
        if (direction.equalsIgnoreCase("h")) {
            return (coordinates.getY() - 1 + length) < BORDER;
        } else if (direction.equalsIgnoreCase("v")) {
            return (coordinates.getX() - 1 + length) < BORDER;
        } else {
            return false;
        }
    }

    public String mapToString(String view) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] alphabet  = "ABCDEFGHIJ".toCharArray();

        if (view.equals("ship")) {
            stringBuilder.append(" ".repeat(16) + "YOUR SHIPS" + " ".repeat(16) + "\n");
        } else {
            stringBuilder.append(" ".repeat(15) + "YOUR SHOOTS" + " ".repeat(15) + "\n");
        }

        for (int i = 1; i < 11; i++) {
            stringBuilder.append(" | " + i);
        }
        stringBuilder.append("|\n");

        int i = 0;

        for (Square[] row : this.map) {
            stringBuilder.append("-".repeat(42) + "\n");
            stringBuilder.append(alphabet[i]);
            i++;
            for (Square column : row) {
                if (view.equals("ship")) {

                    stringBuilder.append("| " + column.getMyStatus() + " ");
                } else {
                    stringBuilder.append("| " + column.getOpponentStatus() + " ");
                }
            }
            stringBuilder.append("|");
            stringBuilder.append("\n");
        }
        stringBuilder.append("-".repeat(42));
        return stringBuilder.toString();
    }

}
