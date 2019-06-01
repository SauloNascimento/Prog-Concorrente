package letraA;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Main {
	
	 public static void main(String[] args) {
		 
		 //   Teste 1: CuncurrentHashMap
		 ConcurrentHashMap<Integer, Integer> map1 = new ConcurrentHashMap<>();
		 Channel writeChannel1 = new Channel();
		 Channel readChannel1 = new Channel();
		 ThreadAdd[] threadsADD1 = new ThreadAdd[Integer.parseInt(args[0])];
		 ThreadGet[] threadsGET1 = new ThreadGet[Integer.parseInt(args[0])];
		 Thread[] threads1 = new Thread[Integer.parseInt(args[0]) * 2];
		 
		 for (int i = 0; i < threadsADD1.length; i++) {
			 threadsADD1[i] = new ThreadAdd(map1, writeChannel1);
			 threads1[i] = new Thread(threadsADD1[i]);
		 }
		 for (int i = 0; i < threadsGET1.length; i++) {
			 threadsGET1[i] = new ThreadGet(map1, readChannel1);
			 threads1[Integer.parseInt(args[0]) + i] = new Thread(threadsGET1[i]);
		 }
		 
		 for (int i = 0; i < threads1.length; i++) {
			 threads1[i].start();
		 }
		 
		 try {
			 for (int i = 0; i < threads1.length; i++) {
				 threads1[i].join();
			 }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 
		 //   Teste 2: SynchronizedMap
		 Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();
		 Map<Integer, Integer> synchMap = Collections.synchronizedMap(map2);
		 Channel writeChannel2 = new Channel();
		 Channel readChannel2 = new Channel();
		 ThreadAdd[] threadsADD2 = new ThreadAdd[Integer.parseInt(args[0])];
		 ThreadGet[] threadsGET2 = new ThreadGet[Integer.parseInt(args[0])];
		 Thread[] threads2 = new Thread[Integer.parseInt(args[0]) * 2];
		 
		 for (int i = 0; i < threadsADD2.length; i++) {
			 threadsADD2[i] = new ThreadAdd(synchMap, writeChannel2);
			 threads2[i] = new Thread(threadsADD2[i]);
		 }
		 for (int i = 0; i < threadsGET2.length; i++) {
			 threadsGET2[i] = new ThreadGet(synchMap, readChannel2);
			 threads2[Integer.parseInt(args[0]) + i] = new Thread(threadsGET2[i]);
		 }
		 
		 for (int i = 0; i < threads2.length; i++) {
			 threads2[i].start();
		 }
		 
		 try {
			 for (int i = 0; i < threads2.length; i++) {
				 threads2[i].join();
			 }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
