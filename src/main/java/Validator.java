import java.util.ArrayList;

public class Validator {

    UserInterface userInterface;

    public Validator(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    protected Position validateFirePosition(IBoard board) {

        Position position = new Position(-1, -1);
        do {
            ArrayList<Integer> userAns = userInterface.askInputRowColumn("Please insert coordinates to hit");
            position.setRow(userAns.get(0));
            position.setColumn(userAns.get(1));
        }
        while (!validateInBoardPosition(board, position));

        return position;
    }

    protected boolean validateShipPosition(IBoard board, IShip ship, Position position, Boolean isVertical) {
        if ((!validateInBoardPosition(board, position))) {
            return false;
        }
        int tempx = 0;
        int tempy = 0;
        int verticalFlag = 0;
        int horizonFlag = 0;

        if (isVertical) {
            verticalFlag = 1;
        } else {
            horizonFlag = 1;
        }
        //check if ship tail out of bounds
        if (position.getRow() + ship.getLength() * verticalFlag + ship.getWidth() * horizonFlag > board.getRow() || position.getColumn() + ship.getWidth() * verticalFlag + ship.getLength() * horizonFlag > board.getColumn()) {
            userInterface.printMassage(Constant.INVALID_COORDINATE + " all the ship must be inside the boards");
            return false;
        }
        for (int i = 0; i < ship.getWidth(); i++) {
            tempx = position.getRow() + i * horizonFlag;
            tempy = position.getColumn() + i * verticalFlag;

            if (board.isShipHere(tempx ,tempy)) {
                userInterface.printMassage(Constant.RESET_SHIP_MSG);
                return false;
            }
            if (!validateShipDistance(board, tempx, tempy, Constant.DEFAULT_DISTANCE)) {
                userInterface.printMassage(Constant.DISTANCE_MSG);
                return false;
            }

        }
        for (int i = 0; i < ship.getLength(); i++) {
            tempx = position.getRow() + i * verticalFlag;
            tempy = position.getColumn() + i * horizonFlag;

            if (board.isShipHere(tempx ,tempy)) {
                userInterface.printMassage(Constant.RESET_SHIP_MSG);
                return false;
            }

            if (!validateShipDistance(board, tempx, tempy, Constant.DEFAULT_DISTANCE)) {
                userInterface.printMassage(Constant.DISTANCE_MSG);
                return false;
            }
        }
        return true;
    }

    protected boolean validateInBoardPosition(IBoard board , Position position) {
        if (position.getRow() < 0 || position.getRow() >= board.getRow() || position.getColumn() < 0 || position.getColumn() >= board.getColumn()) {
            userInterface.printMassage("Out of bounds");
            return false;
        }
        return true;
    }

    private boolean validateShipDistance(IBoard board, int x, int y, int distance) {

        for (int i = distance; i > 0; i--) {
            if (x - i > 0) {
                if (board.isShipHere(x - i,y)) {
                    return false;
                }
            }
            if (x + i < board.getRow()) {
                if (board.isShipHere(x + i,y)) {
                    return false;
                }
            }
        }

        for (int i = distance; i > 0; i--) {
            if (y - i > 0) {
                if (board.isShipHere(x ,y-1)) {
                    return false;
                }
            }
            if (y + i < board.getColumn()) {
                if (board.isShipHere(x ,y+1)) {
                    return false;
                }
            }
        }
        return true;
    }

}
