# Integrating Quarkus With Kafka Demo

Demonstrate the use of quarkus with kafka in microsevice environment 

## prerequisite the application

- docker and docker-compose are installed
- port 8000 and 8001 are free
- java 11 or higher

## Build the application
Package both producer and consumer using:
- for mac / linux
    ```shell script
    ./mvnw package
    ```
- for windows
    ```shell script
    ./mvnw.bat package
    ```
do so in their respective folders


## Running the application

Run this command in the root folder where the docker-compose.yml file is located:
```shell script
docker-compose up
```
This would start up zookeeper, kafka, and the producer / consumer applications. make sure both (producer / consumer) applications have been packaged.

## Stopping the application

You can shutdonw the containers using:
```shell script
docker-compose stop
```
You can also stop and start individually containers using:
```shell script
docker-compose stop consumer
```
```shell script
docker-compose start producer
```
Lastly to remove the containers:
```shell script
docker-compose down
```

## Application resource

- producer: `http://localhost:8000`
- consumer: `http://localhost:8001`

## Documentation

- find the doc for producer at `http://localhost:8000/swagger-ui`
- find the doc for consumer at `http://localhost:8001/swagger-ui`