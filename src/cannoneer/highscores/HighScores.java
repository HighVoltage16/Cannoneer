package cannoneer.highscores;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HighScores {

    public static Score[] getScores(DB db) throws SQLException {
        Score[] scArr = new Score[30];
        //if array is only 10 units big, the chance of an error is quite high
        //increasing the size to 30 reduces this chance

        String name;
        String date;
        int score;
        int index = 0;

        ResultSet rs;
        rs = db.queryTbl("SELECT TOP 10 * FROM hs_table ORDER BY Score");

        while (rs.next()) {
            name = rs.getString(2);
            score = rs.getInt(3);
            String tempDate = rs.getString(4) + "";
            date = "";

            for (int i = 0; i < 10; i++) {
                date += tempDate.charAt(i);
            }

            scArr[index] = new Score(name, date, score);
            index++;
        }
        return scArr;
    }
}
