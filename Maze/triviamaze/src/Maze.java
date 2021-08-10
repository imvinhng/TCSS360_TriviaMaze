public class Maze {
    private int size = 4;
    private int roomId = 0;
    private Room[][] rooms = new Room[size][size];

    public Maze() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.rooms[i][j] = new Room();
                this.rooms[i][j].setRoomId(roomId++);
            }
        }
        mazeGen();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public void mazeGen() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j < size - 1) {
                    rooms[i][j].setRight(rooms[i][j + 1]);
                }
                if (i < size - 1) {
                    rooms[i][j].setDown(rooms[i + 1][j]);
                }
                if (i > 0) {
                    rooms[i][j].setUp(rooms[i - 1][j]);
                }
                if (j > 0) {
                    rooms[i][j].setLeft(rooms[i][j - 1]);
                }

            }
        }

    }
    public void displayMaze(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(rooms[i][j].getDown() != null)
                    System.out.print(rooms[i][j].getDown().getRoomId() + "\t");
                else
                    System.out.print("N\t");
            }
            System.out.println();
        }

    }

    public void displayMaze(int currentRoomId, int endRoomId){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(rooms[i][j].getRoomId() == currentRoomId)
                    System.out.print("O\t");
                else if(rooms[i][j].getRoomId() == endRoomId)
                    System.out.print("E\t");
                else
                    System.out.print("X\t");
            }
            System.out.println();
        }

    }

}
