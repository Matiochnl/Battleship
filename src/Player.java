import java.util.List;
import java.util.ArrayList;

public class Player {
    private Ocean ocean;
    private String name;
    private View viewer;

    public Player(String name, View viewer) {
        this.ocean = new Ocean();
        this.name = name;
        this.viewer = viewer;
    }

    public String getName() {
        return this.name;
    }

    public Ocean getOcean(){
        return this.ocean;
    }

    public void shoot(Coordinates coordinates){
        this.ocean.handleShoot(coordinates);
    }

    public boolean shipPlacement(Coordinates coordinates, String direction, String shipName, int shipLength, boolean automate){
        if (this.ocean.testShipPlacement(coordinates, shipLength, direction)){
            List<Square> shipSquares = getShipSquares(coordinates, direction, shipLength, shipName);
            Ship ship = new Ship(shipSquares);
            this.ocean.addShip(shipName, ship);
            return true;
        } else {
            if (!automate) {
                viewer.printError("Ship cannot be placed");
            }
            return false;
        }
    }

    private List<Square> getShipSquares(Coordinates coordinates, String direction, int shipLength, String shipName) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        List<Square> shipSquares = new ArrayList<Square>();
        for (int i = 0; i < shipLength; i++) {
            shipSquares.add(this.ocean.getSquare(x, y));
            this.ocean.getSquare(x, y).setMyStatus("S");
            this.ocean.getSquare(x, y).setShipName(shipName);
            if (direction.equalsIgnoreCase("h")) {
                y++;
            } else {
                x++;
            }
        }
        return shipSquares;
    }

    
}