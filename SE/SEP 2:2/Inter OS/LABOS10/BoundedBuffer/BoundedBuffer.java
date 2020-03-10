public class BoundedBuffer
{
   private static final int   BUFFER_SIZE = 5;

   private int count; // number of items in the buffer
   private int in;   // points to the next free position in the buffer
   private int out;  // points to the next full position in the buffer
   private Object[] buffer;

   public BoundedBuffer()
   {
      // buffer is initially empty
      count = 0;
      in = 0;
      out = 0;
      buffer = new Object[BUFFER_SIZE];
   }
   //producer calls this method
   public void insert(Object item) {
      System.out.println("in insert count = " + count);
      
      while (count == BUFFER_SIZE); 
		buffer[in] = item;
      in = (in + 1) % BUFFER_SIZE;
		count++;
		if (count == BUFFER_SIZE)
			System.out.println("Producer Entered " + item + 
            " Buffer FULL");
		else
			System.out.println("Producer Entered " + item + 
            " Buffer Size = " +  count);
     
   }

   // consumer calls this method
   public Object remove() {
   	Object item;
      System.out.println("in remove count = " + count);
 		while (count == 0); 
		item = buffer[out];
  		out = (out + 1) % BUFFER_SIZE;
      count--;
  		if (count == 0)
		   System.out.println("Consumer Consumed " + item + 
         " Buffer EMPTY");
		else
			System.out.println("Consumer Consumed " + item + 
            " Buffer Size = " + count);
      return item;
   }
}
