package e1.flyweight;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class JavaIcon extends AbstractIcon {

    private final int H = 48;
    private ImageIcon icon;

    JavaIcon() {
        URL iconURL = ClassLoader.getSystemResource("./images/source_java.png");
        if (iconURL != null) {
            icon = new ImageIcon(iconURL);
        } else {
            System.out.println("Icon images/source_java.png not found");
        }
    }

    public void draw(Graphics g, int tx, int ty, String name, boolean sel) {
        g.clearRect(tx, ty, icon.getIconWidth(), icon.getIconHeight());
        icon.paintIcon(null, g, tx, ty);
        g.drawString(name, tx, ty + H + 15);
    }
}
