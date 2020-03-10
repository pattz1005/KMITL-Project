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
public class WrapXML implements DrawingService{
    XML_Writer writer = new XML_Writer();
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        writer.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawPixel(int x, int y) {
        writer.drawPixel(x, y);
    }

    @Override
    public void drawCircle(int x, int y, float r) {
        writer.drawCircle(x, y, r);
    }
}
