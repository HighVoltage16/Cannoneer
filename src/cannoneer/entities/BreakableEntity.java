package cannoneer.entities;

public class BreakableEntity extends SolidEntity {

    public BreakableEntity(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void destroy() { //this is the only place where the BreakableEntity differs from the SolidEntity
        //the method doesn't dispose of the Object, just make its size 0
        this.width = 0;
        this.height = 0;
    }
}
