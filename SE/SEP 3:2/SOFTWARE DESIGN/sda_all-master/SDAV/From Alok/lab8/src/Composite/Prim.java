package Composite;

import java.util.Iterator;

public abstract class Prim extends Object_Component {
	public abstract void render();
	public abstract float volume();
	public abstract Iterator createIterator();
        @Override
        public void add(Object_Component component) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
}
