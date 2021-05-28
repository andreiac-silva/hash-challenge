## Product Service - Java and [Quarkus](https://quarkus.io/)
This project is part of the solution proposed to reach the Hash challenge. Product-calculator includes a rest API to bring the products with their respective discounts (if available).
To execute the complete solution by docker-compose, go back to [parent repository](https://github.com/andreiac-silva/hash-challenge) and take a look at the instructions. Otherwise, to run it locally, take a look at the following steps.

### Running the application in dev mode

You can run the application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

The product service image pushed on docker hub was generated in native mode.
