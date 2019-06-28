package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
	"sync"
)

var count int = 0
var sum int = 0

func request(done chan int, num_request int, waitgroup *sync.WaitGroup) {
	waitgroup.Wait()
	tempo := rand.Intn(30) + 1
	fmt.Println(tempo)
	time.Sleep(time.Duration(tempo) * time.Second)
	done <- tempo

}

func timeout(done chan int, waitgroup *sync.WaitGroup) {
	waitgroup.Wait()
	time.Sleep(16 * time.Second)
	done <- -1
}

func gateway(num_request int) {
	done := make(chan int, num_request - 1)
	var waitgroup sync.WaitGroup
	waitgroup.Add(1)
	for i := 0; i < num_request; i++ {
		select {
		case <-done:
			return
		default:
			go request(done,num_request, &waitgroup)
		}
	}
  go timeout(done, &waitgroup)
	waitgroup.Done()
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
