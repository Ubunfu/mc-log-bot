#!/usr/bin/env bash

# brew install jq python-yq
VERSION=$(xq .project.version ../pom.xml | tr -d \")

echo ""
echo "Deploying tag ${VERSION}"
echo ""

./deploy-github-tag.sh $VERSION
./deploy-docker-image.sh $VERSION