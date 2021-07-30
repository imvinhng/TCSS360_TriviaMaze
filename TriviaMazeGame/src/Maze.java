import java.util.HashMap;
import java.util.Map;

public class Maze {

    Integer myRows;
    Integer myColumns;
    Player myPlayer;
    Rooms2DArray myRooms;
    Map<Integer, Door> myDoors = new HashMap<Integer, Door>();


        public Maze ( final int theRow, final int theCol) {
            myRows = theRow + 2;
            myColumns = theCol + 2;

            addRooms();
            createWalls();
            addPlayer();
        }

        public void addRooms() {
            for(int row = 1; row < myRows; row++) {
                for(int col = 1; col < myColumns; col++) {
                    addDoors(row, col);
                }
            }
        }

        public void addDoors(Integer theRoomRowPosition, Integer theRoomColumnPosition) {
//            for ()
        }

        public void createWalls(){};

        public void addPlayer(){
            myPlayer = new Player(1,1);
        }





}
