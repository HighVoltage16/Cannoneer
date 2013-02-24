package cannoneer.gameplay;

import cannoneer.entities.*;
import cannoneer.highscores.*;
import cannoneer.tools.Sounds;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import javax.swing.*;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

    private BufferedImage buffer;
    private ScoreTimer st = (ScoreTimer) null;
    private Ball b1;
    private boolean inAir = false, lvlFinish = false, gameFinish = false;
    private int cX, cY;
    private String time;
    private LevelSet set = (LevelSet) null; //make it 'null' to make sure it's empty
    private Level currLevel = (Level) null;
    private int lvlIndex;
    private DB scoreDB; //initialise the database first to prevent too much RAM being used
    private Score[] hsArr;

    public DrawPanel() {
        setIgnoreRepaint(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (!inAir) {
            int xP = e.getX();
            int yP = 600 - e.getY();

            double xS = xP / 32; //because the dimensions are 800x600, they must both be divided by different ratios
            double yS = yP / 24;

            //this will determine where the ball must be created
            double ang = Math.atan((double) (600 - cY) / cX);
            int r = 80;
            int xPt = (int) ((r * Math.cos(ang)) + 20);
            int yPt = (int) (540 - (r * Math.sin(ang)));
            b1 = new Ball(xPt, yPt, xS, -yS);
            Sounds.playShot();

            inAir = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        //to make sure that even if the mouse is dragged, the ball will still fire and the barrel will still rotate
        mouseMoved(e);
        mousePressed(e);
    }

    public void mouseMoved(MouseEvent e) {
        cX = (int) e.getPoint().getX();
        cY = (int) e.getPoint().getY();
    }

    //THE LOOP
    private void initialise() {
        buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        set = new LevelSet();
        lvlIndex = 0;
        currLevel = set.getLevel(lvlIndex);
        scoreDB = new DB(".\\data\\db\\highscores");
        st = new ScoreTimer(1000);
    }

    private void update() {
        if (inAir) {
            b1.act(b1.getxS(), b1.getyS());
            if (b1.getX() > 800 || b1.getY() > 600) {
                inAir = false;
            }
        }
        currLevel.updateLevel();
        lvlFinish = currLevel.isFinished();

        if (lvlFinish && lvlIndex < 9) {
            lvlFinish = false;
            lvlIndex++;
            currLevel = set.getLevel(lvlIndex);
        } else if (lvlFinish && lvlIndex == 9) {
            gameFinish = true;
            int score = Integer.parseInt(time);
            String name = JOptionPane.showInputDialog("Finished! Your score was " + score + "\nPlease enter your name:");
            try {
                scoreDB.updateTbl("INSERT INTO hs_table (Name, Date_of_Reading, Score) VALUES ('" + name + "', NOW(), " + score + ")");
            } catch (SQLException e) {
                System.out.println("Error in SQL statement");
            }
        }
        time = st.getTime();
    }

    private void checkCollisions() {
        if (inAir) {
            currLevel.doCollisions(b1);
        }
    }

    private void drawBuffer() {
        Graphics2D b = buffer.createGraphics();
        Painter.drawBackground(b);
        if (!gameFinish) {
            if (inAir) {
                Painter.drawBall(b, b1.getX(), b1.getY(), b1.getDiam());
            }
            Painter.drawBarrel(b, cX, cY, this);
            Painter.drawWheel(b, this);

            currLevel.drawLevel(b);

            Painter.drawTimer(b, time);
        } else {
            try {
                hsArr = HighScores.getScores(scoreDB);
            } catch (SQLException e) {
                System.out.println("Error in query");
            }
            Painter.drawHighscores(b, hsArr);
            lvlIndex++; //to make sure that the game does not still think it's running!
        }
        b.dispose();
    }

    private void drawScreen() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void startGame() {
        JOptionPane.showMessageDialog(this, "Welcome to Cannoneer!\n\nRemember to "
                + "read the README.TXT file \nin the game's directory for instructions."
                + "\n\nHave Fun!", "Cannoneer", JOptionPane.INFORMATION_MESSAGE);
        initialise();
        while (true) {
            update();
            checkCollisions();
            drawBuffer();
            drawScreen();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception caught");
            }
        }
    }
}
