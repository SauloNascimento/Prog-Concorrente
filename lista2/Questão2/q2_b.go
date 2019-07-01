package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

func request(done chan int, start chan interface{}) {
	<-start
	tempo := rand.Intn(30) + 1
	fmt.Println(tempo)
	time.Sleep(time.Duration(tempo) * time.Second)
	done <- tempo

}

func timeout(done chan int, start chan interface{}) {
	<-start
	time.Sleep(16 * time.Second)
	done <- -1
}

func gateway(num_request int) {
	done := make(chan int, num_request - 1)
	start := make(chan interface{})
	for i := 0; i < num_request; i++ {
		select {
		case <-done:
			return
		default:
			go request(done, start)
		}
	}
  	go timeout(done, start)
  	close(start)
	soma := 0
	for i := 0; i < num_request; i++ {
    num := <-done
		if num > 0 {
      soma += num
    } else {
      soma = -1
      break
    }
	}
	close(done)
	fmt.Println(soma)
}

func main() {
	rand.Seed(time.Now().UTC().UnixNano())
	e := os.Args[1]
	num, _ := strconv.Atoi(e)
	gateway(num)
}
