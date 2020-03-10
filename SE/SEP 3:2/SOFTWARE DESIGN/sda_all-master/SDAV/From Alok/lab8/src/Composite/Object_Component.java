/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composite;

import java.util.Iterator;

/**
 *
 * @author alok
 */
public abstract class Object_Component {
    
    public abstract void add(Object_Component component);
    public abstract float volume();
    public abstract void render();
    public abstract Iterator createIterator();
}
