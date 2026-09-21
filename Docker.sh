#!/bin/zsh

set -e

./gradlew build

docker build --load -f Dockerfile.local --platform linux/amd64 -t leonardosantino/scheduleapplication:local .
