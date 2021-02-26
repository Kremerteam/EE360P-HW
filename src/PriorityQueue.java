
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// aek2267
// cms6788

public class PriorityQueue {

	ReentrantLock reLock;
	int maxSize;
	int inQ;
	int outQ;
	Condition notEmpty; 
	Condition notFull;
	LinkedList<String> queue;
	LinkedList<Integer> Priorities;
	
	public PriorityQueue(int maxSize) {
        // Creates a Priority queue with maximum allowed size as capacity
		reLock = new ReentrantLock();
		this.maxSize = maxSize;
		notEmpty = reLock.newCondition();
		notFull = reLock.newCondition();
		queue= new LinkedList<String>();
		Priorities= new LinkedList<Integer>();
		inQ=0;
		
	}

	public int add(String name, int priority) {
		// Adds the name with its priority to this queue.
		// Returns the current position in the list where the name was inserted;
		// otherwise, returns -1 if the name is already present in the list.
		// This method blocks when the list is full.
		reLock.lock();
		try {
			if(inQ == maxSize) {
				notFull.await();
			}

		//Priority 0 at end and priority 9 at beginning
		if(queue.contains(name))
			return -1;
		else if(Priorities.contains(priority))
		{
			int index = Priorities.lastIndexOf(priority)+1;
			queue.add(index, name);
			Priorities.add(index, priority);
			inQ++;
			return index;
		}
		else if(inQ==0){
			queue.add(name);
			Priorities.add(priority);
			inQ++;
			return 0;
		}
		else {
			for(int i=0;i<=inQ;i++)
			{
				if(i==inQ || Priorities.get(i)<priority)
				{
					int index=i;
					queue.add(i, name);
					Priorities.add(i, priority);
					inQ++;
					return index;
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			notEmpty.signalAll();
			reLock.unlock();
		}

		return -2;
	}

	public int search(String name) {
        // Returns the position of the name in the list;
        // otherwise, returns -1 if the name is not found.
		reLock.lock();
		try{
			if(queue.contains(name))
				return queue.indexOf(name);
			else
				return -1;
		}finally{
			reLock.unlock();
		}

	}

	public String getFirst() throws InterruptedException {
        // Retrieves and removes the name with the highest priority in the list,
        // or blocks the thread if the list is empty.
		reLock.lock();
		try {
			if(inQ==0)
				notEmpty.await();
			Priorities.remove(0);
			inQ--;
			String result = queue.remove(0);
			return result;
		} finally {

			notFull.signalAll();
			reLock.unlock();
		}
		
	}
}

