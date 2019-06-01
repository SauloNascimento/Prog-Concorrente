package letraB;
import letraB.Lock;
import letraB.Channel;
import letraB.Request;


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
		Channel channel = new Channel(numReplicas);
		Thread[] threads = new Thread[numReplicas];
		for (int i = 0; i < threads.length; i++) {
			Request request = new Request(lock, channel);
			threads[i] = new Thread(request);
			threads[i].start();
		}
		synchronized (lock) {
			lock.unlock();
		}
		try {
			synchronized (channel) {
				int[] results = channel.recieve();
				for (int i = 0; i < results.length; i++) {
					total += results[i];
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return total;
	}
}
