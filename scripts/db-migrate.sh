#!/bin/bash
export DATABASE_URL=$1
export DATABASE_USER=$2
export DATABASE_PASSWORD=$3
./gradlew database:flywayMigrate -i -Dflyway.url="$DATABASE_URL" -Dflyway.user=$DATABASE_USER -Dflyway.password=$DATABASE_PASSWORD
