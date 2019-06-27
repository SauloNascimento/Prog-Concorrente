package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

var count int = 0
var sum int = 0

func request(done chan int, num_request int) {
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	tempo := r.Intn(30) + 1
	fmt.Println(tempo)
	time.Sleep(time.Duration(tempo) * time.Second)
	count++
	sum += tempo
	if (num_request == count){
		done <- sum
	}

}

func gateway(num_request int) {
	done := make(chan int)
	for i := 0; i < num_request; i++ {
		select {
		case <-done:
			return
		default:
			go request(done,num_request)
		}
	}
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
