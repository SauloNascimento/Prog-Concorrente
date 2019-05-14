package q2;

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

}
