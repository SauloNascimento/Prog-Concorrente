package letraA;

public class Main {
	
	public static void main(String[] args) {
		Gateway gateway = new Gateway(Integer.parseInt(args[0]));
		Thread t0 = new Thread(gateway);
		t0.start();
		
	}

}
