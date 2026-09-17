#!/usr/bin/env bash

if [[ "$(docker images -q al2023-graalvm25:native 2> /dev/null)" == "" ]];
then
  echo "Building"
  docker build -t al2023-graalvm25:native src/main/docker
fi

docker run --rm -it -v `pwd`:`pwd` -w `pwd` -v ~/.m2:/root/.m2 al2023-graalvm25:native ./mvnw clean package -Pnative
