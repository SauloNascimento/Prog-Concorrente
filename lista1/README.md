# Resolução da Lista 1 - Programação Concorrente #

## Questão 1 ##


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

### Letra a ###
[Código Java ](https://github.com/tainahemmanuele/programacao_concorrente/tree/master/lista1/Quest%C3%A3o5/src/letraA)

Códigos para gerar os gráficos

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_read_0.2.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_read_0.5.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_read_0.8.png)


Como podemos observar nos gráficos o ConcurrentHashMap é normalmente muito mais rápido em operações de leitura que o synchronizedMap, uma vez que no ConcurrentHashMap  as operações de leitura podem ocorrer em conjunto, por não alterarem a estrutura de dados, e o synchronizedMap permite uma única operação por vez, independendo do tipo da operação (escrita ou leitura).

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_write_0.2.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_write_0.5.png)

![](https://github.com/tainahemmanuele/programacao_concorrente/blob/master/lista1/Quest%C3%A3o5/src/graficos_letra_a/grafico_letra_a_write_0.8.png)

Já nas operações de escrita, o synchronizedMap se mostra melhor, novamente, isso se deve por apenas uma operação ocorrer por vez, independendo da natureza da operação,  e no ConcurrentHashMap a opração de escrita deve ocorrer só. Nota-se que com maior volume de operação de leitura, as operações de escrita no ConcurrentHashMap demoram em média mais tempo que com baixos volumes de leitura, isso é explicado pelo fato de que enquanto leituras estão sendo feitas, a operação de escrita espera que elas  acabem pra iniciar, então bloqueando a execução de qualquer operação na estrutura.

Podemos concluir, então, que quando se têm um ambiente em que o número de operações de leitura é maior que o de operações de escrita, o ConcurrentHashMap é sem dúvida  a melhor opção, uma vez que o ganho de tempo nas operações de leitura será muito maior que o atraso gerado pelas operações de escrita. O synchronizedMap tende a se  tornar cada vez mais lento em qualquer operação com base na quantidade de threads operando, pois apenas uma thread poderá executar por vez, independendo do que ela  vá fazer (ler ou escrever), mas é uma boa opção quando a quantidade de operações de escrita é similar ou maior que a de leitura e quando houverem poucas threads  executando essas operações.