public class Worker implements Runnable {

	@Override
	public void run() {
		int total = 0;
		for (int i = 1; i <= 1000; i++) {
			total += i;
		}
	}

}
