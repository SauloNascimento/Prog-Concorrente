package letraA;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadConcurrentSize implements Runnable {
	private ConcurrentHashMap<Integer, Integer> map;
	private Channel channel;

	public ThreadConcurrentSize(ConcurrentHashMap<Integer, Integer> map, Channel channel) {
		this.map = map;
		this.channel = channel;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			long startTime = System.currentTimeMillis();
			map.size();
			long finalTime = System.currentTimeMillis();
			long executionTime = finalTime - startTime;
			System.out.println(startTime +" "+ finalTime + " " +executionTime);
			channel.send(executionTime);
		}


	}
}
