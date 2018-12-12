import javafx.geometry.Pos;

public class TowerDefenceLevel {
    private int height;
    private int width;
    private boolean[][] passable;
    private int startRow;

    public boolean[][] getPassable() {
        return passable;
    }

    private int startCol;
    private int targetRow;
    private int targetCol;
    private int maxTowers;


    // Create a new tower defence level with the following parameters:
    // - 'width' and 'height' are the number of rows and columns of the level
    // - 'passable' is an array of booleans specifying whether each position in
    //   the level can be walked on by the monster. 'true' corresponds to a
    //   passable (white) square, and 'false' corresponds to an impassable (green)
    //   square, where the player can build their towers.
    // - 'startRow' and 'startCol' indicate the initial position of the monster.
    // - 'targetRow' and 'targetCol' indicate the target position of the monster.
    //   (when the monster reaches this position, the player loses the game).
    public TowerDefenceLevel(int height, int width, boolean[][] passable,
                             int startRow, int startCol,
                             int targetRow, int targetCol,int maxTowers) {
        this.height= height;
        this.width = width;
        this.passable = passable;
        this.startRow = startRow;
        this.startCol = startCol;
        this.targetRow = targetRow;
        this.targetCol = targetCol;
        this.maxTowers = maxTowers;

    }

    public int getTargetRow() {
        return targetRow;
    }

    public int getTargetCol() {
        return targetCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getHeight() {
    return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMaxTowers() {
        return maxTowers;
    }

    public static TowerDefenceLevel buildSimpleLevel() {
        boolean[][] passable = {
                { false , false , false },
                { true  , true  , true  },
                { false , false , false }
        };
        return new TowerDefenceLevel(3,3,passable,1,0,1,2,3);
    }

    public static TowerDefenceLevel buildDefaultLevel() {
        boolean[][] passable = {
                { false , false , true , true , true , false , false , false },
                { true  , true  , true , false, true,  false , false , false },
                { false , false , false, false, true,  true  , true  , true  }
        };
        return new TowerDefenceLevel(3,8,passable,1,0,2,7,11);
    }

    public static TowerDefenceLevel buildAdvancedLevel() {
        boolean[][] passable = {
                { false , true , true  , true , false , true , true  , true , false },
                { true  , true , false , true , true  , true , false , true , true  },
                { false , true , true  , true , false , true , true  , true , false }
        };
        return new TowerDefenceLevel(3,9,passable,1,0,1,8,7);
    }

    public static TowerDefenceLevel buildSpiralLevel() {
        boolean[][] passable = {
                { true , false , false , false , false , false , false , false , false },
                { true , false , true  , true  , true  , true  , true  , true  , true  },
                { true , false , true  , false , false , false , false , false , true  },
                { true , false , true  , false , true  , true  , true  , false , true  },
                { true , false , true  , false , true  , false , true  , false , true  },
                { true , false , true  , false , false , false , true  , false , true  },
                { true , false , true  , true  , true  , true  , true  , false , true  },
                { true , false , false , false , false , false , false , false , true  },
                { true , true  , true  , true  , true  , true  , true  , true  , true  }
        };
        return new TowerDefenceLevel(9,9,passable,0,0,4,4,8);
    }

    public static TowerDefenceLevel buildGridLevel() {
        boolean[][] passable = {
                { true , true  , true , true  , true , true  , true , true  , true },
                { true , false , true , false , true , false , true , false , true },
                { true , true  , true , true  , true , true  , true , true  , true },
                { true , false , true , false , true , false , true , false , true },
                { true , true  , true , true  , true , true  , true , true  , true },
                { true , false , true , false , true , false , true , false , true },
                { true , true  , true , true  , true , true  , true , true  , true },
                { true , false , true , false , true , false , true , false , true },
                { true , true  , true , true  , true , true  , true , true  , true }
        };
        return new TowerDefenceLevel(9,9,passable,0,0,8,8,8);
    }

}
