package letraA;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadConcurrentAdd  implements Runnable{
	private ConcurrentHashMap<Integer, Integer> map;
	private final Random random = new Random();
	private Channel channel;
	
	public ThreadConcurrentAdd(ConcurrentHashMap <Integer, Integer> map, Channel channel) {
		this.map =  map;
		this.channel = channel;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			long startTime = System.currentTimeMillis();
			map.put(random.nextInt(100) , random.nextInt(100)*5);
			long finalTime = System.currentTimeMillis();
			long executionTime = finalTime - startTime;
			System.out.println(startTime +" "+ finalTime + " " +executionTime);
			channel.send(executionTime);
		}

	}

}
