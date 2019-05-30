package letraA;

public class Gateway implements Runnable{
	private int numReplicas;
	private int num;
	
	public Gateway(int numReplicas) {
		this.numReplicas = numReplicas;
	}

	@Override
	public void run() {
		System.out.println(gateway());
	}
	
	private int gateway() {
		Lock lock = new Lock();
		Chanel chanel = new Chanel();
		Thread[] threads = new Thread[numReplicas];
		for (int i = 0; i < threads.length; i++) {
			Request request = new Request(lock, chanel);
			threads[i] = new Thread(request);
			threads[i].start();
		}
		synchronized (lock) {
			lock.unlock();
		}
		synchronized (chanel) {
			try {
				num = chanel.recieve();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return num;
		}
	}
}
