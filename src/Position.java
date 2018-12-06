// A class representing a position in the game


public class Position {
    private int row, col;
    private String posId;




    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public Position(int row, int col){
        this.row = row;
        this.col = col;
        posId = toString();

    }

    @Override
    public String toString() {
        return "" +
                 row +
                "" + col
                ;
    }
}
