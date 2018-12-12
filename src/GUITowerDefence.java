import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame {

  // A map that assigns a panel to each position in the game
  public static final Map<String, JPanel> positionPanels = new HashMap<>();

  // The size of each position panel
  private static final int POSITION_SIZE = 100;

  // A timer that will automatically advance the game each second.
  private final Timer timer;
  private static final int SPEED = 1000;
  private static final int PAUSE = 3000;

  private List<Tower> towers = new ArrayList<>();
  private TowerDefenceLevel level;
  private JPanel positionPanel;
  private JLabel towerLabel;
  private int clickCounter = 1;
  private Monster monster;
  private int endRow;
  private int endCol;
  boolean gameOver = false;






  public static void main(String[] args) {

    // Change this to try out different levels
    TowerDefenceLevel level = TowerDefenceLevel.buildDefaultLevel();

    // Create the GUI and set it to be visible
    GUITowerDefence gui = new GUITowerDefence(level);
    gui.setVisible(true);



  }

  public GUITowerDefence(TowerDefenceLevel level) {

    this.setTitle("SAVE THE COOKIES!");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.level = level;
    int levelHeight = level.getHeight();
    int levelWidth = level.getWidth();

    this.setSize(levelWidth*POSITION_SIZE, levelHeight*POSITION_SIZE);
    this.setResizable(false);

    // A 'main panel' that contains all other components of the GUI
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(levelHeight, levelWidth));
    this.add(mainPanel);

    boolean[][] passable = level.getPassable();

    BoxListener box = new BoxListener();
    //Creates all the positionpanels for each ow and column of the map. only adds mouseListener to the green ones.
    // I.E where the monster cannot go
    for (int row = 0; row < levelHeight; row++) {
      for (int col = 0; col < levelWidth; col++) {
        positionPanel = new JPanel();
        positionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        positionPanel.add(buildCookies());
        if(!passable[row][col]) {
          positionPanel.removeAll();
          positionPanel.addMouseListener(box);
          positionPanel.setBackground(Color.GREEN);
          positionPanel.setToolTipText(Integer.toString(row)+Integer.toString(col));
        }
        mainPanel.add(positionPanel);


        // Add the panel to the 'positionPanels' map so we can access it
        // later with positionPanels.get(posId);
        Position pos = new Position(row,col);
        String posId = pos.getPosId();
        positionPanels.put(posId, positionPanel);
      }
    }
    //creates the monster in level
   this.monster = new Monster(level,level.getStartRow(),level.getStartCol());
   setMonsterGui(monster.getPosId(),monster);
   monster.setLastPos(monster.getPosId());




    // Start the timer and set it to call the event loop each second
    EventLoop loop = new EventLoop();
    timer = new Timer(SPEED, loop);
    timer.setInitialDelay(PAUSE);
    timer.start();



  }

  // ---------- Event handling --------------------

  class EventLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      endRow = level.getTargetRow();
      endCol = level.getTargetCol();
      if(monster.getRow() == endRow && monster.getCol() == endCol){
        gameOver = true;
      }
      if (gameOver) {
        setTitle("Game over! all cookies have been eaten!");
        timer.stop();
        return;
      }
      //moving the monster
      String pos = monster.move();
      JPanel lastPosPanel = positionPanels.get(monster.getPosId());
      lastPosPanel.removeAll();
      monster.setLastPos(monster.getPosId());
      monster.setPosId(pos);
      monster.setRow(Integer.parseInt(pos.substring(0,1)));
      monster.setCol(Integer.parseInt(pos.substring(1,2)));
      setMonsterGui(pos, monster);
      //see if towers are in range and if so damages the monster for each tower
      for(Tower t: towers){
        if(t.inRange(monster,level)){
          t.attack(monster);
        }
      }
      if(monster.getCurrentHealth() <= 0){
        setTitle("Cookie monster has been defeated");
        JPanel lastPanel = positionPanels.get(monster.getPosId());
        lastPanel.removeAll();
        timer.stop();
        return;
      }

      revalidate();
      repaint();

    }
  }

  // ----------- Helper methods ---------------------

  // Helper method to construct a JLabel with a given image
  private JLabel getIconLabel(String fileName) {
    URL url = this.getClass().getResource(fileName);
    ImageIcon ii = new ImageIcon(url);
    return new JLabel(ii);
  }
  private JLabel buildCookies(){
    return getIconLabel("icons/cookie.png");
  }

  private JLabel buildTowerLabel() {
    return getIconLabel("icons/tower-icon.png");
  }
// Builds the monsterLabel on a new panel and returns it
  private JPanel buildMonsterPanel(JPanel panel,int monsterHealth) {

    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());

    JLabel monsterIcon = getIconLabel("icons/cookieMonstah.gif");
    panel.add(monsterIcon, BorderLayout.CENTER);

    JLabel healthLabel = new JLabel(Integer.toString(monsterHealth));
    healthLabel.setFont(new Font("Serif", Font.BOLD, 10));
    panel.add(healthLabel, BorderLayout.SOUTH);

    return panel;
  }
private void createTower(String pos){
    Tower tower = new Tower(pos);
    towers.add(tower);

} //Creates the tower label and puts it on a specific position
private JPanel buildTowerGui(JPanel towerPanel) {


  towerLabel = buildTowerLabel();
  towerPanel.removeAll(); //removes the button so that tower is visible
  towerPanel.add(towerLabel, BorderLayout.CENTER);
  return towerPanel;
}



//Builds the tower on the position that was clicked
//also creates a tower object on that position
  public class BoxListener extends MouseAdapter{
    public void mouseClicked(MouseEvent event){
      if (clickCounter<=level.getMaxTowers()) {
        JPanel clickedPanel = (JPanel) event.getSource();
        buildTowerGui(clickedPanel);
        String pos = clickedPanel.getToolTipText();
        createTower(pos);
        System.out.println("Tower Built");
        revalidate();
        repaint();
        clickCounter++;
        }
        else{
          System.out.println("Max number of towers built");
      }
    }
}
private void setMonsterGui(String pos,Monster monster){
    JPanel monsterPanel = positionPanels.get(pos);
    buildMonsterPanel(monsterPanel,monster.getCurrentHealth());
    revalidate();
    repaint();
}

}

