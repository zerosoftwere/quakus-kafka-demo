# Kafka Message Consumer

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Requirement
- kafka cluster running
- provide the KAFKA_BOOTSTRAP_SERVER environment variable to point to the kafka cluster

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```