package cannoneer.gameplay;

import cannoneer.highscores.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class Painter {

    //all of the follwoing Objects are static, to prevent having to load them over and over again
    private static Font f1 = new Font("Arial Black", Font.BOLD, 35);
    private static Font fn2 = new Font("JSL Ancient", Font.PLAIN, 20);
    private static Font fn2big = new Font("JSL Ancient", Font.BOLD, 25);
    private static Font fn = new Font("JSL Ancient", Font.BOLD, 30);
    private static ImageIcon wheelIcon = new ImageIcon(".\\data\\sprites\\wheel.png");
    private static ImageIcon barrelIcon = new ImageIcon(".\\data\\sprites\\barrel.png");
    private static ImageIcon targetIcon = new ImageIcon(".\\data\\sprites\\target.png");
    private static Image wheel = wheelIcon.getImage();
    private static Image barrel = barrelIcon.getImage();
    private static Image target = targetIcon.getImage();

    public static void drawBackground(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 800, 600);
    }

    public static void drawTimer(Graphics2D g2d, String time) {
        g2d.setFont(f1);
        g2d.setColor(Color.yellow);
        g2d.drawString(time, 650, 40);
    }

    public static void drawWheel(Graphics2D g2d, ImageObserver obs) {
        g2d.drawImage(wheel, 10, 540, obs);
    }

    public static void drawBarrel(Graphics2D g2d, int mX, int mY, ImageObserver obs) {
        //maths which determines the angle of the barrel, and then rotates the Graphics2D object to suit it
        double ang = Math.atan((double) mX / (600 - mY));
        g2d.translate(27, 547);
        g2d.rotate(ang);

        g2d.drawImage(barrel, -13, -88, obs);

        //MUST CHANGE THE Graphics2D OBJECT BACK!!
        g2d.rotate(-ang);
        g2d.translate(-27, -547);
    }

    public static void drawBall(Graphics2D g2d, double x, double y, int d) {
        g2d.setColor(Color.darkGray);
        g2d.fillOval((int) x, (int) y, d, d);
    }

    public static void drawSolidEntity(Graphics2D g2d, Rectangle r) {
        g2d.setColor(Color.blue);
        g2d.fill(r);
        g2d.setColor(Color.darkGray);
        g2d.draw(r);
    }

    public static void drawBreakableEntity(Graphics2D g2d, Rectangle r) {
        g2d.setColor(Color.green);
        g2d.fill(r);
        g2d.setColor(Color.black);
        g2d.draw(r);
    }

    public static void drawMovingEntity(Graphics2D g2d, Rectangle r) {
        g2d.setColor(Color.magenta);
        g2d.fill(r);
        g2d.setColor(Color.darkGray);
        g2d.draw(r);
    }

    public static void drawTarget(Graphics2D g2d, int x, int y, ImageObserver obs) {
        g2d.drawImage(target, x, y, obs);
    }

    public static void drawHighscores(Graphics2D g2d, Score[] hs) {
        //this takes time, but works well
        g2d.setColor(Color.yellow);
        g2d.setFont(fn);
        g2d.drawString("High Scores", 50, 50);


        g2d.setFont(fn2);

        g2d.setColor(Color.green);
        g2d.drawString("Name", 50, 100);
        g2d.drawString("Date", 400, 100);
        g2d.drawString("Score", 650, 100);

        g2d.setColor(Color.red);

        for (int i = 0; i < 10; i++) {
            int y = 150 + (i * 40);

            if (i < 3) {
                g2d.setFont(fn2big);
            } else {
                g2d.setFont(fn2);
            }
            g2d.drawString(i + 1 + ". " + hs[i].getName(), 50, y);
            g2d.drawString(hs[i].getDate(), 400, y);
            g2d.drawString(hs[i].getScore() + "", 650, y);
        }

        g2d.setColor(Color.yellow);
        g2d.drawString("Thanks for playing!", 624, 566);
    }
}
