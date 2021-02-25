import java.util.concurrent.TimeUnit;

public class PriorityQTest implements Runnable {
	final static int SIZE = 10;
	final static int ROUND = 5;
	
	PriorityQueue pQueue;
	
	public PriorityQTest(PriorityQueue p) {
		this.pQueue = p;
	}
	

	public void run() {
		try {
			double random = Math.random();
			if(random < .2){
				pQueue.add("Alex", 9);
				System.out.println(pQueue.queue.toString());
				pQueue.search("Jameson");
				pQueue.getFirst();
			}else if(random > .2 && random < .5){
				pQueue.add("Connor", 8);
				System.out.println(pQueue.queue.toString());
				pQueue.search("Canga");
				pQueue.getFirst();
			}else if(random > .5 && random < .8){
				pQueue.add("Ahbi", 2);
				System.out.println(pQueue.queue.toString());
				pQueue.search("Connor");
				pQueue.getFirst();
			}else{
				pQueue.add("Canga", 5);
				System.out.println(pQueue.queue.toString());
				pQueue.search("Ahbi");
				pQueue.getFirst();
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		PriorityQueue p = new PriorityQueue(5);
		PriorityQTest test = new PriorityQTest(p);
		Thread[] t = new Thread[SIZE];

		for (int i = 0; i < SIZE; ++i) {
			t[i] = new Thread(test);
		}

		for (int i = 0; i < SIZE; ++i) {
			t[i].start();
		}

	}


}
