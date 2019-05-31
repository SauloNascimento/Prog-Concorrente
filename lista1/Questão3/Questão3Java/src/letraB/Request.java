package letraB;

import java.util.Random;
import letraB.Channel;
import letraB.Lock;

public class Request implements Runnable{
	private static final Random rand = new Random();
	private Lock lock;
	private Channel channel;
	private int num;
	
	public Request(Lock lock, Channel channel) {
		this.lock = lock;
		this.channel = channel;
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
			synchronized (channel) {
				channel.send(num);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public int getNum() {
		return num;
	}

}
