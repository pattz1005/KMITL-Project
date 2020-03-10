package src;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class TextIcon extends AbstractIcon {

    private final int H = 48;
    private ImageIcon icon;

    TextIcon() {
        URL iconURL = ClassLoader.getSystemResource("./images/txt.png");
        if (iconURL != null) {
            icon = new ImageIcon(iconURL);
        } else {
            System.out.println("Icon images/txt.png not found");
        }
    }

    public void draw(Graphics g, int tx, int ty, String name, boolean sel) {
        g.clearRect(tx, ty, icon.getIconWidth(), icon.getIconHeight());
        icon.paintIcon(null, g, tx, ty);
                g.setColor(Color.WHITE);

        g.drawString(name, tx, ty + H + 15);
    }
}
