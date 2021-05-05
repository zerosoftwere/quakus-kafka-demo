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

You can run your application in dev mode that enables live coding using:
```shell script
docker-compose up
```

## Stopping the application

The application would be removed using:
```shell script
docker-compose down
```

## Application resource

- producer: `http://localhost:8000`
- consumer: `http://localhost:8001`

## Documentation

- find the doc for producer at `http://localhost:8000/swagger-ui`
- find the doc for consumer at `http://localhost:8001/swagger-ui`