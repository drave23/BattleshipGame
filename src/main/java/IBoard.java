import java.util.List;

public interface IBoard {

    Boolean isShipHere(int x, int y);
     int getRow();
     int getColumn();
     int getNumberOfShipsOnBoard();
     void initBoard(int x, int y);
     void printBoard() ;
     boolean setShipOnBoard(IShip ship, Position position, Boolean isHorizontal);
     State setHit(Position position);
}
