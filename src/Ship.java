import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Square> shipSquares;

    public Ship(List<Square> shipSquares) {
        this.shipSquares = shipSquares;
    }

    public boolean isDestroyed() {
        int destroyedSquares = 0;
        int lengthShip = this.shipSquares.size();
        for (Square square: this.shipSquares) {
            if (square.getMyStatus().equals("X") || square.getMyStatus().equals("#")) {
                destroyedSquares++;
            }
        }
        return destroyedSquares == lengthShip;
    }

    public void markHit(Square square) {
        square.setMyStatus("X");
        square.setOpponentStatus("X");
        if (this.isDestroyed()) {
            this.markAsSunk();
        }
    }

    private void markAsSunk() {
        for (Square square: this.shipSquares) {
            square.setMyStatus("#");
            square.setOpponentStatus("#");
        }
    }
}
