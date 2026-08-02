#!/bin/zsh

set -e

./gradlew build

docker build --load -t leonardosantino/schedule_application:0.0.1 .
