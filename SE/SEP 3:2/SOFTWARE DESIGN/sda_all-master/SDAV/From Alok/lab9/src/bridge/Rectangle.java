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
public class Rectangle extends Shape{
    int x1, y1, x2, y2, x3, y3, x4, y4;
    public Rectangle(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }
    @Override
    public void draw() {
        this.drawingService.drawLine(x1, y1, x2, y2);
        this.drawingService.drawLine(x3, y3, x1, y1);
        this.drawingService.drawLine(x3, y3, x4, y4);
        this.drawingService.drawLine(x4, y4, x2, y2);
    }
    public void setParameters(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }
}
