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
public class Printer {
    public void drawLine(int x1, int y1, int x2, int y2){
        System.out.println("Line printed from (" + Integer.toString(x1) + ", " + Integer.toString(y1) + ") to (" + Integer.toString(x1) + ", " + Integer.toString(y1) + ")");
    }
    public void drawPixel(int x, int y){
        System.out.println("Pixel printed at (" + Integer.toString(x) + ", " + Integer.toString(y) + ")");
    }
    public void drawCircle(int x, int y, float r){
        System.out.println("Circle with radius " + Double.toString(r) + " printed at (" + Integer.toString(x) + ", " + Integer.toString(y) + ")");
    }
}
