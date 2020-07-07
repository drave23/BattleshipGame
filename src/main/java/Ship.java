public class Ship implements IShip {
    private String name;
    private int size_length;
    private int size_width;

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
    private int livesLeft;
    private boolean isSunk;

    public Ship(ShipType shipType) {
        this.name = shipType.name();
        this.size_length = shipType.getLength();
        this.size_width = shipType.getWidth();
        this.livesLeft = shipType.getLength() * shipType.getWidth();
        this.isSunk = false;
    }
    public String getName() {
        return name;
    }
    public int getLength() {
        return size_length;
    }
    public int getWidth() {
        return size_width;
    }
    public State hit() {
        if (isSunk) {
            return State.NO_HIT;
        }

        livesLeft--;
        if (livesLeft == 0) {
            isSunk = true;
            return State.SUNK;
        } else {
            return State.PARTIAL_HIT;
        }

    }
}
