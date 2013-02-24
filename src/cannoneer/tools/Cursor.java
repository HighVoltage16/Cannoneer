package cannoneer.tools;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Cursor {

    public static void setDefault(javax.swing.JFrame comp) {
        Image cursorImage = new ImageIcon(".\\data\\sprites\\crosshair.png").getImage();
        Point hotspot = new Point(15, 15);
        String name = "crosshair";
        comp.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, hotspot, name));
    }
}
