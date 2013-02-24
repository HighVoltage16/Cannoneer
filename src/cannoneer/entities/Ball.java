package cannoneer.entities;

import java.awt.Rectangle;

public class Ball {

    private double x, y, xS, yS;
    //it is advisable to make the values doubles, because GRAVITY is a double
    private int diam;
    private final double GRAVITY = 0.3;

    public Ball(int x, int y, double xS, double yS) {
        this.x = x;
        this.y = y;
        diam = 15; //diameter is constant
        this.xS = xS;
        this.yS = yS;
    }

    public int getDiam() {
        return diam;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds() { //although a ball is a circle, it still takes a Rectangle as its bounds
        return new Rectangle((int) getX(), (int) getY(), diam, diam);
    }

    public double getxS() {
        return xS;
    }

    public double getyS() {
        return yS;
    }

    public void act(double xSpeed, double ySpeed) { //this method makes the ball move
        x = x + xSpeed;
        y = y + ySpeed + GRAVITY;
        yS = yS + GRAVITY;
    }

    public void destroy() {
        //this method doesn't "destroy" the ball, it merely places it in a position which can't be seen
        //it creates the illusion of it being destroyed
        this.x = -20;
        this.y = 620;
        this.xS = 0;
        this.yS = 0;
    }
}
