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
        List<String> possiblePositions = checkNextPositions(new Position(getRow(),getCol()));
        String nextPos = nextPosition(possiblePositions);
        return nextPos;
    }
    
    
    // Change position
      for(int i = 0; i < 5; i++) {
        // Remove old monsterPanel
        JPanel currentMonsterPanel = positionPanels.get(monster.getPosId());
        currentMonsterPanel.removeAll();

        String nextpos = monster.move();
        System.out.println(nextpos);
        monsterGui(monster.getPosId(),monster);
      }
