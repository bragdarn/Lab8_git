import java.util.Random;

public class Tower {

    private String towerPos;
    private int damage;

    public Tower(String towerPos) {
        this.towerPos = towerPos;
        damage = 1;
    }

    public String getTowerPos() {
        return towerPos;
    }


    public int getDamage() {
        return damage;
    }
    //checks if a given tower is in range to damage monster
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public boolean inRange(Monster monster,TowerDefenceLevel level){
        int monsterRow = monster.getRow();
        int monsterCol = monster.getCol();
        int towerRow = Integer.parseInt(towerPos.substring(0,1));
        int towerCol = Integer.parseInt(towerPos.substring(1,2));
        // if tower is above monster
        if(monsterRow>0) {
            if (towerRow == monsterRow - 1 && towerCol == monsterCol) {
                return true;
            }
        }
        //if tower is above to the left of monster
        if(monsterRow>0 && monsterCol > 0){
            if(towerRow == monsterRow -1 && towerCol == monsterCol-1){
                return true;
            }
        }
        //if tower is above to the right of monster
        if(monsterRow >0 && monsterCol<level.getWidth()-1){
            if(towerRow == monsterRow -1 && towerCol == monsterCol+1){
                return true;
            }
        }
        //if tower is below monster
        if(monsterRow<level.getHeight()){
            if(towerRow == monsterRow && towerCol == monsterCol){
                return true;
            }
        }
        //if tower is below to the left of monster
        if(monsterRow<level.getHeight() && monsterCol>0){
            if(towerRow == monsterRow +1 && towerCol == monsterCol-1){
                return true;
            }
        }
        //if tower is below to the right of monster
        if(monsterRow<level.getHeight() && towerCol<level.getWidth()-1){
            if(towerRow == monsterRow +1 && towerCol == monsterCol+1){
                return true;
            }
        }
        // if tower is to the left of monster
        if(monsterCol>0){
            if(towerRow == monsterRow && towerCol == monsterCol -1){
                return true;
            }
        }
        // if tower is to the right of monster
        if(monsterCol<level.getWidth()-1){
            if(towerRow == monsterRow && towerCol == monsterCol +1){
                return true;
            }
        }

        return false;
    }
    //damages the monster if rolls above 0
    public void attack(Monster monster){
        Random random = new Random();
        int randomNum = random.nextInt(2);
        if(randomNum > 0){
            monster.setCurrentHealth(monster.getCurrentHealth()-1);
        }
    }
}



