#!/usr/bin/env bash

docker run -i --rm -p 8080:8080 --net quarkusjpademo_default --link librarydatasource --name jpa-library quarkus/jpa-library