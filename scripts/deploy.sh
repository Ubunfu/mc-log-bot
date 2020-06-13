#!/bin/bash

# brew install jq python-yq
VERSION=$(xq .project.version ../pom.xml | tr -d \")

./deploy-githib-package.sh
./deploy-docker-image.sh $VERSION