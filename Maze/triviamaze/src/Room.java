
public class Room {
    private int roomId;
    private Room right;
    private Room left;
    private Room up;
    private Room down;

    public Room() {
    }


    public Room(int roomId, Room right, Room left, Room up, Room down) {
        this.roomId = roomId;
        this.right = right;
        this.left = left;
        this.up = up;
        this.down = down;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Room getRight() {
        if(this.right != null) {
            return right;
        }

        else
            return this;
    }

    public void setRight(Room right) {
        this.right = right;
    }

    public Room getLeft() {

        if(this.left != null) {
            return left;
        }
        else
            return this;
    }

    public void setLeft(Room left) {
        this.left = left;
    }

    public Room getUp() {
        if(this.up != null) {
            return up;
        }else
            return this;
    }

    public void setUp(Room up) {
        this.up = up;
    }

    public Room getDown() {
        if(this.down != null) {
            return down;
        }else
            return this;
    }

    public void setDown(Room down) {
        this.down = down;
    }



}
