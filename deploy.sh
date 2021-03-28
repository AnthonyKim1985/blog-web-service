#!/usr/bin/env bash

argc=$#
args=("$@")

function printUsage() {
  echo "USAGE: /bin/bash deploy.sh {target: integer value, 0 (local), 1 (dev), 2 (prod)}"
}

function init() {
  REGISTRY_ID="hyuk0628"

  if [[ ${argc} -eq 0 ]]; then
    TAG="snapshot"
  elif [[ ${argc} -eq 1 ]]; then
    if [[ ${args[0]} -eq 0 ]]; then
      TAG="snapshot"
    elif [[ ${args[0]} -eq 1 ]]; then
      TAG="snapshot"
    elif [[ ${args[0]} -eq 2 ]]; then
      TAG="release"
    else
      printUsage
      exit 0
    fi
  else
    printUsage
    exit 0
  fi

  BASE_NAME=$(/bin/bash gradlew -q printBaseName)
  VERSION=$(/bin/bash gradlew -q printVersion)
}
init

function deployImage() {
  # Clean up
  /bin/bash gradlew clean
  /bin/bash gradlew dockerClean

  # Build up
  /bin/bash gradlew docker --info

  # Deploy
  docker tag "${BASE_NAME}":"${VERSION}" "${REGISTRY_ID}"/"${BASE_NAME}":"${TAG}"."${VERSION}"
  docker push "${REGISTRY_ID}"/"${BASE_NAME}":"${TAG}"."${VERSION}"
  echo "The deployment for ${REGISTRY_ID}/${BASE_NAME}:${TAG}.${VERSION} has been completed."

  docker tag "${BASE_NAME}":"${VERSION}" "${REGISTRY_ID}"/"${BASE_NAME}":"${TAG}".latest
  docker push "${REGISTRY_ID}"/"${BASE_NAME}":"${TAG}".latest
  echo "The deployment for ${REGISTRY_ID}/${BASE_NAME}:${TAG}.latest has been completed."
}
deployImage

echo "Job's done"
