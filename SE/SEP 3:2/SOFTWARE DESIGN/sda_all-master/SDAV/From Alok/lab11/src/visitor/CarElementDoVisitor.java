/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

/**
 *
 * @author Fant_QuarTZ
 */
class CarElementDoVisitor implements CarElementVisitor{
    public void visit(Wheel wheel){
        System.out.println("Kicking my "+wheel.getName() + " Wheel");
    }
    public void visit(Engine engine){
        System.out.println("Starting my engine");
    }
    public void visit(Body body){
        System.out.println("Waxing my body");
    }
    public void visit(Car car){
        System.out.println("Starting my car");
    }
}