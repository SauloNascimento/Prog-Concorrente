import matplotlib as plt
import matplotlib.pyplot as plt
import numpy as np

x = []
y = []
media1 = 0
media2 = 0
sum1 = 0
sum2 = 0
count = 0
count2 = 0
thread = 1
dataset1 = open('data_concurrent_hash_read_0.2.csv', 'r')
dataset2 = open('data_syncchronized_map_read_0.2.csv', 'r')

for line in dataset1:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    sum1 +=int(F)
    count+=1
    if (count == 80) :
        x.append(B)
        y.append(sum1/thread)
        count = 0
        sum1=0
        thread = thread * 2
dataset1.close()

x1 = []
y1 = []

thread = 1
for line in dataset2:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    sum2 += int(F)
    count2+=1
    if (count2 == 80) :
        x1.append(B)
        y1.append(sum2/thread)
        count2 = 0
        sum2=0
        thread = thread * 2

dataset2.close()
plt.xlabel('Threads')
plt.ylabel('Tempo (em nanotime)')
line_up, = plt.plot(x,y, label='ConcurrentHashMap')
line_down, = plt.plot(x1,y1, label='Collections.synchronizedMap')
plt.legend([line_up, line_down], ['ConcurrentHashMap', 'Collections.synchronizedMap'])

plt.title('Grafico para operação de leitura, usando 0.8 de carga na operação')

plt.show()
