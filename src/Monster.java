public class Monster {

    private Position monsterPos;
    private int maxHealth;
    private int currentHealth;

    public Monster(int startRow, int startCol) {
        monsterPos = new Position(startRow, startCol);
        maxHealth = 10;
        this.currentHealth = maxHealth;
    }

    public Position getMonsterPos() {
        return monsterPos;
    }

    public void setMonsterPos(Position monsterPos) {
        this.monsterPos = monsterPos;
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
}
