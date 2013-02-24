package cannoneer.gameplay;

import cannoneer.entities.*;
import cannoneer.tools.Sounds;
import java.awt.Graphics2D;
import java.io.*;
import java.util.Scanner;

public class Level {

    private SolidEntity[] seArr = new SolidEntity[100];
    private BreakableEntity[] beArr = new BreakableEntity[100];
    private MovingEntity[] meArr = new MovingEntity[100];
    private Target t;
    private boolean finished = false;
    private int seSize = 0;
    private int beSize = 0;
    private int meSize = 0;

    public Level(String filename) {
        Scanner scFile;
        Scanner scLine;

        try {
            scFile = new Scanner(new File(".\\data\\levels\\" + filename + ".lvl")); //gets level data from lvl files
            while (scFile.hasNext()) {
                String line = scFile.nextLine();
                scLine = new Scanner(line).useDelimiter("#");

                String type = scLine.next();
                if (type.equalsIgnoreCase("S") || type.equalsIgnoreCase("B")) {
                    int x = scLine.nextInt();
                    int y = scLine.nextInt();
                    int w = scLine.nextInt();
                    int h = scLine.nextInt();
                    if (type.equalsIgnoreCase("S")) {
                        seArr[seSize] = new SolidEntity(x, y, w, h);
                        seSize++;
                    } else if (type.equalsIgnoreCase("B")) {
                        beArr[beSize] = new BreakableEntity(x, y, w, h);
                        beSize++;
                    }
                } else if (type.equalsIgnoreCase("M")) {
                    int x = scLine.nextInt();
                    int y = scLine.nextInt();
                    int w = scLine.nextInt();
                    int h = scLine.nextInt();
                    int xS = scLine.nextInt();
                    int yS = scLine.nextInt();
                    int xD = scLine.nextInt();
                    int yD = scLine.nextInt();
                    meArr[meSize] = new MovingEntity(x, y, w, h, xS, yS, xD, yD);
                    meSize++;
                } else if (type.equalsIgnoreCase("T")) {
                    int x = scLine.nextInt();
                    int y = scLine.nextInt();
                    t = new Target(x, y);
                } else {
                    System.out.println("Error in type in '" + line + "'.");
                }
                scLine.close();
            }
            scFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void drawLevel(Graphics2D g2d) {
        //draws all entities in the arrays
        for (int i = 0; i < seSize; i++) {
            Painter.drawSolidEntity(g2d, seArr[i].getBounds());
        }
        for (int i = 0; i < beSize; i++) {
            Painter.drawBreakableEntity(g2d, beArr[i].getBounds());
        }
        for (int i = 0; i < meSize; i++) {
            Painter.drawMovingEntity(g2d, meArr[i].getBounds());
        }

        Painter.drawTarget(g2d, t.getX(), t.getY(), new DrawPanel());
    }

    public void updateLevel() {
        //moves the moving entities
        for (int i = 0; i < meSize; i++) {
            meArr[i].act();
        }
    }

    public void doCollisions(Ball b) {
        //first checks for collisions, then identifies where a collision is, and destroys a breakable entity if necessary
        boolean col = false;
        for (int i = 0; i < seSize; i++) {
            if (!col) {
                col = seArr[i].isCollision(b);
            }
        }
        if (!col) {
            for (int i = 0; i < beSize; i++) {
                if (!col) {
                    col = beArr[i].isCollision(b);
                }
            }
        }
        if (!col) {
            for (int i = 0; i < meSize; i++) {
                if (!col) {
                    col = meArr[i].isCollision(b);
                }
            }
        }

        if (!col) {
            if (t.isCollision(b)) {
                col = true;
                finished = true;
            }
        }

        if (col) {
            for (int i = 0; i < beSize; i++) {
                if (beArr[i].isCollision(b)) {
                    beArr[i].destroy();
                }
            }

            Sounds.playExplosion();
            b.destroy(); //destroys the ball as well
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
