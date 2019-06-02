package letraB;

import java.util.LinkedList;
import java.util.List;

public class Channel {
	private List<Long> times;

	public Channel() {
		times = new LinkedList<>();
	}

	public synchronized List<Long> recieve()  {
		return times;
	}

	public synchronized void send(long time) {
		times.add(time);
		
	}

}
