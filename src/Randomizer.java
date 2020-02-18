import java.util.Random;

public class Randomizer {
    Random random;

    public Randomizer() {
        this.random = new Random();
    }

    public Coordinates getRandomCoordantes() {
        int coordX = this.random.nextInt(10);
        int coordY = this.random.nextInt(10);
        return new Coordinates(coordX, coordY);
    }

    public String getRandomDirection() {
        int directionInt = this.random.nextInt(2);
        String direction = (directionInt < 1)? "h" : "v";
        return direction;
    }

    public int getRandomDigitFromParamter(int paramater) {
        return this.random.nextInt(paramater);

    }
}