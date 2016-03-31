#!/usr/bin/env bash

machineName="ws-elastic"

git submodule init
git submodule update

docker-machine create ${machineName} --driver=virtualbox
docker-machine start ${machineName}
./bin/docker-osx-tools/update-dm-hosts.sh
eval $(docker-machine env ${machineName})
./gradlew clean build

docker stop $(docker ps -q)

docker-compose rm -f
docker-compose build
docker-compose up