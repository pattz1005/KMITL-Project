import java.util.*;

public class Consumer implements Runnable
{
   private  BoundedBuffer buffer;
   public Consumer(BoundedBuffer b) {
      buffer = b;
   }

   public void run()
   {
 	   int number  = 0;
      //Random rand = new Random();
      for(int i = 1; i <= 10000; i++) { 
         
        /* try {		
			   Thread.sleep(rand.nextInt(3) * 1000);
		   }
	      catch(InterruptedException e) {
		   } */
         number = (Integer) buffer.remove();
         
		}
   }

   
}


