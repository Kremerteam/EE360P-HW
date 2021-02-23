import java.util.concurrent.TimeUnit;

public class testFairUnifanBathroom implements Runnable {
	final static int SIZE = 5;
	final static int ROUND = 5;
	
	final FairUnifanBathroom bathroom;
	
	public testFairUnifanBathroom(FairUnifanBathroom bathroom) {
		this.bathroom = bathroom;
	}
	

	public void run() {
		bathroom.enterBathroomUT();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bathroom.leaveBathroomUT();
		bathroom.enterBathroomOU();
		//bathroom.leaveBathroomUT();
		bathroom.leaveBathroomOU();
		
	}
	
	public static void main(String[] args) {
		FairUnifanBathroom bathroom = new FairUnifanBathroom();
		Thread[] t = new Thread[SIZE];
		
		for (int i = 0; i < SIZE; ++i) {
			t[i] = new Thread(new testFairUnifanBathroom(bathroom));
		}
		
		for (int i = 0; i < SIZE; ++i) {
			t[i].start();
		}
    }
}
