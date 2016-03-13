#!/usr/bin/env bash

git submodule init
git submodule update

docker-machine start default
./bin/docker-osx-tools/update-dm-hosts.sh
eval $(docker-machine env default)
./gradlew clean build

docker-compose rm -f
docker-compose build
docker-compose up