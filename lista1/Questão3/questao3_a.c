#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>


pthread_barrier_t barreira;

void* run(void* args){
	int thread_id;
	
	thread_id = (int) args;
	request();
	printf("A thread que terminou de executar primeiro foi a thread: %d", thread_id);
	pthread_barrier_destroy(&barreira);
	
}

int request(){
	int num = 1 + (rand() % 30 );
	printf("O tempo é de %d", num);
	sleep(num);
	return num;
}



int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_barrier_init(&barreira, NULL,num_replicas+1);
	
	for(i = 0; i < num_replicas; i++){
		pthread_create(&pthreads[i], NULL, &run,(void*) i);
	}
	

	for(i = 0; i < num_replicas ; i++){
		pthread_join(pthreads[i], NULL);
	}
}

int main (int argc , char *argv[]){
	int value = 6;
	gateway(value);
	
}
