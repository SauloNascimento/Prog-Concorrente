#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <semaphore.h>

long int counter = 0;
pthread_mutex_t mutex;
pthread_cond_t  cond;
sem_t sema;
int retorno;

void* run(void* args){
	int thread_id;
	long int j;
	
	thread_id = (int) args;
	pthread_mutex_lock(&mutex);
	int num = request();
	pthread_mutex_unlock(&mutex);

	printf("A thread que terminou de executar primeiro foi a thread: %d\n", num);
	printf("O número sorteado por request foi: %d\n" , num);

	pthread_exit(thread_id);
	
}

int request(){
	pthread_cond_wait(&cond);
	int num = 1 + rand() % (30 -1);
	sleep(num);
	sem_post(&sema);
	pthread_exit(num);
}

int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_mutex_init(&mutex, NULL);
	pthread_cond_init(&cond, NULL);
	sem_init(&mutex, 0, 0);
	
	for(i = 0; i < num_replicas ; i++){
		pthread_create(&pthreads[i], NULL, &run, (void*) i);	
	}
	
	pthread_cond_broadcast(&pthreads);
	
	sem_wait(&sema);
	return x;
}

int main (int argc , char *argv[]){
	srand(time(NULL));
	int value = 250;
	gateway(value);

}
