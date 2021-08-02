package program;

public class Rooms2DArray {
    private int myRow;
    private int myCol;
    private Maze myMaze;
    private Room[][] myRoomCollection;


    public Rooms2DArray(final Maze theMaze, final int row, final int col) {
        myRow = row;
        myCol = col;
        myMaze = theMaze;
        myRoomCollection = new Room[myRow][myCol];

        populateRoom();

    }

    public void populateRoom() {
        int roomNumber = 1;
        for (int row = 0; row < myRow; row++) {
            for (int col = 0; col < myCol; col++) {
                myRoomCollection[row][col] = new Room(myMaze, row, col, roomNumber++);
            }
        }
    }

    public int getSize() {
        return myCol * myRow;
    }

    public Room[][] getCollection() {
        return myRoomCollection;
    }

    public Room getEntrance() {
        return myRoomCollection[0][0];
    }

    public Room getExit() {
        return myRoomCollection[myRow-1][myCol-1];
    }
}
