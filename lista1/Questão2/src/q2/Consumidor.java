package q2;

public class Consumidor implements Runnable {
	private final Buffer buffer;
	
	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
	}
	

	@Override
	public void run() {
		while (true) {
			synchronized (this.buffer) {
				while(this.buffer.isEmpty()) {
					try {
						this.buffer.wait();
					} catch (InterruptedException e) {
					}
				}
				
			}
			System.out.println("CONSUMIDO: " + this.buffer.takeMessage());
			this.buffer.notifyAll();
		}

	}

}
