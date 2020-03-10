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
public class Circle extends Shape{
    float radius;
    int x;
    int y;
    public Circle(int x, int y, float r){
        this.radius = r;
        this.x = x;
        this.y = y;
    }
    @Override
    public void draw() {
        this.drawingService.drawCircle(x, y, radius);
    }
    public void setParameters(int x, int y, float r){
        this.radius = r;
        this.x = x;
        this.y = y;
    }
}
