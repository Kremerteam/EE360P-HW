import java.util.*;


// aek2267
// cms6788

public class FairUnifanBathroom {   
	
	int ticketCount;
	int size;
	int UTinRoom;
	int OUinRoom;
	int currentTicket;
	Object bathroom;
	

	public FairUnifanBathroom()
	{
		size=4;
		UTinRoom=0;
		bathroom = new Object();
		ticketCount=0;
		currentTicket=0;
	}
	
    public void enterBathroomUT() {
    	
    	synchronized(bathroom) {
    		int ticketNumber = ticketCount;
    		ticketCount++;
    		
    		while(UTinRoom==size || OUinRoom>0 || currentTicket!=ticketNumber) {
				try {
					System.out.println("UT waiting");
					bathroom.wait();
					System.out.println("UT not waiting");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}

    		//Now enters
    		currentTicket++;
    		UTinRoom++;
    		System.out.println(UTinRoom+ "UT");
    		
    		
    	}
    }
	
	public void enterBathroomOU() {
		
    	synchronized(bathroom) {
    		int ticketNumber = ticketCount;
    		ticketCount++;
    		
    		while(OUinRoom==size || UTinRoom>0 || (UTinRoom==0&&OUinRoom==0&&currentTicket!=ticketNumber)) {
				try {
					System.out.println("OU waiting");
					bathroom.wait();
					System.out.println("OU not waiting");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    		//Now enters
    		currentTicket++;
    		OUinRoom++;
    		System.out.println(OUinRoom+ "OU");
    		
    		
    	}
	}
	
	public void leaveBathroomUT() {

		synchronized(bathroom)
		{
			if(UTinRoom!=0)
				UTinRoom--;
			bathroom.notifyAll();
			System.out.println("UT notify");
		}
			
	}

	public void leaveBathroomOU() {

		synchronized(bathroom)
		{
			if(OUinRoom!=0)
				OUinRoom--;
			bathroom.notifyAll();
			System.out.println("OU notify");
		}
	}
}


