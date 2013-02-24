package cannoneer.entities;

public class Target extends SolidEntity {
    //a Target object is a subclass of the SolidEntity class, but it is NOT a rectangular shape
    //it is a sprite of a target image

    public Target(int x, int y) {
        super(x, y, 50, 50);
    }
}
