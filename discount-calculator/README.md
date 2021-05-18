## Discount Calculator

This project part of the solution proposed to reach the Hash challenge. Discount-calculator includes a unary rpc api
which is responsible to calculate discounts based on user id (optional) and product id.

- Enable GO Modules

```shell script
$ export GO111MODULE=on
```

- Download dependencies

```shell script
$ go mod download
```

- Wire was used to handle with dependency injection. Install It.

```sh
$   go get -u github.com/google/wire/cmd/wire
```   

- Execute wire to build `wire_gen.go`.

```sh
$   wire
```

- Now, start application executing `main.go` present on the root project directory. 
