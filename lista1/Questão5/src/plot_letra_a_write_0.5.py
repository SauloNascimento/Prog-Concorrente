import matplotlib as plt
import matplotlib.pyplot as plt
import numpy as np

x = []
y = []

x1 = []
y1 = []

media1 = 0
media2 = 0
sum1 = 0
sum2 = 0
count = 0
count2 = 0
thread = 1
dataset1 = open('data_t1_p0.5_op1000.csv', 'r')
dataset2 = open('data_t2_p0.5_op1000.csv', 'r')
dataset3 = open('data_t4_p0.5_op1000.csv', 'r')
dataset4 = open('data_t8_p0.5_op1000.csv', 'r')
dataset5 = open('data_t16_p0.5_op1000.csv', 'r')
dataset6 = open('data_t32_p0.5_op1000.csv', 'r')
dataset7 = open('data_t64_p0.5_op1000.csv', 'r')
dataset8 = open('data_t128_p0.5_op1000.csv', 'r')

for line in dataset1:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0
dataset1.close()


for line in dataset2:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset2.close()

for line in dataset3:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset3.close()

for line in dataset4:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset4.close()

for line in dataset5:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset5.close()

for line in dataset6:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset6.close()

for line in dataset7:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset7.close()

for line in dataset8:
    line = line.strip()
    A,B,C,D,E,F = line.split(',')
    if (A == "ConcurrentHashMap" and E == "write"):
        sum1 +=int(F)
        count+=1
    if (A == "SynchronizedMap" and E == "write"):
        sum2 +=int(F)
        count2+=1
    if (count == 500) :
        x.append(B)
        y.append(sum1/1000)
        count = 0
        sum1 = 0
    if (count2 == 500):
        x1.append(B)
        y1.append(sum2/1000)
        count2 = 0
        sum2 = 0

dataset8.close()
plt.xlabel('Threads')
plt.ylabel('Tempo Médio (ns)')
line_up, = plt.plot(x,y, label='ConcurrentHashMap')
line_down, = plt.plot(x1,y1, label='Collections.synchronizedMap')
plt.legend([line_up, line_down], ['ConcurrentHashMap', 'Collections.synchronizedMap'])

plt.title('Grafico para operação de escrita, usando 0.5 de carga na operação')

plt.show()
