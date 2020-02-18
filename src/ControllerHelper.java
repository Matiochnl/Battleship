import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ControllerHelper {

    public ControllerHelper() {

    }

    public Map<String, Integer> getShipNamesWithLength() {
        Map<String, Integer> shipNames = new LinkedHashMap<>();
        shipNames.put("Carrier", 5);
        shipNames.put("Battleship", 4);
        shipNames.put("Cruiser", 3);
        shipNames.put("Submarine", 3);
        shipNames.put("Destroyer", 2);

        return shipNames;
    }

    public String joinMaps(String mapShoots, String mapShips) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] mapShootsArr = mapShoots.split("\n");
        String[] mapShipsArr = mapShips.split("\n");

        for (int i = 0; i < mapShipsArr.length; i++) {
            stringBuilder.append(mapShootsArr[i] + " ".repeat(10) +  mapShipsArr[i] + "\n");
        }
        return stringBuilder.toString();

    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
