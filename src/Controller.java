import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Controller {
    private boolean restartGame;
    private View view;
    private Reader reader;
    private ControllerHelper controllerHelper;
    private Randomizer randomizer;

    public Controller() {
        this.restartGame = false;
        this.view = new View();
        this.reader = new Reader(this.view);
        this.controllerHelper = new ControllerHelper();
        this.randomizer = new Randomizer();

    }

    public void runner() {
        while (!this.restartGame) {
            handleMainOption();
        }
    }

    private void handleMainOption() {
        this.controllerHelper.clearScreen();
        this.view.printMenu();
        int option = this.reader.getIntFromUser("Option", 1, 4);
        this.controllerHelper.clearScreen();
        switch (option) {
            case 1:
                handleGameModes();
                break;
            case 2:
                this.view.printHighscore();
                break;
            case 3:
                this.view.printCredits();
                reader.promptEnterKey();
                break;
            case 4:
                this.restartGame = true;
                break;

        }
    }

    private void handleGameModes() {
        this.view.printGameModes();
        int gameModes = this.reader.getIntFromUser("Choose your game mode", 1, 3);
        this.controllerHelper.clearScreen();
        switch (gameModes) {
            case 1:
                playerVsPlayer();
                break;
            case 2:
                handleGameDifficulty(gameModes);
                break;
            case 3:
                handleGameDifficulty(gameModes);
                break;
        }
    }

    private void handleGameDifficulty(int gameModes) {
        this.view.printGameDifficulty();
        int gameDifficulty = this.reader.getIntFromUser("Choose your game difficulty", 1, 3);
        this.controllerHelper.clearScreen();

        switch (gameModes) {
            case 2:
                playerVsComputer(gameDifficulty);
                break;
            case 3:
                computerVsComputer(gameDifficulty);
                break;
        }
    }


    private void playerVsComputer(int gameDifficulty) {
        this.controllerHelper.clearScreen();
        Player playerOne = createPlayer("Player one: what's is your name");
        putShipsOnMap(playerOne, true);

        this.controllerHelper.clearScreen();
        AI computerTwo = createComputer(gameDifficulty, playerOne.getName());
        putShipsOnMap(computerTwo, false);

        this.controllerHelper.clearScreen();
        while (!(playerOne.getOcean().isGameOver() || computerTwo.getOcean().isGameOver())) {
            playerComputerShoot(playerOne, computerTwo);
            computerPlayerShoot(computerTwo, playerOne);
            if (!playerOne.getOcean().isGameOver()) {
                playerComputerShoot(playerOne, computerTwo);
                view.printLine(playerOne.getName() + " WON!");
            } else {
                view.printLine(computerTwo.getName() + " WON!");
                computerPlayerShoot(computerTwo, playerOne);
            }
        }

        reader.promptEnterKey();
    }

    private void computerPlayerShoot(AI playerShoot, Player playerShip) {
        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);
        Coordinates playerOneCoordinates = playerShoot.getRandomCoordinates(this.randomizer);
        playerShoot.shoot(playerOneCoordinates);

    }

    private void playerComputerShoot(Player playerShoot, AI playerShip) {
        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);
        Coordinates playerOneCoordinates = reader.getUserCoordinates("Player " + playerShoot.getName() + ": Please provide coordinates");
        playerShoot.shoot(playerOneCoordinates);

        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);

    }

    private void playerVsPlayer() {
        this.controllerHelper.clearScreen();
        Player playerOne = createPlayer("Player one: what's is your name");
        putShipsOnMap(playerOne, true);

        this.controllerHelper.clearScreen();
        Player playerTwo = createPlayer("Player two: what's is your name");
        putShipsOnMap(playerTwo, true);

        this.controllerHelper.clearScreen();
        while (!(playerOne.getOcean().isGameOver() || playerTwo.getOcean().isGameOver())) {
            playerShoot(playerTwo, playerOne);
            playerShoot(playerOne, playerTwo);

            if (!playerOne.getOcean().isGameOver()) {
                playerShoot(playerTwo, playerOne);
                view.printLine(playerOne.getName() + " WON!");
            } else {
                playerShoot(playerOne, playerTwo);
                view.printLine(playerTwo.getName() + " WON!");

            }
        }

    }

    private void computerVsComputer(int gameDifficulty) {
        this.controllerHelper.clearScreen();
        AI computerOne = createComputer(gameDifficulty, "computer1");
        putShipsOnMap(computerOne, false);

        this.controllerHelper.clearScreen();
        AI computerTwo = createComputer(gameDifficulty, "computer2");
        putShipsOnMap(computerTwo, false);

        this.controllerHelper.clearScreen();
        while (!(computerOne.getOcean().isGameOver() || computerTwo.getOcean().isGameOver())) {
            computerShoot(computerTwo, computerOne);
            computerShoot(computerOne, computerTwo);

            if (!computerOne.getOcean().isGameOver()) {
                computerShoot(computerTwo, computerOne);
                view.printLine(computerOne.getName() + " WON!");

            } else {
                computerShoot(computerOne, computerTwo);
                view.printLine(computerTwo.getName() + " WON!");

            }
        }

        reader.promptEnterKey();
    }

    private void computerShoot(AI playerShoot, AI playerShip) {
        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);
        Coordinates playerOneCoordinates = playerShoot.getRandomCoordinates(this.randomizer);
        playerShoot.shoot(playerOneCoordinates);

        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);


        sleep(100);
        if (!(playerShoot.getOcean().isGameOver() || playerShip.getOcean().isGameOver())) {
            this.controllerHelper.clearScreen();

        }
    }

    private void playerShoot(Player playerShoot, Player playerShip) {
        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);
        Coordinates playerOneCoordinates = reader.getUserCoordinates("Player " + playerShip.getName() + ": Please provide coordinates");
        playerShoot.shoot(playerOneCoordinates);

        this.controllerHelper.clearScreen();
        printMap(playerShoot, playerShip);

        reader.promptEnterKey();
        this.controllerHelper.clearScreen();

        reader.promptEnterKey();
        this.controllerHelper.clearScreen();
    }

    private void printMap(Player playerShoot, Player playerShip) {
        System.out.println("\n PLAYER: " + playerShip.getName());
        String playerOneShootPlayerTwo = playerShoot.getOcean().mapToString("shoot");
        String playerOneShips = playerShip.getOcean().mapToString("ship");
        this.view.printMap(this.controllerHelper.joinMaps(playerOneShootPlayerTwo, playerOneShips));
    }

    private void sleep(int delay) {
                    try
            {
                Thread.sleep(delay);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    }

    private void putShipsOnMap(Player player, boolean isPlayer) {
        boolean automate = false;
        if (isPlayer) {
            String placement  = this.reader.getStringFromUser(
                    "Player " + player.getName() + ": Do you want put your ship automatically (y/n)", "[yn]{1}");
            automate = placement.equals("y");
        } else {
            automate = true;

        }


        Coordinates getCoordinates = randomizer.getRandomCoordantes();
        String direction = randomizer.getRandomDirection();

        if (!automate) {
            this.view.printMap(player.getOcean().mapToString("ship"));
        }

        for (Map.Entry<String, Integer> entry : controllerHelper.getShipNamesWithLength().entrySet()) {
            boolean isShipPlaced = false;
            while (!isShipPlaced) {
                if (automate) {
                    getCoordinates = randomizer.getRandomCoordantes();
                    direction = randomizer.getRandomDirection();

                } else {
                    String questionShip = "Please provide a coordinate for " + entry.getKey() + ", length: " + entry.getValue() + " (e.g A1)";
                    getCoordinates = reader.getUserCoordinates(questionShip);
                    direction = this.reader.getStringFromUser("Vertically or horizontally (h/v)", "[hv]{1}");
                    this.controllerHelper.clearScreen();
                }

                isShipPlaced = player.shipPlacement(getCoordinates, direction, entry.getKey(), entry.getValue(), automate);

                if (!automate) {
                    this.view.printMap(player.getOcean().mapToString("ship"));

                }
            }
        }
        if (automate) {
            this.view.printMap(player.getOcean().mapToString("ship"));
        }
    }


    private Player createPlayer(String name) {
        String player = this.reader.getNameFromUser(name);
        return new Player(player, this.view);
    }

    private AI createComputer(int difficulty, String computerName) {
        if (difficulty == 1) {
            return new EasyAI(computerName, this.view);
        } else if (difficulty == 2) {
            return new MediumAI(computerName, this.view);
        } else {
            return new EasyAI(computerName, this.view);
        }


    }


}
