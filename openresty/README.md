This is a boilerplate for OpenResty project.

## Prerequisites

OpenResty is required to run the project or run tests for the project.

## Config project

```sh
cp config.lua.example config.lua
```

## Install required libs

```
make require
```

## Install required libs for dev environment

```
make require-dev
```

## Run tests

```sh
make test
```

## conf/nginx.conf.example

`nginx.conf.dist` is the location configuration to run the project with OpenResty.

## conf/nginx.conf.testing

`nginx.conf.testing` is the config file used for running tests with OpenResty.
