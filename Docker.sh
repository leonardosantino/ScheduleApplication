set -e

./gradlew build

docker build -f Dockerfile.local --platform linux/amd64 -t leonardosantino/scheduleapplication:local .
