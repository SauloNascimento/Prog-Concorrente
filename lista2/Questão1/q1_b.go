package main

import (
	"math/rand"
	"os"
	"strconv"
	"time"
)

var count int = 0
var sum int  =0

func request(done chan int, num_request int) {
	for {
		if count == num_request {
			done <- sum
			return
		} else {
			r := rand.New(rand.NewSource(time.Now().UnixNano()))
			tempo := r.Intn(30) + 1
			time.Sleep(time.Duration(tempo) * time.Second)
			count++
			println(tempo)
			sum += tempo
		}
	}
}

func gateway(num_request int) {
	done := make(chan int)
	for i := 0; i < num_request; i++ {
		go request(done,num_request)
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
