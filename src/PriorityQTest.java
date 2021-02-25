import java.util.concurrent.TimeUnit;

public class PriorityQTest implements Runnable {
	final static int SIZE = 10;
	final static int ROUND = 5;
	
	/*final FairUnifanBathroom bathroom;
	
	public testFairUnifanBathroom(FairUnifanBathroom bathroom) {
		this.bathroom = bathroom;
	}
	

	public void run() {
		double random = Math.random();
		if(random < .5){
			//System.out.println("OU");
			bathroom.enterBathroomOU();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//bathroom.leaveBathroomUT();
			bathroom.leaveBathroomOU();
		}else{
		//	System.out.println("UT");
			bathroom.enterBathroomUT();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bathroom.leaveBathroomUT();
		}

		
	}
	*/
	public static void main(String[] args) {
		PriorityQueue p = new PriorityQueue(5);
		p.add("Hi", 9);
		p.add("ta", 9);
		p.add("Yo", 6);
		System.out.println(p.search("Hi"));
		try {
			System.out.println(p.getFirst());
			System.out.println(p.getFirst());
			System.out.println(p.search("Hi"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//testFairUnifanBathroom test = new testFairUnifanBathroom(bathroom);
		
	/*	Thread[] t = new Thread[SIZE];
		
		for (int i = 0; i < SIZE; ++i) {
			t[i] = new Thread(test);
		}
		
		for (int i = 0; i < SIZE; ++i) {
			t[i].start();
		}*/
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
