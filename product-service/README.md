## Product Service - Java and Quarkus

This project uses Java with [Quarkus](https://quarkus.io/).

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Creating a native executable

You can create a native executable using:
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/product-service-1.0.0-SNAPSHOT-runner`

The product service image pushed on docker hub, was generated using GraalVM.
