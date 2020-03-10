/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author alok
 */
public class PancakeHouseMenuIterator implements Iterator{
    
    private ArrayList menuItems;
    private int index;
    
    public PancakeHouseMenuIterator(ArrayList menuItems){
        this.menuItems = menuItems;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index<this.menuItems.size()-1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Object next() {
        if(this.hasNext()){
            index++;
            return this.menuItems.get(index);
        }
        else{
            throw new NoSuchElementException();
        }
    }
    
}
