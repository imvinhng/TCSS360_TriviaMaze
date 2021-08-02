public class Player {

    Integer myXPosition;
    Integer myYPosition;
    Integer myAvailableHints;

    public Player (Integer theInitialX, Integer theInitialY) {
        setPlayerPosition(theInitialX, theInitialY);
        myAvailableHints = 3;
    }

    public void movePlayerNorth() {
        setPlayerPosition(myXPosition, myYPosition - 1);
    }

    public void movePlayerSouth() {
        setPlayerPosition(myXPosition, myYPosition + 1);
    }

    public void movePlayerEast() {
        setPlayerPosition(myXPosition + 1, myYPosition);
    }

    public void movePlayerWest() {
        setPlayerPosition(myXPosition - 1, myYPosition);
    }

    public void setPlayerPosition(Integer theXPosition, Integer theYPosition) {
        myXPosition = theXPosition;
        myYPosition = theYPosition;
    }

    public Integer getPlayerXPosition() {
        return myXPosition;
    }

   public Integer getPlayerYPosition() {
       return myYPosition;
   }

    public void useHint() {
        if (myAvailableHints >0) {
            myAvailableHints = myAvailableHints - 1;
        }
    }

    public Integer getAvailableHints() {
        return myAvailableHints;
    }
}
