import java.util.*;


// EID 1
// EID 2

public class FairUnifanBathroom {   
	
	int ticketCount;
//	Stack<Fan> room;
//	Stack<Fan> wait;
	int size;
	
/*	class Fan{
		int ticketNumber;
		boolean isUT;
		
		public Fan(boolean isUT)
		{
			ticketNumber=ticketCount;
			this.isUT=isUT;
		}
	}
	

	public FairUnifanBathroom()
	{
		size=4;
		room = new Stack<Fan>();
		wait = new Stack<Fan>();
		ticketCount=0;
	}*/
  public synchronized void enterBathroomUT() {
    
  }
	
	public synchronized void enterBathroomOU() {
    // Called when a OU fan wants to enter bathroom
	}
	
	public synchronized void leaveBathroomUT() {

		
			
	}

	public synchronized void leaveBathroomOU() {

	}
}


