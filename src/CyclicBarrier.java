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
	
	public CyclicBarrier(int parties) {
		
		this.parties = parties;
		mutex= new Semaphore(0,true);
		position=parties-1;
		barrier1= new Semaphore(0,true);
		barrier2= new Semaphore(0,true);
	}
	
	public int await() throws InterruptedException {

           int index = position;
           position--;
           mutex.release();
           mutex.acquire(parties);
           mutex.release(parties);
           
           
           //Reset Barrier
     /*      released.
           released.release();
           released.acquire(parties);
           mutex.drainPermits();
           released.release(parties);*/
           //mutex.drainPermits();*/
	       return index;
	}
}
