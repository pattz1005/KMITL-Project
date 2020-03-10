package Iterator_Comp;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class test_prims {

	public static void main(String[] args) {
        Cube cube1 = new Cube(1.0f,1.0f,1.0f);
        Cube cube2 = new Cube(1.0f,1.0f,1.0f);
        Sphere sphere1 = new Sphere(4.0f);
       // Cylinder cylinder1 = new Cylinder();
        
        //Initialize three composite prims
        Composite pcom1 = new Composite();
        Composite pcom2 = new Composite();
          
         pcom1.add(cube1);
         pcom1.add(cube2);
  
         pcom2.add(pcom1);
         pcom2.add(sphere1);

     
         pcom2.render();
         System.out.println(pcom2.volume());
        
         Iterator iterator = pcom2.createIterator();
         while (iterator.hasNext()) {
         	((Prim) iterator.next()).render();
           }
        }
}

 