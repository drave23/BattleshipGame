import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private  Scanner scanner = new Scanner(System.in);

    public  int getMenuOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    protected  Boolean askHorizontalOrVertical() {
        String direction;

        do {
            System.out.println("Do you want to place the ship horizontally (H) or vertically (V)?");
            direction = scanner.nextLine().trim();
        } while (!"H".equals(direction) && !"V".equals(direction));
        return "V".equals(direction);
    }

    protected  ArrayList<Integer> askInputRowColumn(String msg) {

        System.out.println(msg);
        System.out.println("Please insert your choice in this format: number,number. for example: 3,3");

        ArrayList<Integer> ans = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                ans = ValidateUserInputPosition(scanner.nextLine());
                validInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again !");
                scanner = new Scanner(System.in);
            }
        }
        return ans;
    }

    public void printMassage(String msg) {
        System.out.println(msg);
    }

    public  ArrayList<Integer> ValidateUserInputPosition(String move) throws Exception {

        String[] splittedMove = move.split(",");
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] cordinate = new int[2];

        int row = -1;
        int column = -1;

        if (move.isEmpty()) {
            throw new Exception("Empty input is not valid.");
        }

        if (splittedMove.length != 2) {
            throw new Exception(Constant.INVALID_COORDINATE);
        }

        try {
            row = Integer.parseInt(splittedMove[0]);
            cordinate[0] = row;
            column = Integer.parseInt(splittedMove[1]);
            cordinate[1] = column;
        } catch (Exception e) {
            throw new Exception("Row and column must be A number !");
        }

        for (int j = 0; j < cordinate.length; j++) {
            result.add(cordinate[j]);
        }
        return result;
    }

    public  boolean validateBoardSize(int x, int y) {

        if (! (x >= Constant.BOARD_MIN_SIZE && x <= Constant.BOARD_MAX_SIZE && y >= Constant.BOARD_MIN_SIZE && y <= Constant.BOARD_MAX_SIZE)){
            printMassage("Invalid board size ");
            return false;
        }
        return true;
    }

    public  void getAward(State state){

        if (State.SUNK.equals(state)) {
            printMassage("wow! you sunk a ship");
            }
         else if (State.PARTIAL_HIT.equals(state)) {
            printMassage("Good job! you hit a ship");
        }
        else if(State.NO_HIT.equals(state) ){
            printMassage("you missed");
        }

    }

}
