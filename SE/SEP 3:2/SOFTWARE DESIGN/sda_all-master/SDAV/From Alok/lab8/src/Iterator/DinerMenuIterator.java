/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author alok
 */
public class DinerMenuIterator implements Iterator{
    private MenuItem[] menuItems;
    private int index;
    
    public DinerMenuIterator(MenuItem[] menuItems){
        this.menuItems = menuItems;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index<this.menuItems.length-1){
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
            return this.menuItems[index];
        }
        else{
            throw new NoSuchElementException();
        }
    }
}
