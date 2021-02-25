
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
	//String[] queue;
	//int[] priority;
	Condition notEmpty; 
	Condition notFull;
	Vector<String> queue;
	Vector<Integer> Priorities;
	
	public PriorityQueue(int maxSize) {
        // Creates a Priority queue with maximum allowed size as capacity
		reLock = new ReentrantLock();
		this.maxSize = maxSize;
		notEmpty = reLock.newCondition();
		notFull = reLock.newCondition();
		queue= new Vector<String>();
		queue.setSize(maxSize);
		Priorities= new Vector<Integer>();
		Priorities.setSize(maxSize);
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
			int index = Priorities.lastIndexOf(priority)+1; //TODO check if full!!
			queue.insertElementAt(name, index);
			queue.setSize(maxSize);
			Priorities.insertElementAt(priority, index);
			Priorities.setSize(maxSize);
			inQ++;
			return index;
		}
		else if(inQ==0){
			queue.set(0, name);
			Priorities.set(0,priority);
			inQ++;
			return 0;
		}
		else {
			for(int i=0;i<=inQ;i++)
			{
				if(i==inQ || Priorities.get(i)<priority)
				{
					int index=i;
					queue.insertElementAt(name, i);
					queue.setSize(maxSize);
					Priorities.insertElementAt(priority, i);
					Priorities.setSize(maxSize);
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
			Priorities.setSize(maxSize);
			inQ--;
			String result = queue.remove(0);
			queue.setSize(maxSize);
			return result;
		} finally {

			notFull.signalAll();
			reLock.unlock();
		}
		
	}
}

