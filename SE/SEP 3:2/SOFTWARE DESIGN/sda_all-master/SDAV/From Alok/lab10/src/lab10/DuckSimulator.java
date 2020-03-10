/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

import java.util.Iterator;

/**
 *
 * @author alok
 */
public class DuckSimulator {
    public Quackable makeDuck(AbstractDuckFactory factory){
        return factory.createDuckCall();
    }
}
