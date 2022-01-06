# Run unittest
```shell
# Startup mysql etc
$ docker-compose up
$ mvn clean compile test
```

# Generate MyBatis Mapper
```shell script
$ mvn mybatis-generator:generate
```