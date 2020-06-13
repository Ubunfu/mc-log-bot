#!/usr/bin/env bash

VERSION=$1

git tag $VERSION
git push --tags