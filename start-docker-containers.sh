#!/bin/bash

if [ $# -ne 1 ]; then
  echo "Usage: ./start-docker-container <profile>"
  echo "Valid profiles: local | docker"
  exit 1
fi

profile=$1

if [[ "$profile" != "local" && "$profile" != "docker" ]]; then
  echo "Invalid profile: $profile"
  echo "Valid profiles: local | docker"
  exit 1
fi

docker-compose -f ./docker/docker-compose.yaml --profile "$profile" up
