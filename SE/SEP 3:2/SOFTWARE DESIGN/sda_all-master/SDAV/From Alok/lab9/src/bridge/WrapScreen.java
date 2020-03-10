/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

/**
 *
 * @author alok
 */
public class WrapScreen implements DrawingService{
    Screen screen = new Screen();
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        screen.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawPixel(int x, int y) {
        screen.drawPixel(x, y);
    }

    @Override
    public void drawCircle(int x, int y, float r) {
        screen.drawCircle(x, y, r);
    }
    
}
