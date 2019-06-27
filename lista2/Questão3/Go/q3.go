package main

import (
	"strconv"
	"os"
)

func worker(done chan bool) {
	total := 0
	for i := 1; i <= 1000; i++ {
		total += i;
	}
	done <- true
}

func main() {
	done := make(chan bool)
	num_threads, _ := strconv.Atoi(os.Args[1])
	for i := 0; i < num_threads; i++ {
		go worker(done)
	}
	for i := 0; i < num_threads; i++ {
		<-done
	}
}