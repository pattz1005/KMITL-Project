import java.util.*;

public class Producer implements Runnable
{
	private int number;
   private  BoundedBuffer buffer;
   public Producer(BoundedBuffer b) {
      buffer = b;
      number = 1;
   }
   public void run()
   {
         //Random rand = new Random();
         for(int i = 1; i <= 10000; i++) {
      	  	
           /* try {		
			   			Thread.sleep(rand.nextInt(3) * 1000);
		      }
		      catch(InterruptedException e) {
		      }*/
         
         	buffer.insert(number++);
            
         }
   }

   
}
