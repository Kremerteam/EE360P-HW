
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// aek2267
// cms6788

public class PriorityQueue {

	ReentrantLock lock;
	int maxSize;
	int inQ;
	int outQ;
	String[] queue;
	//int[] priority;
	Condition notEmpty; 
	Condition notFull;
	Stack<String> stack;
	Stack<Integer> Priorities;
	
	public PriorityQueue(int maxSize) {
        // Creates a Priority queue with maximum allowed size as capacity
		lock = new ReentrantLock();
		this.maxSize = maxSize;
		notEmpty = lock.newCondition();
		notFull = lock.newCondition();
		queue = new String[Integer.MAX_VALUE];
		//priority = new int[Integer.MAX_VALUE];
		stack= new Stack<String>();
		Priorities= new Stack<Integer>();

	}

	public int add(String name, int priority) {
        // Adds the name with its priority to this queue.
        // Returns the current position in the list where the name was inserted;
        // otherwise, returns -1 if the name is already present in the list.
        // This method blocks when the list is full.
		
		//Priority 9 at end and priority 0 at beginning
		if(stack.contains(name))
			return -1;
		else if(Priorities.contains(priority))
		{
			int index = Priorities.indexOf(priority);
			stack.insertElementAt(name, index);
			Priorities.insertElementAt(priority, index);
			return index;
		}
		else {
			for(int i=0;i<Priorities.size();i++)
			{
				if(Priorities.get(i)>=priority || i==Priorities.size()-1)
				{
					int index=i;
					stack.insertElementAt(name, i);
					Priorities.insertElementAt(priority, i);
					return index;
				}
			}
			
		}
		return -2;
	}

	public int search(String name) {
        // Returns the position of the name in the list;
        // otherwise, returns -1 if the name is not found.
		
		if(stack.contains(name))
			return stack.indexOf(name);
		else
			return -1;
	}

	public String getFirst() throws InterruptedException {
        // Retrieves and removes the name with the highest priority in the list,
        // or blocks the thread if the list is empty.

		try {
			while(inQ==0)
				notEmpty.await();
			
			return stack.pop();
		} finally {
			
		}
		
	}
}

