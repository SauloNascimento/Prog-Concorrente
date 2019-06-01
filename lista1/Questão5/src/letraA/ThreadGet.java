package letraA;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ThreadGet implements Runnable {
	private Map<Integer, Integer> map;
	private Channel channel;
	private final Random random = new Random();
	private int operacoes;

	public ThreadGet(Map<Integer, Integer> map, Channel channel, int operacoes) {
		this.map = map;
		this.channel = channel;
		this.operacoes = operacoes;
	}
	@Override
	public void run() {
		for (int i = 0; i < operacoes; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>(map.keySet());
			int key = list.get(random.nextInt(list.size()));
			
			long startTime = System.nanoTime();
			map.get(key);
			long finalTime = System.nanoTime();
			long executionTime = finalTime - startTime;
			channel.send(executionTime);
		}


	}
}
