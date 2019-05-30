package letraB;

public class Main {
	
	public static void main(String[] args) {
		Gateway gateway = new Gateway(10);
		Thread t0 = new Thread(gateway);
		t0.start();
		
	}

}
