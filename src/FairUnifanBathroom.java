import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// aek2267
// cms6788

public class FairUnifanBathroom {   
	
	int ticketCount;
	int size;
	int inRoom;
	int OUinRoom;
	int currentTicket;
	Object bathroom;
	final ReentrantLock monitorLock = new ReentrantLock();
	final Condition empty = monitorLock.newCondition();
	final Condition notFull = monitorLock.newCondition();
	final Condition UToccupied = monitorLock.newCondition();
	final Condition OUoccupied = monitorLock.newCondition();
	boolean UT;
	public FairUnifanBathroom()
	{
		size=4;
		inRoom=0;
		bathroom = new Object();
		ticketCount=0;
		currentTicket=0;
	}
	
    public synchronized void enterBathroomUT() {
    	System.out.println("A UT fan wants to enter bathroom");
		int ticketNumber = ticketCount;

		while(currentTicket != ticketNumber) {

		}
		ticketCount++;
		monitorLock.lock();

		try {
			while (inRoom == size) {
				notFull.await();

			}

			if (inRoom == 0) {
				inRoom++;
				UT = true;
				currentTicket++;
				System.out.println(inRoom + " UT fans in bathroom");
				notFull.signalAll();
			}
			else if (inRoom < 3) {
				if(UT == true){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + " UT fans in bathroom");
					notFull.signalAll();
				}else{
					empty.await();
					UT = true;
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + " UT fans in bathroom");
				}

			} else if(inRoom==3){
				if(UT == true){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + " UT fans in bathroom");
				}else{
					empty.await();
					inRoom++;
					UT = true;
					currentTicket++;
					System.out.println(inRoom + " UT fans in bathroom");
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitorLock.unlock();
		}


	}


	public synchronized void enterBathroomOU() {
		System.out.println("A OU fan wants to enter bathroom");
		int ticketNumber = ticketCount;

		while(currentTicket != ticketNumber) {

		}
		ticketCount++;
		monitorLock.lock();

		try {
			while (inRoom == size) {
				notFull.await();
			}

			if (inRoom == 0) {
				inRoom++;
				UT = false;
				currentTicket++;
				System.out.println(inRoom + " OU fans in bathroom");
				notFull.signalAll();
			}
			else if (inRoom < 3) {
				if(UT == false){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + " OU fans in bathroom");
					notFull.signalAll();
				}else{
					empty.await();
					inRoom++;
					UT = false;
					currentTicket++;
					System.out.println(inRoom + " OU fans in bathroom");
					notFull.signalAll();
				}

			} else if(inRoom==3){
				if(UT == false){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + " OU fans in bathroom");
					notFull.signalAll();
				}else{
					empty.await();
					inRoom++;
					UT = false;
					currentTicket++;
					System.out.println(inRoom + " OU fans in bathroom");
					notFull.signalAll();
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitorLock.unlock();
		}


	}

	public void leaveBathroomOU() {
		monitorLock.lock();
		try {
			if(inRoom == 1){
				inRoom--;
				empty.signalAll();
				notFull.signalAll();
			}else{
				inRoom--;
				notFull.signalAll();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			System.out.println("A OU fan is leaving bathroom");
			monitorLock.unlock();
		}
	}
	public void leaveBathroomUT() {
		monitorLock.lock();
		try {
			if(inRoom == 1){
				inRoom--;
				empty.signalAll();
				notFull.signalAll();

			}else{
				inRoom--;
				notFull.signalAll();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			System.out.println("A UT fan is leaving bathroom");
			monitorLock.unlock();
		}
	}

}


