# !/bin/bash
#

PROJECT_ROOT=`pwd`
NGINX_PID="/tmp/openresty_testing.pid"
NGINX_CONFIG="$PROJECT_ROOT/conf/nginx.conf.testing"

# OpenResty still create log files under `logs`, even if `error_log`
# directive is used
if [ ! -d "logs" ]; then
    mkdir "logs"
fi

echo "Running nginx with $NGINX_CONFIG"

# stop nginx before running test
if [ -f "$NGINX_PID" ]; then
    nginx -c "$NGINX_CONFIG" -s stop
fi

# start nginx
PROJECT_ROOT="$PROJECT_ROOT" nginx -c "$NGINX_CONFIG" -p "$PROJECT_ROOT"

# running test

TEST_RESULT=`curl -i http://localhost:18888/__run_tests`

echo "$TEST_RESULT"

# stop nginx
nginx -c $NGINX_CONFIG -s stop

# get http status from $TEST_RESULT
HTTP_200=`echo $TEST_RESULT | grep 'HTTP/1.1 200 OK'`

# we only consider `200 OK` as tests passed
if [ -z "$HTTP_200" ]; then
    exit 1
else
    exit 0
fi

