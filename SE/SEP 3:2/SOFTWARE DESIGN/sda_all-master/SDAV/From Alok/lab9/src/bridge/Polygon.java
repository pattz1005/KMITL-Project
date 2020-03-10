/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import java.util.ArrayList;

/**
 *
 * @author alok
 */
public class Polygon extends Shape{
    int[][] points;
    public Polygon(int[][] points){
        this.points = points;
    }
    @Override
    public void draw() {
        int i = 0;
        while(i<points.length){
            if(i==points.length-1){
                this.drawingService.drawLine(points[i][0], points[i][1], points[0][0], points[0][1]);
            }
            else{
                this.drawingService.drawLine(points[i][0], points[i][1], points[i+1][0], points[i+1][1]);
            }
            i++;
        }
    }
    public void setAttributes(int[][] points){
        this.points = points;
    }
}
