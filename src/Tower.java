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

    public void setTowerPos(String towerPos) {
        this.towerPos = towerPos;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
