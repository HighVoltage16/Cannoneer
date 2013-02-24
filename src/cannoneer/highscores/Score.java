package cannoneer.highscores;

public class Score {

    private String name;
    private String date;
    private int score;

    public Score(String n, String d, int sc) {
        name = n;
        date = d;
        score = sc;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
