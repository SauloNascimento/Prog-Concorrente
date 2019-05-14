package q2;

import java.math.*;

public class Buffer implements Channel {
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
