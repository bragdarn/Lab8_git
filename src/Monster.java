import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Position {

    private int maxHealth;
    private int currentHealth;

    private TowerDefenceLevel level;


    public Monster(TowerDefenceLevel level, int startRow, int startCol) {
        super(startRow, startCol);
        this.level = level;

        maxHealth = 10;
        this.currentHealth = maxHealth;
    }




//    public void setPosId(int row, int col) {
//
//        this.posId = Integer.toString(row) + Integer.toString(col);
//    }


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

    //returns a List of all possible positions the monster can go
    //Also checks so that you dont get arrayIndexOutOfBounds exception depending on position
    public List<String> checkNextPositions() {
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
        if (currentRow < level.getHeight()) {
            if (passable[currentRow + 1][currentCol]) {
                below = Integer.toString((currentRow + 1)) + Integer.toString(currentCol);
            }
        }
        //checks if square to left is passable
        if (currentCol > 0) {
            if (passable[currentRow][currentCol - 1]) {
                left = Integer.toString(currentRow) + Integer.toString(currentCol - 1);
            }
        }
        //checks if square to the right is passable
        if(currentCol == level.getTargetRow())
        if (passable[currentRow][currentCol + 1]) {
            right = Integer.toString(currentRow) + Integer.toString(currentCol + 1);
        }
        //adds the strings to the list if they are not empty
        if (above.equals("")) {
        } else {
            possiblePositions.add(above);
        }
        if (below.equals("")) {
        } else {
            possiblePositions.add(below);
        }
        if (left.equals("")) {
        } else {
            possiblePositions.add(left);
        }
        if (right.equals("")) {
        } else {
            possiblePositions.add(right);
        }
        return possiblePositions;
    }

    public String nextPosition(List<String> possiblePositions) {
        Random random = new Random();
        int randomNumber = random.nextInt(possiblePositions.size());
        String randomPos = possiblePositions.get(randomNumber);
        setRow(Integer.parseInt(randomPos.substring(0,1)));
        setCol(Integer.parseInt(randomPos.substring(1,2)));
        setPosId(toString());
        return randomPos;
    }

    public String move() {
        List<String> possiblePositions = checkNextPositions();
        for(String p :possiblePositions){
            System.out.println(p);
        }
        String nextPos = nextPosition(possiblePositions);
        return nextPos;
    }

}

