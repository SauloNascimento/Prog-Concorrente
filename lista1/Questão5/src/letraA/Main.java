package letraA;

import java.util.concurrent.ConcurrentHashMap;

public class Main {
	
	 public static void main(String[] args) {
		 ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
		 Channel writeChannel = new Channel();
		 Channel readChannel = new Channel();
		 //long start = System.currentTimeMillis();
		 
		 ThreadConcurrentAdd concurrentAdd = new ThreadConcurrentAdd(map, writeChannel);
		 ThreadConcurrentAdd concurrentAdd1 = new ThreadConcurrentAdd(map, writeChannel);
		 ThreadConcurrentAdd concurrentAdd2 = new ThreadConcurrentAdd(map, writeChannel);
		 ThreadConcurrentSize concurrentSize= new ThreadConcurrentSize(map, readChannel);
		 Thread thread0 = new Thread(concurrentAdd);
		 Thread thread1 = new Thread(concurrentSize);
		 Thread thread2 = new Thread(concurrentAdd1);
		 Thread thread3 = new Thread(concurrentAdd2);
		 thread0.start();
		 thread1.start();
		 thread2.start();
		 thread3.start();
		 try {
			thread0.join();
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //System.out.println(map);
		 //System.out.println(readChannel.recieve());
		 //System.out.println(writeChannel.recieve());
		 //long end = System.currentTimeMillis();
		 //System.out.println(end - start);
	}

}
