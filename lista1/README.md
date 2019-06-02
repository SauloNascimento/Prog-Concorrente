# Resolução da Lista 1 - Programação Concorrente #
[Questões da Lista ](https://docs.google.com/document/d/1blMH6kakRiwa4-WPYWp2Dks3Mt0SSq07XZysa2ffrPM/edit)

## Questão 1 ##
[Código com uso de mutex (Contador não compartilhado) ](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o1/questao1.c)

[Código sem uso de mutex (Contador compartilhado)](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o1/questao1_v1.c)

### Executando o perf em cada uma das versões do código ###
Para o codigo que usa mutex, o resultado da execução do perf foi:

    Performance counter stats for './questao1':

         77,973064      task-clock (msec)         #    0,990 CPUs utilized          
                 6      context-switches          #    0,077 K/sec                  
                 1      cpu-migrations            #    0,013 K/sec                  
                74      page-faults               #    0,949 K/sec                  
        77.163.647      cycles                    #    0,990 GHz                      (35,04%)
       137.646.302      instructions              #    1,78  insn per cycle           (75,79%)
        22.461.507      branches                  #  288,068 M/sec                    (80,51%)
            17.822      branch-misses             #    0,08% of all branches          (83,13%)
       131.365.295      L1-dcache-loads           # 1684,752 M/sec                    (84,64%)
            14.798      L1-dcache-load-misses     #    0,01% of all L1-dcache hits    (77,50%)
             8.184      LLC-loads                 #    0,105 M/sec                    (42,71%)
             1.440      LLC-load-misses           #   17,60% of all LL-cache hits     (30,77%)

       0,078727587 seconds time elapsed

Para o código que não usa mutex, o resultado da execução do perf foi:

	   Performance counter stats for './questao1_v1':

        248,857179      task-clock (msec)         #    2,803 CPUs utilized          
                12      context-switches          #    0,048 K/sec                  
                 1      cpu-migrations            #    0,004 K/sec                  
                74      page-faults               #    0,297 K/sec                  
       736.448.310      cycles                    #    2,959 GHz                      (43,13%)
       281.609.810      instructions              #    0,38  insn per cycle           (59,70%)
        26.873.129      branches                  #  107,986 M/sec                    (62,00%)
            19.825      branch-misses             #    0,07% of all branches          (66,76%)
       102.224.660      L1-dcache-loads           #  410,776 M/sec                    (70,50%)
         2.742.446      L1-dcache-load-misses     #    2,68% of all L1-dcache hits    (69,07%)
            38.777      LLC-loads                 #    0,156 M/sec                    (50,38%)
             2.480      LLC-load-misses           #    6,40% of all LL-cache hits     (45,61%)

       0,088792423 seconds time elapsed

Percebe-se que o tempo de execução do  código que usa mutex foi menor (0,078727587 segundos) do que o que não usa (0,088792423 segundos) . Isso ocorre porque  não há várias threads tentando mexer na variável do contador ao mesmo tempo,o mutex só permite uma operação de leitura e escrita por vez. No código onde não se usa mutex, mais de uma thread pode ler/gravar no contador ao mesmo tempo e nesse caso, há disputa entre as threads, o que pode fazer com que o desempenho diminua.

## Questão 2 ##
[Resolução da Questão 2](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o2/src/q2)

## Questão 3 ##
### Letra a ###
[C](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o3/Quest%C3%A3o3C/questao3_a.c)

[Java](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o3/Quest%C3%A3o3Java/src/letraA)

### Letra b ###
[C](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o3/Quest%C3%A3o3C/questao3_b.c)

[Java](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o3/Quest%C3%A3o3Java/src/letraB)

## Questão 4 ##
### Letra a ###
[C](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o4/Quest%C3%A3o4C/questao4_a.c)

[Java](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o4/Quest%C3%A3o4Java/src/letraA)

### Letra b ###
[C](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o4/Quest%C3%A3o4C/questao4_b.c)

[Java](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o4/Quest%C3%A3o4Java/src/letraB)


## Questão 5 ##
Para plotar os gráficos, é necessário instalar a biblioteca mathplotlib

     pip install matplotlib

Depois, basta executar os scripts na pasta de artefatos, que já estão configurados para a quantidade de threads (de 1 a 128, dobrando a cada operação), carga (0.2, 0.5 e 0.8) e operações (1000).

Para executar os scripts, rode o comando:

    python nomedoscript


### Letra a ###
[Código Java ](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o5/src/letraA)

[Código para executar os experimentos](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/executa_letra_a.sh)

[Códigos para gerar os gráficos](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o5/src/Artefatos_letra_a)


![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_read_0.2.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_read_0.5.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_read_0.8.png)


Como podemos observar nos gráficos o ConcurrentHashMap é normalmente muito mais rápido em operações de leitura que o synchronizedMap, uma vez que no ConcurrentHashMap  as operações de leitura podem ocorrer em conjunto, por não alterarem a estrutura de dados, e o synchronizedMap permite uma única operação por vez, independendo do tipo da operação (escrita ou leitura).

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_write_0.2.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_write_0.5.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_write_0.8.png)

Já nas operações de escrita, o synchronizedMap se mostra melhor, novamente, isso se deve por apenas uma operação ocorrer por vez, independendo da natureza da operação,  e no ConcurrentHashMap a opração de escrita deve ocorrer só. Nota-se que com maior volume de operação de leitura, as operações de escrita no ConcurrentHashMap demoram em média mais tempo que com baixos volumes de leitura, isso é explicado pelo fato de que enquanto leituras estão sendo feitas, a operação de escrita espera que elas  acabem pra iniciar, então bloqueando a execução de qualquer operação na estrutura.

Podemos concluir, então, que quando se têm um ambiente em que o número de operações de leitura é maior que o de operações de escrita, o ConcurrentHashMap é sem dúvida  a melhor opção, uma vez que o ganho de tempo nas operações de leitura será muito maior que o atraso gerado pelas operações de escrita. O synchronizedMap tende a se  tornar cada vez mais lento em qualquer operação com base na quantidade de threads operando, pois apenas uma thread poderá executar por vez, independendo do que ela  vá fazer (ler ou escrever), mas é uma boa opção quando a quantidade de operações de escrita é similar ou maior que a de leitura e quando houverem poucas threads  executando essas operações.

### Letra b ###

[Código Java](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o5/src/letraB)

[Código para executar os experimentos](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/executa_letra_b.sh)

[Códigos para gerar os gráficos](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o5/src/artefatos_letra_b)