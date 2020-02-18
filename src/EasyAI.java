public class EasyAI extends AI {

    public EasyAI(String name, View viewer) {
        super(name, viewer);
    }

    public Coordinates getRandomCoordinates(Randomizer randomizer) {
        int shootListIndex = randomizer.getRandomDigitFromParamter(this.getPossibleShoots().size());
        return this.getPossibleShoots().get(shootListIndex);
    }
}