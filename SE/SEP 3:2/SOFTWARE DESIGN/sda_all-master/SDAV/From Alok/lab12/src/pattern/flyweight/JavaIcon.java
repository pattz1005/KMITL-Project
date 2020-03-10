/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.flyweight;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class JavaIcon extends AbstractIcon {

    private final int H = 48;
    private ImageIcon icon;

    JavaIcon(){
        URL iconURL =
	    ClassLoader.getSystemResource("icons/source_java.png");
        if (iconURL != null) {
	    icon = new ImageIcon(iconURL);
	} else {
	    System.out.println("Icon icons/source_java.png not found");
	}
    }

    @Override
    public void draw(Graphics g, int tx, int ty, String name, boolean selected) {
        icon.paintIcon(null, g, tx, ty);
        g.drawString(name, tx, ty + H + 15);
    }
}

