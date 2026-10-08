set -e

./gradlew build

docker build -f Dockerfile.local -t leonardosantino/scheduleapplication:local .
