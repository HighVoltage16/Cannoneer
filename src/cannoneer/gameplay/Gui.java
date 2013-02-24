package cannoneer.gameplay;

import cannoneer.tools.Cursor;
import javax.swing.*;

public class Gui {

    private JFrame window;
    private DrawPanel panel;

    private Gui() {
        window = new JFrame("Cannoneer");
        panel = new DrawPanel();
        window.setSize(806, 628); //to account for the border; size is actually 800x600
        Cursor.setDefault(window);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null); //to make sure that it is in the centre of the screen
        window.getContentPane().add(panel);
        window.setResizable(false);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Gui game = new Gui();
        game.go();
    }

    private void go() {
        panel.startGame();
    }
}
