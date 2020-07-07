import java.util.ArrayList;

public enum ShipType {
/*    Carrier(5, 1),
    Battleship(4, 1),
    Destroyer(3, 1),*/
    Submarine(3, 1),
    PatrolBoat(2, 1);

    private final int length;
    private final int width;

    ShipType(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public static ArrayList<ShipType> getAllShipType() {
        ArrayList<ShipType> allSchiptypes = new ArrayList<ShipType>();
        for (ShipType shipType : ShipType.values()) {
            allSchiptypes.add(shipType);
        }
        return allSchiptypes;
    }

}