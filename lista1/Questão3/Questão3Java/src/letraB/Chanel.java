package letraB;

public class Chanel {
	private int[] nums;
	private int i;
	
	public Chanel(int size) {
		i = 0;
		nums = new int[size];
	}
	
	public int[] recieve() throws InterruptedException {
		if(i < nums.length) {
			this.wait();
		}
		return nums;
	}
	
	public void send(int number) {
		if(i < nums.length) {
			nums[i] = number;
			i++;
			if(i == nums.length) {
				this.notifyAll();
			}
		}
	}
}
