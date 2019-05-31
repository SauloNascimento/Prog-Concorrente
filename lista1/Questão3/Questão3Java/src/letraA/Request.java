package letraA;

import java.util.Random;
import letraA.Channel;
import letraA.Lock;

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

}
