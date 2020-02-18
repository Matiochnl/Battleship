public class MediumAI extends AI {

    public MediumAI(String name, View viewer) {
        super(name, viewer);
    }

    public Coordinates getRandomCoordinates(Randomizer randomizer) {
        int shootListIndex = randomizer.getRandomDigitFromParamter(this.getPossibleShoots().size());
        Coordinates shootCoodinates = this.getPossibleShoots().get(shootListIndex);
        this.removeUsedCoordinates(shootListIndex);
        return shootCoodinates;
    }
}