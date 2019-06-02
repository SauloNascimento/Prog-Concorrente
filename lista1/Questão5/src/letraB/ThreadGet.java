package letraB;

import java.util.List;
import java.util.Random;

public class ThreadGet implements Runnable {
	private List<Integer> list;
	private Channel channel;
	private final Random random = new Random();
	private int operacoes;

	public ThreadGet(List<Integer> list, Channel channel, int operacoes) {
		this.list = list;
		this.channel = channel;
		this.operacoes = operacoes;
	}
	@Override
	public void run() {
		for (int i = 0; i < operacoes; i++) {
			int key = random.nextInt(list.size());
			
			long startTime = System.nanoTime();
			list.get(key);
			long finalTime = System.nanoTime();
			long executionTime = finalTime - startTime;
			channel.send(executionTime);
		}


	}
}
