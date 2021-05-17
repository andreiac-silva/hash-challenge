##Build Image
FROM golang:1.16-stretch AS builder
COPY . /discount-calculator
WORKDIR /discount-calculator
RUN go mod download
RUN go get -u github.com/google/wire/cmd/wire@v0.5.0 && wire && go get -t . && CGO_ENABLED=0 GOOS=linux go build -o bin/application

##Run Image
FROM scratch
COPY --from=builder /discount-calculator/bin/application application
COPY .env .env
EXPOSE 8000
ENTRYPOINT ["./application"]