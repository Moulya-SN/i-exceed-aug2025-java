class one extends Thread
  {
     public void run()
	{
	  for(var i=0;i<10;i++)
		{
	          System.out.println(Thread.currentThread().getName()+"-iteration"+i);
		 }
         }
   }
 
 
class threadyield
{
    public static void main(String asd[])
	{
	   one obj1=new one();
	   obj1.start();
	   obj1.yield();
           try {
	   for(var i=0;i<10;i++)		{
		   System.out.println("Main thread running");
		   Thread.sleep(1000);
		}
                       } catch (InterruptedException e) {
                System.out.println("Child thread interrupted: " + e.getMessage());
            }

	}  
}