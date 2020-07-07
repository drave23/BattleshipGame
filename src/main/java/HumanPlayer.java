import java.util.ArrayList;

public class HumanPlayer extends Player {


    public HumanPlayer(IBoard board) {
        super(board);
    }

    @Override
    public State fire(Player rival,Position position) {
        State state = rival.getBoard().setHit(position);
        statistic.updateStatistic(state);
        return state;
    }


    public Position placeNextShip(IShip ship, Position position, Boolean isVertical) {

        getBoard().setShipOnBoard(ship, position, isVertical);
        return null;
    }

}