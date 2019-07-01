#!/bin/bash
tail -f /dev/null
cd /opt
javac -classpath . src/Main.java
java -classpath . src.Main.java value
