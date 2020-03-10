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
public class Lab9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] points = {{1, 2}, {3, 4}, {4, 5}};
        Shape polygon = new Polygon(points);
        Shape circle = new Circle(1, 2, 3);
        DrawingService printer = new WrapPrinter();
        DrawingService writer = new WrapXML();
        DrawingService screen = new WrapScreen();
        polygon.setDrawingService(printer);
        polygon.draw();
        polygon.setDrawingService(writer);
        polygon.draw();
        circle.setDrawingService(screen);
        circle.draw();
    }
    
}
