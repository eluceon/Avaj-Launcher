#!/bin/sh

find . -name "*.java" > sources.txt
javac @sources.txt
java -cp src com.github.eluceon.avaj.Simulator scenario_md5.txt