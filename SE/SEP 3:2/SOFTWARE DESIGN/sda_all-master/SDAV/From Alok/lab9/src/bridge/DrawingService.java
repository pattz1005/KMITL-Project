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
public interface DrawingService {
    public void drawLine(int x1, int y1, int x2, int y2);
    public void drawPixel(int x, int y);
    public void drawCircle(int x, int y, float r);
}
