public class Room {
    private String roomName;

    private int column;

    private int row;

    private int row2;

    private int row3;

    public Room(String roomName, int column, int row, int row2, int row3) {
        this.roomName = roomName;
        this.column = column;
        this.row = row;
        this.row2 = row2;
        this.row3 = row3;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    public int getRow2() {
        return this.row2;
    }

    public int getRow3() {
        return this.row3;
    }
}
