#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>

long int counter = 0;
pthread_barrier_t barreira;

void* run(void* args){
	int thread_id;
	
	thread_id = (int) args;
	int num = request();
	counter++;
	
	if (counter == 1) {
		printf("A thread que terminou de executar primeiro foi a thread: %d\n", thread_id);
		printf("O número sorteado por request foi: %d\n" , num);
	}
	pthread_barrier_destroy(&barreira);
	


	return NULL;

	
}

int request(){
	int num = 1 + rand() % (30 -1);
	sleep(num);
	return num;
}



int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_barrier_init(&barreira, NULL, 1);
	
	for(i = 0; i < num_replicas; i++){
		pthread_create(&pthreads[i], NULL, &run, (void*) i);
	}
	
	pthread_barrier_wait(&barreira);


	for(i = 0; i < num_replicas ; i++){
		pthread_join(pthreads[i], NULL);
	}
	
	pthread_barrier_destroy(&barreira);
	
	return 0;
}

int main (int argc , char *argv[]){
	int value = 40;
	gateway(value);

}
