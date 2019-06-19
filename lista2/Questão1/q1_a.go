package main

import (
	"math/rand"
	"os"
	"strconv"
	"time"
)

func request(done chan int) {
	for {
		select {
		case <-done:
			return
		default:
			r := rand.New(rand.NewSource(time.Now().UnixNano()))
			tempo := r.Intn(30) + 1
			println(tempo)
			time.Sleep(time.Duration(tempo) * time.Second)
			done <- tempo
		}
	}
}

func gateway(num_request int) {
	done := make(chan int)
	for i := 0; i < num_request; i++ {
		go request(done)
	}
	result := <- done
	close(done)
	println(result)

}


func main() {
	e := os.Args[1]
	num, _ := strconv.Atoi(e)
	gateway(num)

}