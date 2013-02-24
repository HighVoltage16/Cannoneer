package cannoneer.entities;

public class MovingEntity extends SolidEntity {

    private int xS, yS, xDist, yDist, xDir, yDir, xInit, yInit;
    private final int RIGHT = 0, LEFT = 1, DOWN = 0, UP = 1; //these identifiers make the directions easier to use

    public MovingEntity(int x, int y, int w, int h, int xS, int yS, int xD, int yD) {
        super(x, y, w, h);
        this.xS = xS;
        this.yS = yS;
        this.xDist = xD;
        this.yDist = yD;

        xInit = x;
        yInit = y;

        xDir = RIGHT;
        yDir = DOWN;
    }

    public void act() { //this method will move the Entity from place to place repeatedly
        if (x > (xInit + xDist)) {
            xDir = LEFT;
        } else if (x < xInit) {
            xDir = RIGHT;
        }

        if (y > (yInit + yDist)) {
            yDir = UP;
        } else if (y < yInit) {
            yDir = DOWN;
        }

        if (xDir == RIGHT) {
            x += xS;
        } else if (xDir == LEFT) {
            x -= xS;
        }

        if (yDir == DOWN) {
            y += yS;
        } else if (yDir == UP) {
            y -= yS;
        }
    }
}
