import java.util.ArrayList;
import java.util.List;

public abstract class AI extends Player {
    private List<Coordinates> possibleShoots;

    public AI(String name, View viewer) {
        super(name, viewer);
        this.possibleShoots = new ArrayList<Coordinates>();
        this.fillShootsList();
    }

    private void fillShootsList() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.possibleShoots.add(new Coordinates(i, j));
            }
        }
    }

    public List<Coordinates> getPossibleShoots() {
        return this.possibleShoots;
    }

    public void removeUsedCoordinates(int index) {
        this.possibleShoots.remove(index);
    }

    public abstract Coordinates getRandomCoordinates(Randomizer randomizer);
}