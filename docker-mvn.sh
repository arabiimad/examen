#!/bin/bash
set -e
docker run -it -v $(pwd):/src --workdir /src maven:3-eclipse-temurin-21 mvn -Dmaven.repo.local=/src/.m2/repository $@
