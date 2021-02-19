import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


// aek2267
// cms6788

public class FairUnifanBathroom {   
	
	int ticketCount;
	int size;
	int UTinRoom;
	int OUinRoom;
	Object bathroom;
	ReentrantLock UTLock;
	

	public FairUnifanBathroom()
	{
		size=4;
		UTinRoom=0;
		bathroom = new Object();
		UTLock = new ReentrantLock();
		ticketCount=0;
		
	}
	
    public synchronized void enterBathroomUT() {
    	if(UTinRoom==size)
    		
    }
	
	public synchronized void enterBathroomOU() {
		if(OUinRoom==size)
	}
	
	public synchronized void leaveBathroomUT() {

		synchronized(bathroom)
		{
			if(UTinRoom!=0)
				UTinRoom--;
			bathroom.notifyAll();		
		}
			
	}

	public synchronized void leaveBathroomOU() {

		synchronized(bathroom)
		{
			if(OUinRoom!=0)
				OUinRoom--;
			bathroom.notifyAll();		
		}
	}
}


