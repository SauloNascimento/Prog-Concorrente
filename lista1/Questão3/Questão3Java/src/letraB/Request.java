package letraB;

import java.util.Random;

public class Request implements Runnable{
	private static final Random rand = new Random();
	private Lock lock;
	private int num;
	
	public Request(Lock lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		request();
		
	}
	
	private int request() {
		try {
			synchronized (lock) {
				lock.waitLock();
			}
			num = rand.nextInt(30) + 1;
			Thread.sleep(num*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public int getNum() {
		return num;
	}

}
