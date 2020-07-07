import java.util.ArrayList;

public class Factory {

    public IBoard getBoard(int x, int y){
        return new Board(x,y);
    }

    public Player GetPlayer(IBoard board){
        return new HumanPlayer(board);
    }

    public Position getPosition(int x , int y){
        return new Position(x,y);
    }

    public IShip getShip(ShipType shipType){
        return new Ship(shipType);
    }

    public UserInterface getUserInterface(){
        return new UserInterface();
    }

    public Validator getValidator(UserInterface userInterface){
        return new Validator(userInterface);
    }

}
