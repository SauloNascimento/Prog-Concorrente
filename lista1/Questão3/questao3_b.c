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

void* run(){

	int num = request();
	pthread_mutex_lock(&mutex);
	retorno += num;
	pthread_mutex_unlock(&mutex);



	
}

int request(){
	int num = 1 + rand() % (30 - 1);
	printf("Valor sorteado : %d\n", num);
	sleep(num);
	count++;
	return num;
}
int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_mutex_init(&mutex, NULL);
	pthread_cond_init(&cond, NULL);
	for(i = 0; i < num_replicas ; i++){
		pthread_create(&pthreads[i], NULL, &run, NULL);	
	}
	
	for(i = 0; i < num_replicas ; i++){
		pthread_join(pthreads[i], NULL);
	}

	
	return retorno;
}

int main (int argc , char *argv[]){
	srand(time(NULL));
	int value = 10;
	int ret = gateway(atoi(argv[1]));
	printf("Soma de todos os valores sorteados: %d\n", ret);

}

