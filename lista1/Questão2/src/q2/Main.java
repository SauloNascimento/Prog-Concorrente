package q2;

import java.math.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Buffer buffer = new Buffer(5);

		Produtor produtor = new Produtor(buffer);
		Consumidor consumidor = new Consumidor(buffer);

		Thread t0 = new Thread(produtor, "thread-produtor");
		Thread t1 = new Thread(consumidor, "thread-consumidor");

		t0.start();
		t1.start();

		t0.join();
		t1.join();

	}

	public static class Produtor implements Runnable {
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
					while (this.buffer.isFull()) {
						try {
							this.buffer.wait();
						} catch (InterruptedException e) {
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

	}

	public static class Consumidor implements Runnable {
		private final Buffer buffer;

		public Consumidor(Buffer buffer) {
			this.buffer = buffer;
		}

		@Override
		public void run() {
			while (true) {
				synchronized (this.buffer) {
					while (this.buffer.isEmpty()) {
						try {
							this.buffer.wait();
						} catch (InterruptedException e) {
						}
					}
					System.out.println("CONSUMIDO: " + this.buffer.takeMessage());
					this.buffer.notifyAll();
				}

			}

		}

	}

	public static class Buffer implements Channel {
		private String fila[];
		private int tail;
		private int head;
		private int size;

		public Buffer(int tamanho) {
			this.fila = new String[tamanho];
			this.tail = -1;
			this.head = 0;
			this.size = 0;

		}

		@Override
		public void putMessage(String message) {
			this.tail = (this.tail + 1) % this.fila.length;
			this.fila[tail] = message;
			this.size = Math.min(this.size + 1, this.fila.length);

		}

		@Override
		public String takeMessage() {
			String mensagem = this.fila[this.head];
			this.head = (this.head + 1) % this.fila.length;
			this.size = Math.max(this.size - 1, 0);
			return mensagem;
		}

		public boolean isFull() {
			return this.size == this.fila.length;
		}

		public boolean isEmpty() {
			return this.size == 0;
		}
	}

}
