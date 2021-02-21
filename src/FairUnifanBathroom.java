import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


// aek2267
// cms6788

public class FairUnifanBathroom {   
	
	int ticketCount;
	int size;
	int UTinRoom;
	int OUinRoom;
	int currentTicket;
	Object bathroom;
	ReentrantLock UTLock;
	

	public FairUnifanBathroom()
	{
		size=4;
		UTinRoom=0;
		bathroom = new Object();
		UTLock = new ReentrantLock();
		ticketCount=0;
		currentTicket=0;
	}
	
    public synchronized void enterBathroomUT() {
    	
    	synchronized(bathroom) {
    		int ticketNumber = ticketCount;
    		ticketCount++;
    		
    		if(UTinRoom==size || OUinRoom>0 || currentTicket!=ticketNumber) {
				try {
					bathroom.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    		//Now enters
    		currentTicket++;
    		UTinRoom++;
    		
    		
    	}
    }
	
	public synchronized void enterBathroomOU() {
    	synchronized(bathroom) {
    		int ticketNumber = ticketCount;
    		ticketCount++;
    		
    		if(OUinRoom==size || UTinRoom>0 || currentTicket!=ticketNumber) {
				try {
					bathroom.wait(); //Waits until turn
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    		//Now enters
    		currentTicket++;
    		OUinRoom++;
    		
    		
    	}
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


