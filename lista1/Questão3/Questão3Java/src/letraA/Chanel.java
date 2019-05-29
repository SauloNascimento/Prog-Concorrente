package letraA;

public class Chanel {
	private int num;
	
	public Chanel() {
		num = -1;
	}
	
	public int recieve() throws InterruptedException {
		if(num < 0)
			this.wait();
		return num;
	}
	
	public void send(int number) {
		if(this.num < 0)
			this.num = number;
			this.notifyAll();
		
	}
}
