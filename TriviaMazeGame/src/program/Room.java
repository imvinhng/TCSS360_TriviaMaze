package program;

public class Room {
    int myX;
    int myY;
    int myRoomNumber;

    public Room(Maze maze, Integer initialY, Integer initialX, Integer roomNumber) {
        myY = initialY;
        myX = initialX;
        myRoomNumber = roomNumber;
    }

    public void displayInfo() {
        System.out.println("program.Room #" + myRoomNumber+ " X: " + myX + ", Y: " + myY);
    }


}
