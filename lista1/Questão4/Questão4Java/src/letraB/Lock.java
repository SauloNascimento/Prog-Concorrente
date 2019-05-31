package letraB;

public class Lock {
	private boolean breacked;
	
	public Lock() {
		breacked = false;
	}
	
	public void waitLock() throws InterruptedException {
		if(!breacked)
			this.wait();

	}
	
	public void unlock() {
		breacked = true;
		this.notifyAll();
	}
}
