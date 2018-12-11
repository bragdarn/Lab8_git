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
  private final Map<String, JPanel> positionPanels = new HashMap<>();

  // The size of each position panel
  private static final int POSITION_SIZE = 100;

  // A timer that will automatically advance the game each second.
  private final Timer timer;
  private static final int SPEED = 1000;
  private static final int PAUSE = 3000;

  // A representation of the complete game
  private TowerDefenceLevel level;
  private JPanel positionPanel;
  private JLabel towerLabel;
  private int clickCounter = 1;
  private Monster monster;






  public static void main(String[] args) {

    // Change this to try out different levels
    TowerDefenceLevel level = TowerDefenceLevel.buildDefaultLevel();

    // Create the GUI and set it to be visible
    GUITowerDefence gui = new GUITowerDefence(level);
    gui.setVisible(true);



  }

  public GUITowerDefence(TowerDefenceLevel level) {

    this.setTitle("Tower Defence");
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

    for (int row = 0; row < levelHeight; row++) {
      for (int col = 0; col < levelWidth; col++) {
        positionPanel = new JPanel();
        positionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if(!passable[row][col]) {
          positionPanel.addMouseListener(box);
          positionPanel.setBackground(Color.GREEN);
        }
        mainPanel.add(positionPanel);


        // Add the panel to the 'positionPanels' map so we can access it
        // later with positionPanels.get(posId);
        Position pos = new Position(row,col);
        String posId = pos.getPosId();
        positionPanels.put(posId, positionPanel);
      }
    }
      //test for hashmap keys
    //System.out.println(positionPanels.keySet());
//    JPanel panel1 =  positionPanels.get("10");
//    panel1.add(monsterPanel);
   this.monster = new Monster(level,level.getStartRow(),level.getStartCol());
   setMonsterGui(monster.getPosId(),monster);
   monster.setLastPos(monster.getPosId());
//    testMonsterPositions(monster);



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

      String pos = monster.move();
      JPanel lastPosPanel = positionPanels.get(monster.getPosId());
      lastPosPanel.removeAll();
      monster.setLastPos(monster.getPosId());
      monster.setPosId(pos);
      monster.setRow(Integer.parseInt(pos.substring(0,1)));
      monster.setCol(Integer.parseInt(pos.substring(1,2)));
      setMonsterGui(pos, monster);

      boolean gameOver = false;

      if (gameOver) {
        setTitle("Game over!");
        timer.stop();
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

  
  private JLabel buildTowerLabel() {
    return getIconLabel("icons/tower-icon.png");
  }
// Builds the monsterLabel on a new panel and returns it
  private JPanel buildMonsterPanel(JPanel panel,int monsterHealth) {

    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());

    JLabel monsterIcon = getIconLabel("icons/monster10.gif");
    panel.add(monsterIcon, BorderLayout.CENTER);

    JLabel healthLabel = new JLabel(Integer.toString(monsterHealth));
    healthLabel.setFont(new Font("Serif", Font.BOLD, 10));
    panel.add(healthLabel, BorderLayout.SOUTH);

    return panel;
  }
private Tower createTower(String pos){
    Tower tower = new Tower(pos);
    return tower;
} //Creates the tower label and puts it on a specific position
private JPanel buildTowerGui(JPanel towerPanel) {


  towerLabel = buildTowerLabel();
  towerPanel.removeAll(); //removes the button so that tower is visible
  towerPanel.add(towerLabel, BorderLayout.CENTER);
  return towerPanel;
}



//Builds the tower on the position that was clicked

  public class BoxListener extends MouseAdapter{
    public void mouseClicked(MouseEvent event){
      if (clickCounter<=level.getMaxTowers()) {
        JPanel clickedPanel = (JPanel) event.getSource();
        buildTowerGui(clickedPanel);
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

