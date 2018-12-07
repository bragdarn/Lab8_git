import java.util.ArrayList;
import java.util.List;

public class Monster {

    private Position monsterPos;
    private int maxHealth;
    private int currentHealth;
    private String posId;

    public Monster(int startRow, int startCol) {
        monsterPos = new Position(startRow, startCol);
        posId = monsterPos.getPosId();
        maxHealth = 10;
        this.currentHealth = maxHealth;
    }

    public Position getMonsterPos() {
        return monsterPos;
    }

    public void setMonsterPos(Position monsterPos) {
        this.monsterPos = monsterPos;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(int row,int col) {

        this.posId = Integer.toString(row+col);
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
    //returns a List of all possible positions the monster can go
    public List<String> checkNextPositions(Position currentPos, TowerDefenceLevel level) {
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();
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
        if(currentRow<level.getHeight()){
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
        if (passable[currentRow][currentCol + 1]) {
            right = Integer.toString(currentRow)+Integer.toString(currentCol+1);
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
