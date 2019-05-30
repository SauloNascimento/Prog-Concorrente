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
struct timespec   ts;
struct timeval    tp;

void* request(){
	
	int num = 1 + rand() % (30 - 1);
	printf("Valor sorteado : %d\n", num);
	sleep(num);
	count++;
	pthread_mutex_lock(&mutex);
	retorno += num;
	pthread_mutex_unlock(&mutex);



	
}


int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_mutex_init(&mutex, NULL);
	pthread_cond_init(&cond, NULL);
	clock_gettime(CLOCK_REALTIME, &ts);
    ts.tv_sec += 16;
	
	for(i = 0; i < num_replicas ; i++){
		pthread_create(&pthreads[i], NULL, &request, NULL);	
	}
	
	for(i = 0; i < num_replicas ; i++){
		int err = pthread_timedjoin_np(pthreads[i], NULL, &ts);
		if (err != 0){
			retorno = -1;
		}
	}

	return retorno;
}

int main (int argc , char *argv[]){
	srand(time(NULL));
	int value = 10;
	int ret = gateway(atoi(argv[1]));
	printf("Soma de todos os valores sorteados: %d\n", ret);

}
