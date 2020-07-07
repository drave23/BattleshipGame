import java.util.ArrayList;

public abstract  class Player {

    private String name;
    private IBoard board;
    protected Statistic statistic;

    public Player(IBoard board){
        statistic = new Statistic();
        this.board = board;

    }
    public IBoard getBoard() {
        return board;
    }
    public Statistic getStatistic() {
        return statistic;
    }
    public abstract Position  placeNextShip(IShip ship, Position position, Boolean isVertical);
    public abstract State fire(Player rivalPlayer,Position position);
}
