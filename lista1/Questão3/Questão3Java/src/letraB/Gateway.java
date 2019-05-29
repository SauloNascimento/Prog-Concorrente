package letraB;

public class Gateway implements Runnable{
	private int numReplicas;
	private int total;
	
	public Gateway(int numReplicas) {
		this.numReplicas = numReplicas;
	}

	@Override
	public void run() {
		System.out.println(gateway());
	}
	
	private int gateway() {
		Lock lock = new Lock();
		Request[] requests = new Request[numReplicas];
		Thread[] threads = new Thread[numReplicas];
		for (int i = 0; i < threads.length; i++) {
			requests[i] = new Request(lock);
			threads[i] = new Thread(requests[i]);
			threads[i].start();
		}
		synchronized (lock) {
			lock.unlock();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
				total += requests[i].getNum();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return total;
	}
}
