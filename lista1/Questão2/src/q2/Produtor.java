package q2;

public class Produtor implements Runnable {
	private final Buffer buffer;
	private int count;

	public Produtor(Buffer buffer) {
		this.count = -1;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this.buffer) {
				while(this.buffer.isFull()) {
					try {
						this.buffer.wait();
					} catch (InterruptedException e) {
					}
				}
				
			}
			this.count++;
			String message = String.format("Mensagem: %d", count);
			this.buffer.putMessage(message);
			System.out.println("PRODUZIDO: " + message);
			this.buffer.notifyAll();
		}

	}

}
