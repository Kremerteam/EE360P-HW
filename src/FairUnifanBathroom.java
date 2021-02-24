import javax.security.auth.login.Configuration;
import java.util.*;
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
	
    public void enterBathroomUT() {

		int ticketNumber = ticketCount;

		while(currentTicket != ticketNumber) {
			//System.out.println("ticket - "+ ticketNumber);
			//System.out.println("curticket - "+ currentTicket);
		}
		ticketCount++;
		monitorLock.lock();
		//System.out.println("ticket - "+ ticketNumber);
		//System.out.println("curticket - "+ currentTicket);

		try {
			while (inRoom == size) {
				notFull.await();

			}

			if (inRoom == 0) {
				inRoom++;
				//UToccupied.signal();
				UT = true;
				currentTicket++;
				System.out.println(inRoom + "UT");
				notFull.signalAll();
			}
			else if (inRoom < 3) {
				if(UT == true){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + "UT");
					notFull.signalAll();
				}else{
					empty.await();
					UT = true;
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + "UT");
				}
				//UToccupied.await();

			} else if(inRoom==3){
				//UToccupied.await();
				if(UT == true){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + "UT");
				}else{
					empty.await();
					inRoom++;
					UT = true;
					currentTicket++;
					System.out.println(inRoom + "UT");
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitorLock.unlock();
		}


	}


	public void enterBathroomOU() {

		int ticketNumber = ticketCount;

		while(currentTicket != ticketNumber) {
			//System.out.println("ticket - "+ ticketNumber);
			//System.out.println("curticket - "+ currentTicket);
		}
		ticketCount++;
		monitorLock.lock();

		//System.out.println("ticket - "+ ticketNumber);
		//System.out.println("curticket - "+ currentTicket);

		try {
			while (inRoom == size) {
				notFull.await();
			}

			if (inRoom == 0) {
				inRoom++;
				UT = false;
				//UToccupied.signal();
				currentTicket++;
				System.out.println(inRoom + "OU");
				notFull.signalAll();
			}
			else if (inRoom < 3) {
				if(UT == false){
					inRoom++;
					currentTicket++;
					System.out.println(inRoom + "OU");
					notFull.signalAll();
				}else{
					empty.await();
					inRoom++;
					UT = false;
					currentTicket++;
					System.out.println(inRoom + "OU");
					notFull.signalAll();
				}
				//UToccupied.await();

			} else if(inRoom==3){
				//UToccupied.await();
				if(UT == false){
					inRoom++;

					currentTicket++;
					System.out.println(inRoom + "OU");
					notFull.signalAll();
				}else{
					empty.await();
					inRoom++;
					UT = false;

					currentTicket++;
					System.out.println(inRoom + "OU");
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
			System.out.println("OU Leaving bathroom");
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
			//System.out.println(inRoom+ "UT");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			System.out.println("UT Leaving bathroom");
			monitorLock.unlock();
		}
	}

}


