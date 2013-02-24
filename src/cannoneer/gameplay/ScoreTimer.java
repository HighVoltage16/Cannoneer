package cannoneer.gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ScoreTimer {

    private int time = 0;

    public ScoreTimer(int delay) {
        ActionListener taskPerformer = new ActionListener() {
            //creates a class within a class, and records time with a separate thread (not the main thread)

            public void actionPerformed(ActionEvent evt) {
                time++;
            }
        };
        new Timer(delay, taskPerformer).start(); //starts the thread
    }

    public String getTime() {
        return Integer.toString(time);
    }
}
