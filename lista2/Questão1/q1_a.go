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

func gateway(num_request int) {
	done := make(chan int)
	start := make(chan interface{})
	for i := 0; i < num_request; i++ {
		select {
		case <-done:
			return
		default:
			go request(done, start)
		}
	}
	close(start)
	result := <-done
	close(done)
	fmt.Println(result)

}

func main() {
	rand.Seed(time.Now().UTC().UnixNano())
	e := os.Args[1]
	num, _ := strconv.Atoi(e)
	gateway(num)

}
