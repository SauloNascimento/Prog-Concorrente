#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>


pthread_mutex_t mutex;
pthread_cond_t  cond;
int retorno = 0;
int count = 0;

void* request(){

	int num = 1 + rand() % (30 - 1);
	printf("%d\n", num);
	sleep(num);
	count++;
	pthread_mutex_lock(&mutex);
	if (count == 1) {
		retorno = num;
	}

	pthread_mutex_unlock(&mutex);
	pthread_cond_signal(&cond);
	pthread_mutex_unlock(&mutex);
	

	


	
}

int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_mutex_init(&mutex, NULL);
	pthread_cond_init(&cond, NULL);
	for(i = 0; i < num_replicas ; i++){
		pthread_create(&pthreads[i], NULL, &request, NULL);	
	}
	
	pthread_mutex_lock(&mutex);
	while (retorno == 0){
		pthread_cond_wait(&cond,&mutex);
		
	}
	printf("%d\n", retorno);
	pthread_mutex_unlock(&mutex);

	
	return retorno;
}

int main (int argc , char *argv[]){
	srand(time(NULL));
	int value = 2 ;
	gateway(value);

}
