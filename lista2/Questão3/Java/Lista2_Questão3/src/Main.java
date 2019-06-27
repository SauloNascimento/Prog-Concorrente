
public class Main {

	public static void main(String[] args) {
		int numThreads = Integer.parseInt(args[0]);
		Thread[] threads = new Thread[numThreads];
		for (int i = 0; i < threads.length; i++) {
			Worker worker = new Worker();
			threads[i] = new Thread(worker);
			threads[i].start();
		}
		try {
			for (int i = 0; i < threads.length; i++) {
					threads[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
