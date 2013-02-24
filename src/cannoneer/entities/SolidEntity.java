package cannoneer.entities;

import java.awt.Rectangle;

public class SolidEntity {
    //SolidEntity is the superclass for the other two entities

    protected int x, y, width, height;

    public SolidEntity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isCollision(Ball b) { //will return 'true' if there's a collision between this and the Ball b
        if (this.getBounds().intersects(b.getBounds())) {
            return true;
        }
        return false;
    }
}
