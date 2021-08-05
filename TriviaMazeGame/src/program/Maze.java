package program;


import java.util.HashMap;
import java.util.Map;

public class Maze {

    Integer myRows;
    Integer myColumns;
    Player myPlayer;
    Rooms2DArray myRooms;
    boolean isSolvable = true;
    Room currentRoom;
    Map<Integer, Door> myDoors = new HashMap<Integer, Door>();


    public Maze ( final int theRow, final int theCol) {
            myRows = theRow;
            myColumns = theCol;
            myRooms = new Rooms2DArray(this, theRow, theCol);

            addRooms();
            createWalls();
            addPlayer();
//            displayMazeInfo();

            currentRoom = myRooms.getEntrance();
        }


    public void addDoors(Integer theRoomRowPosition, Integer theRoomColumnPosition) {
//           for(int row = 1; row < myRows; row++) {
//                for(int col = 1; col < myColumns; col++) {
//                    addDoors(row, col);
//                }
//            }
        }

    /**
     * Populate rooms in the current program.Maze;
     */
    public void addRooms() {
            myRooms.populateRoom();
    }

    public void createWalls(){};

    /**
     * Dropping a player sprite into the maze at entrance.
     */
    public void addPlayer(){
        myPlayer = new Player(0,0);
    }

    /**
     * Display introductory prompt for user.
     */
    public void displayIntroPrompt() {
        System.out.println("\n\nWelcome to our Trivia program.Maze Game!");
        System.out.println("This game replicate an old-school maze runner game that a player\n" +
                "aka. you, will attempt to progress through the maze by answering a series\n" +
                "of questions, ranging from Multiple Choice, T/F, or Short Answers. \n\n" +
                "If you fail to answer a question, that particular door will be locked for\n" +
                "the rest of that map. There will be hints to let you unlock the doors or help you\n" +
                "progress through the maze in some ways, but they are quite rare and appear at random. \n\n" +
                "If there is no possible route for you to solve the maze due to locked doors or \n" +
                "otherwise, you will have the option of restarting that map, or end the game.\n" +
                "You're all set for now, let's have you choose your avatar to start\n\n");
        System.out.println("                                                                Next");
    }

    /**
     * Display row, colummns, # of rooms,
     */
        public void displayMazeInfo() {
            System.out.println("Row: " + myRows);
            System.out.println("Columns: " + myColumns);
            System.out.println("Total Doors: " + myDoors.size());
            System.out.println("Total Rooms: " + myRooms.getSize());

        }

        public void displayCurrentRoomInfo() {
            currentRoom.displayInfo();
        }





}
