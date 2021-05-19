## Hash Challenge

Hey!! What's up? First of all, thank you for your time reviewing this code.
To make it easy, this parent repository contains the two projects which compose the Hash Challenge solution: product-service and discount-calculator.
<br/>
To execute the complete solution by docker-compose, run the following command line from this parent directory:

```shell script
$ docker-compose up -d
```

The projects' images were pushed on docker-hub. The users and products collections will be automatically loaded through `docker-compose` execution.
<br/>
Once the application is running, the API will be available on `localhost:8080`.
<br/>

| METHOD       | RESOURCE    | HEADERS         |
|--------------|-------------|-----------------|
| GET          |/product     | X-USER-ID (opt) |

Users' id can be retrieved accessing mongodb:
<br/>


```shell script
$ docker exec -it mongo bash
$ mongo
$ db.getCollection('users').find({})
```

To stop the execution, run:

```shell script
$ docker-compose down
```
