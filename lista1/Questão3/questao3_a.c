#include <stdio.h>
#include <assert.h>
#include <pthread.h>

long int counter = 0;
pthread_mutex_t mutex;

void* run(void* args){
	int my_id;
	long int j;
	
	my_id = (int) args;
	pthread_mutex_lock(&mutex);
	
	for(j = 0; j < 1e7 ; j++){
		counter = counter + 1;
	}
	pthread_mutex_unlock(&mutex);
	printf("my_id=%d j=%ld counter=%ld\n", my_id , j , counter);
	pthread_exit(my_id);
	
}

int* request(){
	int num = 1 + rand() % (30 + 1 - 1);
	printf(num);
	sleep(num);
	return num;
}



int gateway(int num_replicas){
	int i;
	pthread_t pthreads[num_replicas];
	pthread_mutex_init(&mutex, NULL);
	
	for(i = 0; i < num_replicas; i++){
		pthread_create(&pthreads[i], NULL, &run, (void*) i);
	}
	
	for(i = 0; i < num_replicas; i++){
		pthread_join(pthreads[i], NULL);
	}
	
	
}

int main (int argc , char *argv[]){
	int value = gateway(3);
	printf("O valor da primeira thread finalizada é=%ld\n", value);
	
}
