/*
 aek2267
 cms6788
 * 
 */
import java.util.concurrent.Semaphore; // for implementation using Semaphores

public class CyclicBarrier {
	
	private int parties;
	private Semaphore mutex;
	private int position;
	private Semaphore barrier1;
	private Semaphore barrier2;
	private int count;
	
	public CyclicBarrier(int parties) {
		
		this.parties = parties;
		mutex= new Semaphore(0,true);
		position=parties-1;
		barrier1= new Semaphore(1,true);
		barrier2= new Semaphore(0,true);
		count=0;
	}
	
	public int await() throws InterruptedException {

		 int index = position;
		 barrier1.acquire();
         position--;
         barrier1.release();
         
         if(index==0) {
        	 barrier2.release();
        	 position = parties-1;
         }
         barrier2.acquire();
    	 count++;
         if(count!=parties)
         {
         barrier2.release();
         }
         else
        	 count=0;

		 
		 return index;
		

	}
}
