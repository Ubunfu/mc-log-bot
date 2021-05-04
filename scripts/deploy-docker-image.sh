#!/usr/bin/env bash

VERSION=$1

docker buildx build --platform linux/amd64,linux/arm64 -t ghcr.io/ubunfu/mc-log-bot/mc-log-bot:$VERSION -t ubunfu/mc-log-bot:$VERSION --push ../