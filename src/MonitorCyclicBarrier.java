import java.util.ArrayList;

/*
 aek2267
 cms6788
 * 
 */

public class MonitorCyclicBarrier {
	
	private int position;
	ArrayList<Integer> buffer;
	private int parties;
	
	public MonitorCyclicBarrier(int parties) {
		buffer = new ArrayList<Integer>();
		position=parties-1;
		this.parties=parties;
	}
	
	public int await() throws InterruptedException {//???
           int index = 0;
		   index=position;
		   position--;
		   buffer.add(position);
           while(buffer.size()!=parties)
        	   buffer.wait();
           
           buffer.notifyAll();
           
          // you need to write this code
	    return index;
	}
}
