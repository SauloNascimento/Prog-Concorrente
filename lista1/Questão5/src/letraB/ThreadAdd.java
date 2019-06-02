package letraB;


import java.util.List;
import java.util.Random;

public class ThreadAdd  implements Runnable{
	private List<Integer> list;
	private final Random random = new Random();
	private Channel channel;
	private int operacoes;
	
	public ThreadAdd(List <Integer> list, Channel channel, int operacoes) {
		this.list =  list;
		this.channel = channel;
		this.operacoes = operacoes;
	}
	@Override
	public void run() {
		for (int i = 0; i < operacoes; i++) {
			long startTime = System.nanoTime();
			list.add(random.nextInt(1000));
			long finalTime = System.nanoTime();
			long executionTime = finalTime - startTime;
			channel.send(executionTime);
		}

	}

}
