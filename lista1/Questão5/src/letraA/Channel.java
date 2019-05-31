package letraA;

import java.util.LinkedList;
import java.util.List;

public class Channel {
	private List<Long> times;
	private int i;

	public Channel() {
		times = new LinkedList<>();
	}

	public synchronized List recieve()  {
		return times;
	}

	public synchronized void send(long time) {
		times.add(time);
		
	}

}
