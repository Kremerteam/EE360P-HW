import java.util.ArrayList;

/*
 aek2267
 cms6788
 * 
 */

public class MonitorCyclicBarrier {
	
	private int position;
	Object monitor;
	private int parties;
	
	public MonitorCyclicBarrier(int parties) {
		monitor = new Object();
		position=parties-1;
		this.parties=parties;
	}
	
	public int await() throws InterruptedException {
		synchronized(monitor) {
           int index =position;
		   position--;
		   if(index==0)
		   {
			   monitor.notifyAll();
			   position=parties-1;
		   }
		   else
			   monitor.wait();
     
	    return index;
		}
		
	}
}
