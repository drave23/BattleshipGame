import java.util.ArrayList;
import java.util.List;

public class Game {
    Factory factory = new Factory();
    private Player player;
    private boolean isGameRun = false;
    private boolean quit = false;
    private ArrayList<String> mainMenu = new ArrayList<String>();
    UserInterface UserInterface = factory.getUserInterface();
    private Validator validator = factory.getValidator(UserInterface);
    ArrayList<ShipType> shipTypeList;

    public Game() {
        shipTypeList = ShipType.getAllShipType();
        setMainMenu();
    }

    private void init() {
        quit = false;
        isGameRun = false;

    }

    protected void menu() {

        printMenu(mainMenu);
        int input = -1;
        while (!quit) {
            try {
                input = UserInterface.getMenuOption();
            } catch (Exception e) {
                //send it to the console
                UserInterface.printMassage("Please enter number");
                printMenu(this.mainMenu);
                input = -1;
            }
            switch (input) {
                case 1:
                    this.startGameAndSetShips();
                    break;
                case 2:
                    this.executeMove();
                    break;
                case 3:
                    this.showStatistic();
                    break;
                case 4:
                    this.showBoard();
                    break;
                case 5:
                    this.quiteGame();
                    break;
                default:
                    System.out.println("Please choose a number between 1-5");
            }
        }
    }

    private void startGameAndSetShips() {
        init();
        isGameRun = true;
        player = factory.GetPlayer(createBoard());

        for (ShipType shipType : shipTypeList) {
            IShip ship = factory.getShip(shipType);
            UserInterface.printMassage("Ship Name : " + ship.getName() + " Length:" + ship.getLength() + " Width:" + ship.getWidth());
            Boolean isHorizontal = UserInterface.askHorizontalOrVertical();
            Position position = new Position(-1, -1);
            do {
                ArrayList<Integer> userAns = UserInterface.askInputRowColumn("Please insert a ship - the coordinate represents the top left ship corner");
                position.setRow(userAns.get(0));
                position.setColumn(userAns.get(1));
            } while (!validator.validateShipPosition(player.getBoard(), ship, position, isHorizontal));

            player.placeNextShip(ship, position, isHorizontal);
            player.getBoard().printBoard();
        }
        printMenu(this.mainMenu);
    }

    private void setMainMenu() {
        this.mainMenu.add("Start a new game");  //1
        this.mainMenu.add("Hit a ship!"); //2
        this.mainMenu.add("Statistics"); //3
        this.mainMenu.add("Show board"); //4
        this.mainMenu.add("Quit"); //5

    }

    private void quiteGame() {
        UserInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        UserInterface.printMassage("Hope you enjoyed:)");
        UserInterface.printMassage("Thank you for playing... see you next time ;)");
        UserInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        UserInterface.printMassage("Quit!");
        quit = true;
    }

    private void endGame() {
        isGameRun = false;
        UserInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        UserInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        UserInterface.printMassage("you won!");
        UserInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        UserInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        showStatistic();

    }

    private void showStatistic() {
        if (isGameRun) {
            UserInterface.printMassage(" ");
            UserInterface.printMassage("**************Statistics***************");
            UserInterface.printMassage("Sunken ships: " + player.getStatistic().getSunk());
            UserInterface.printMassage("Hits: " + player.getStatistic().getHits());
            UserInterface.printMassage("Miss: " + (player.getStatistic().getTotal() - player.getStatistic().getHits()));
        } else {
            UserInterface.printMassage("no game is running..");
        }
        printMenu(this.mainMenu);

    }

    private void executeMove() {
        if (isGameRun) {
            Position position = validator.validateFirePosition(player.getBoard());
            State state = player.fire(player, position);
            UserInterface.getAward(state);

            if (player.getStatistic().getSunk() == player.getBoard().getNumberOfShipsOnBoard()) {
                endGame();
                player.getBoard().printBoard();
                return;

            }
            this.player.getBoard().printBoard();
        } else {
            UserInterface.printMassage("For hitting a ship, you should start a new game");
        }
        printMenu(this.mainMenu);


    }

    private void showBoard() {
        if (isGameRun) {
            player.getBoard().printBoard();
        } else {
            UserInterface.printMassage("no game is running..");
        }
        printMenu(this.mainMenu);


    }

    public boolean printMenu(List<String> options) {

        int optionNumber = 1;

        System.out.println("Menu :");
        for (String option : options) {
            System.out.printf("%d. %s \n\r", optionNumber, option);
            optionNumber++;
        }
        System.out.println();
        System.out.println("Please Enter your choice (action number): ");
        return true;
    }

    private IBoard createBoard() {
        ArrayList<Integer> boardSize = null;
        int x;
        int y;
        do {
            boardSize = UserInterface.askInputRowColumn("Board must be between " + Constant.BOARD_MIN_SIZE + " to " + Constant.BOARD_MAX_SIZE);
            x = boardSize.get(0);
            y = boardSize.get(1);
        } while (!UserInterface.validateBoardSize(x, y));

        return new Board(x, y);


    }

}