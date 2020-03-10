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
public class WrapPrinter implements DrawingService{
    Printer printer = new Printer();
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        printer.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawPixel(int x, int y) {
        printer.drawPixel(x, y);
    }

    @Override
    public void drawCircle(int x, int y, float r) {
        printer.drawCircle(x, y, r);
    }
    
}
