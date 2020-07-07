import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Board implements IBoard {
    private int row;
    private int column;
    private int numberOfShips;


    private char[][] board;
    private HashMap<String, IShip> positionToShip = new HashMap<String, IShip>();
    private HashMap<IShip, List<Position>> shipToPositions = new HashMap<IShip, List<Position>>();

    public Board(int row, int column) {
        this.row = row;
        this.column = column;
        initBoard(row, column);
        printBoard();
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public int getNumberOfShipsOnBoard() {
        return numberOfShips;
    }
    public void initBoard(int x, int y) {
        board = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = Constant.WATER_ICON;
            }
        }

    }
    public void printBoard() {
        System.out.print("\t");
        for (int i = 0; i < column; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print((i) + "\t");
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public boolean setShipOnBoard(IShip ship, Position position, Boolean isHorizontal) {

        numberOfShips++;
        List<Position> positions = new LinkedList<Position>();
        int xDiff = 0;
        int yDiff = 0;
        if (isHorizontal) {
            xDiff = 1;
        } else {
            yDiff = 1;
        }
        //set ship icon on board, save  Coordinate and ship object in hashMap
        for (int i = 0; i < ship.getWidth(); i++) {
            board[position.getRow() + i * yDiff][position.getColumn() + i * xDiff] = Constant.SHIP_ICON;
            Position shipPosition = new Position(position.getRow() + i * yDiff,position.getColumn() + i * xDiff);
            positionToShip.put( shipPosition.toString() , ship);
            positions.add(new Position(position.getRow() + i * yDiff, position.getColumn() + i * xDiff));

        }
        for (int i = 0; i < ship.getLength(); i++) {
            board[position.getRow() + i * xDiff][position.getColumn() + i * yDiff] = Constant.SHIP_ICON;
            Position shipPosition = new Position(position.getRow() + i * xDiff,position.getColumn() + i * yDiff);
            positionToShip.put(shipPosition.toString(), ship);
            positions.add(new Position(position.getRow() + i * xDiff, position.getColumn() + i * yDiff));

        }
        shipToPositions.put(ship, positions);
        return true;

    }
    public State setHit(Position position) {
        
        IShip ship = positionToShip.get(position.toString());

        if (ship != null) {
            if(board[position.getRow()][position.getColumn()] != Constant.SHIP_ICON ){
                return State.NO_HIT;
            }
            State ans = ship.hit();
            if (State.SUNK.equals(ans)) {
                List<Position> positions = shipToPositions.get(ship);
                for (Position shipPosition : positions) {
                    board[shipPosition.getRow()][shipPosition.getColumn()] = State.SUNK.getChar();
                }
            } else if (State.PARTIAL_HIT.equals(ans)) {
                board[position.getRow()][position.getColumn()] = State.PARTIAL_HIT.getChar();
            }
            return ans;
        } else {
            board[position.getRow()][position.getColumn()] = State.NO_HIT.getChar();
            return State.NO_HIT;
        }


    }

    public Boolean isShipHere(int x, int y){
        return board[x][y] == Constant.SHIP_ICON;
    }



}

