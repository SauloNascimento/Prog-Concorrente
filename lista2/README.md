# Resolução da Lista 2 - Programação Concorrente #

[Questões da Lista](https://docs.google.com/document/d/1hXRGvRRBkkl7qGsZOJK_loT9bIqFnnDsSt4oZ8O5pGo/edit)

## Executando as Questões em Go ##
Os códigos da questão 1 e da questão 2 devem ser executados da seguinte maneira:

    go run nomedaquestao numerodereplicas

Onde *nomedaquestao* é o nome do arquivo relacionado a letra da questão

E *numerodereplicas*, a quantidade de replicas de goroutines que devem ser criadas 

## Questão 1 ##

[Letra a](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o1/q1_a.go)

[Letra b](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o1/q1_b.go)

## Questão 2 ##

[Letra a](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista2/Quest%C3%A3o2)

[Letra b](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o2/q2_b.go)

## Questão 3 ##


### Introdução ###

Para fazer o experimento dessa questão, onde foi avaliado o consumo de memória de um programa que calcula o somatório de números, dentro do intervalo de 1 a 1000, implementado nas linguagens Go e Java, foi usado containers Docker.

Para a execução dos códigos, foram usados containers com as imagens oficiais de cada uma das linguagens usadas no experimento. Para coletar os dados referentes ao uso de memória, foi usado o cAdvisor. 

Apesar do cAdvisor já gerar os gráficos após a coleta dos dados de desempenho, por questões de legibilidade, foi usado o Grafana para plotar os gráficos a partir dos dados coletados pelo cAdvisor. Para que esses dados fossem plotados corretamente, também foi usado o Prometheus para guardar esses dados coletados pelo cAdvisor. 

A solução com Prometheus + cAdvisor + Grafana está disponível nesse link:

[Grafana + Prometheus + cAdvisor](https://github.com/tainahemmanuele/monitoramento_tcc) 

Por conta disso, no arquivo docker-compose.yml onde estão todos os artefatos para a execução do experimento, a parte relacionada ao cAdvisor está comentado. Caso queria usar apenas o cAdvisor para verificar o desempenho das linguagens, basta descomentar.

[Código em Go](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/Go/q3.go) 

[Código em Java](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista2/Quest%C3%A3o3/Java/Lista2_Quest%C3%A3o3) 

[Docker-compose](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/docker-compose.yml) 

### Executando o experimento ###

Para executar o experimento, siga os passos:

#### 1 Passo
Vá na pasta Go e no arquivo entrypoint.sh troque *value* pela quantidade de threads a serem usadas no experimento

Vá na pasta Java e no arquivo entrypoint.sh troque *value * pela quantidade de threads a serem usadas no experimento

Salve os arquivos

**OBS:** Nos dois arquivos a quantidade de threads deve ser a mesma para que não haja discordância nos resultados gerados.

####2 Passo
No arquivo docker-compose.yml , troque o *x* ***container_name: go_x_threads*** e ***container_name: java_x_threads*** pelo valor da quantidade de threads que serão usadas no experimento

####3 Passo
Na pasta Questão3, execute:

	docker-compose up -d

### Resultados
O experimento foi executado iniciando em 2 threads e dobrando o valor de threads até chegar em 256. A seguir, os gráficos com os dados de uso de memória para cada quantidade de threads

**2 Threads** : A linha em vermelho refere-se ao container executando o código em Java e a linha azul refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_2.png) 


**4 Threads** : A linha em azul refere-se ao container executando o código em Java e a linha verde refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_4.png) 

**8 Threads** : A linha em verde refere-se ao container executando o código em Java e a linha azul refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_8.png) 

**16 Threads** : A linha em vermelho refere-se ao container executando o código em Java e a linha roxa refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_16.png) 

**32 Threads** : A linha em azul refere-se ao container executando o código em Java e a linha rosa refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_32.png) 

**64 Threads** : A linha em vermelho refere-se ao container executando o código em Java e a linha laranja  refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_64.png) 

**128 Threads** : A linha em rosa refere-se ao container executando o código em Java e a linha azul refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_128.png) 

**256 Threads** : A linha em verde refere-se ao container executando o código em Java e a linha laranja refere-se ao container executando o código em Go
![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista2/Quest%C3%A3o3/img/threads_2256.png) 


Ao fim do experimento e observando os gráficos gerados,  pode-se observar pelo consumo de memória, as Goroutines são mais eficientes que a Threads de Java, pois consomem menos memória. Conforme aumentamos o número de threads e goroutines, a diferença de consumo de memória cresce, mostrando consistência na maior eficiência das goroutines.
