package letraB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// Numero de threads de cada tipo de operação que serão criadas
		int num_threads = Integer.parseInt(args[0]); 
		// porcentagem de operações de escrita do total (0 a 1)
		double proporcao = Double.parseDouble(args[1]);
		// total de operações que serão realizadas
		int op_total = Integer.parseInt(args[2]);
		// total de operações de escrita que serão realizadas
		int op_total_escrita = (int) (op_total * proporcao);
		// total de operações de leitura que serão realizadas
		int op_total_leitura = op_total - op_total_escrita;
		// calculo do total de operações que será realizado por thread (soma = total de cada tipo)
		int[] op_threads_escrita = new int[num_threads];
		int op_thread = op_total_escrita;
		for (int i = 0; i < op_threads_escrita.length; i++) {
			op_threads_escrita[i] = op_thread - (op_total_escrita/num_threads * (num_threads - i - 1));
			op_thread -= op_threads_escrita[i];
			
		}
		op_thread = op_total_leitura;
		int[] op_threads_leitura = new int[num_threads];
		for (int i = 0; i < op_threads_leitura.length; i++) {
			op_threads_leitura[i] = op_thread - (op_total_leitura/num_threads * (num_threads - i - 1));
			op_thread -= op_threads_leitura[i];
			
		}

		// Teste 1: CuncurrentHashMap
		CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();
		Channel writeChannel1 = new Channel();
		Channel readChannel1 = new Channel();
		ThreadAdd[] threadsADD1 = new ThreadAdd[num_threads];
		ThreadGet[] threadsGET1 = new ThreadGet[num_threads];
		Thread[] threads1 = new Thread[Integer.parseInt(args[0]) * 2];

		
		for (int i = 0; i < threadsADD1.length; i++) {
			threadsADD1[i] = new ThreadAdd(list1, writeChannel1, op_threads_escrita[i]);
			threads1[i] = new Thread(threadsADD1[i]);
		}
		for (int i = 0; i < threadsGET1.length; i++) {
			threadsGET1[i] = new ThreadGet(list1, readChannel1, op_threads_leitura[i]);
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

		// Teste 2: SynchronizedMap
		List<Integer> list2 = new ArrayList<>();
		List<Integer> synchList = Collections.synchronizedList(list2);
		Channel writeChannel2 = new Channel();
		Channel readChannel2 = new Channel();
		ThreadAdd[] threadsADD2 = new ThreadAdd[Integer.parseInt(args[0])];
		ThreadGet[] threadsGET2 = new ThreadGet[Integer.parseInt(args[0])];
		Thread[] threads2 = new Thread[Integer.parseInt(args[0]) * 2];

		for (int i = 0; i < threadsADD2.length; i++) {
			threadsADD2[i] = new ThreadAdd(synchList, writeChannel2, op_threads_escrita[i]);
			threads2[i] = new Thread(threadsADD2[i]);
		}
		for (int i = 0; i < threadsGET2.length; i++) {
			threadsGET2[i] = new ThreadGet(synchList, readChannel2, op_threads_leitura[i]);
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
		
		// Salvar dados em arquivo .csv
		try {
			File file = new File("data_t"+num_threads+"_p"+proporcao+"_op"+op_total+".csv");
			FileWriter filewriter = new FileWriter(file);
			String EOF = System.lineSeparator();
			filewriter.append("estrutura,num_threads,op_total,prop_escrita,op_tipo,tempo"+EOF);
			for (Long time : readChannel1.recieve()) {
				filewriter.append(String.format("CopyOnWriteArrayList,%d,%d,%s,read,%d%n", num_threads, op_total, proporcao, time));
			}
			for (Long time : writeChannel1.recieve()) {
				filewriter.append(String.format("CopyOnWriteArrayList,%d,%d,%s,write,%d%n", num_threads, op_total, proporcao, time));
			}
			for (Long time : readChannel2.recieve()) {
				filewriter.append(String.format("SynchronizedList,%d,%d,%s,read,%d%n", num_threads, op_total, proporcao, time));
			}
			for (Long time : writeChannel2.recieve()) {
				filewriter.append(String.format("SynchronizedList,%d,%d,%s,write,%d%n", num_threads, op_total, proporcao, time));
			}
			filewriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
