#!/bin/bash
#Script feito para execução da resolução da questão 5, letra a  - lista 1 - Programação Concorrente

#Aqui será considerado que o valor de threads irá de 1 até 128 (onde vai ser dobrado o valor até chegar a 128)
#As cargas usadas nas operações de leitura e escrita serão: 0.2, 0.5 e 0.8
#A quantidade de operações será 1000
THREADS=1

#Compilação do código
javac -classpath . letraA/Main.java

#Execução do código para 0.2
#Comando é: java  -classpath . letraA.Main qtdthreads cargaoperacoes qtdoperacoes
for i in $(seq 1 8) ; do
    java  -classpath . letraA.Main $THREADS 0.2 1000
    THREADS=$[$THREADS*2]
done

THREADS=1

#Execução do código para 0.5
#Comando é: java  -classpath . letraA.Main qtdthreads cargaoperacoes qtdoperacoes
for i in $(seq 1 8) ; do
    java  -classpath . letraA.Main $THREADS 0.5 1000
    THREADS=$[$THREADS*2]
done

THREADS=1
#Execução do código para 0.8
#Comando é: java  -classpath . letraA.Main qtdthreads cargaoperacoes qtdoperacoes
for i in $(seq 1 8) ; do
    java  -classpath . letraA.Main $THREADS 0.8 1000
    THREADS=$[$THREADS * 2]
done

#Move arquivos gerados para pasta onde está os scripts para geração dos gráficos
mv *.csv artefatos_letra_a
