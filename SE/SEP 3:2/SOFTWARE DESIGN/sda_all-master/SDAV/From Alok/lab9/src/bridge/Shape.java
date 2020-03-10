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
public abstract class Shape {
    DrawingService drawingService;
    public abstract void draw();
    public void setDrawingService(DrawingService drawingService){
        this.drawingService = drawingService;
    }
}
