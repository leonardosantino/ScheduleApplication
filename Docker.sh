#!/bin/zsh

set -e

./gradlew build

docker build --load --platform linux/amd64 -t leonardosantino/scheduleapplication:0.0.2 .
