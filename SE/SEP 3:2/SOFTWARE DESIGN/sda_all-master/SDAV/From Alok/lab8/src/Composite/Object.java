/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author alok
 */
public class Object extends Object_Component{
    private ArrayList<Object_Component> components;
    
    public Object(){
        this.components = new ArrayList<Object_Component>();
    }
    
    public void add(Object_Component component){
        components.add(component);
    }
    
    public void render(){
        for(Object_Component component:components){
            component.render();
        }
    }
    
    public float volume(){
        float vol = 0;
        for(Object_Component component:components){
            vol+=component.volume();
        }
        return vol;
    }

    public Iterator createIterator() {
        return new CompositeIterator(components.iterator());
    }
    
}
