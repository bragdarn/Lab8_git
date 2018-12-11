
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Monster extends Position {
    private int maxHealth;
    private int currentHealth;
    private TowerDefenceLevel level;
    public String lastPos = "";
    public Monster(TowerDefenceLevel level, int row, int col) {
        super(row, col);
        maxHealth = 10;
        this.level = level;
        this.currentHealth = maxHealth;
    }
    //returns the next position the monster will go. checks so that it doesn't return the last position
    public String move() {
        List<String> possiblePositions = checkNextPositions();
        String nextPos = nextPosition(possiblePositions);
        while(nextPos.equals(lastPos)){
          nextPos = nextPosition(possiblePositions);
        }
        return nextPos;
    }

    public void setLastPos(String lastPos) {
        this.lastPos = lastPos;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    //Selects a random position from the list of possible positions the monster can go
    private String nextPosition(List<String> possiblePositions) {
        Random random = new Random();
        int randomNumber = random.nextInt(possiblePositions.size());
        String randomPos = possiblePositions.get(randomNumber);
        return randomPos;
    }
    //returns a List of all possible positions the monster can go
    private List<String> checkNextPositions() {
        int currentRow = getRow();
        int currentCol = getCol();
        boolean[][] passable = level.getPassable();
        List<String> possiblePositions = new ArrayList<>();
        String above = "";
        String below = "";
        String right = "";
        String left = "";
        // check if square above is passable
        if (currentRow > 0) {
            if (passable[currentRow - 1][currentCol]) {
                above = Integer.toString((currentRow - 1)) + Integer.toString(currentCol);
            }
        }
        //checks if square below is passable
        if(currentRow<level.getHeight()-1){
            if (passable[currentRow + 1][currentCol]) {
                below = Integer.toString((currentRow + 1))+Integer.toString(currentCol);
            }
        }
        //checks if square to left is passable
        if(currentCol>0){
            if (passable[currentRow][currentCol - 1]) {
                left = Integer.toString(currentRow)+Integer.toString(currentCol-1);
            }
        }
        //checks if square to the right is passable
        if(currentCol<level.getWidth()-1) {
            if (passable[currentRow][currentCol + 1]) {
                right = Integer.toString(currentRow) + Integer.toString(currentCol + 1);
            }
        }
        //adds the strings to the list if they are not empty
        if (above.equals("")){}
        else{
            possiblePositions.add(above);
        }
        if(below.equals("")){}
        else{
            possiblePositions.add(below);
        }
        if(left.equals("")){}
        else{
            possiblePositions.add(left);
        }
        if(right.equals("")){}
        else{
            possiblePositions.add(right);
        }
        return possiblePositions;
    }
}