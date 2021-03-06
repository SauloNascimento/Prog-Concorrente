package letraA;

import java.util.Map;
import java.util.Random;

public class ThreadAdd  implements Runnable{
	private Map<Integer, Integer> map;
	private final Random random = new Random();
	private Channel channel;
	private int operacoes;
	
	public ThreadAdd(Map <Integer, Integer> map, Channel channel, int operacoes) {
		this.map =  map;
		this.channel = channel;
		this.operacoes = operacoes;
	}
	@Override
	public void run() {
		for (int i = 0; i < operacoes; i++) {
			long startTime = System.nanoTime();
			map.put(random.nextInt(1000) , random.nextInt(100)*5);
			long finalTime = System.nanoTime();
			long executionTime = finalTime - startTime;
			channel.send(executionTime);
		}

	}

}
