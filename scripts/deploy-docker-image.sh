#!/bin/bash

VERSION=$1

docker buildx build --platform linux/amd64,linux/arm64 -t ubunfu/mc-log-bot:latest -t ubunfu/mc-log-bot:$VERSION --push ../