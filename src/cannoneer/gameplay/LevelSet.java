package cannoneer.gameplay;

public class LevelSet {
    //an array of levels

    private Level[] lvArr = new Level[10]; //only 10 levels
    private int size = 0;

    public LevelSet() {
        for (int i = 0; i < 10; i++) {
            lvArr[i] = new Level("level" + Integer.toString(i+1));
            size++;
        }
    }

    public Level getLevel(int n) {
        return lvArr[n];
    }
}
