#!/usr/bin/env bash

# brew install jq python-yq
VERSION=$(xq .project.version ../pom.xml | tr -d \")

./deploy-github-tag.sh $VERSION
./deploy-docker-image.sh $VERSION