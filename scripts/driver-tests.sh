#!/bin/bash
service_url=${1}

if [ -z "$service_url" ]; then
     export PERSON_SERVICE_URL="http://localhost:8080"
else
    export PERSON_SERVICE_URL="{service_url}"
fi
echo "Using ${PERSON_SERVICE_URL} as test url"

./gradlew driver:clean driver:test
