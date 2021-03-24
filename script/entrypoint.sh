#!/bin/bash

argc=$#
args=("$@")

for ((i = 1; i < argc; i += 2)); do
  host=${args[$i]}
  port=${args[$i + 1]}

  echo >&2 "Wait for ${host} dependency"
  echo >&2 "run nc -z ${host} ${port}"

  while ! nc -z "${host}" "${port}"; do
    echo >&2 "The ${host} is not ready - waiting..."
    sleep 1
  done
  echo >&2 "The ${host} has been connected."
done

echo >&2 "run java -Dspring.profiles.active=${args[0]} -Duser.timezone=Asia/Seoul -Xmx1024m -XX:+UseG1GC -cp app:app/lib/* com.anthonykim.web.BlogWebServiceApplication"
java -Dspring.profiles.active=${args[0]} -Duser.timezone=Asia/Seoul -Xmx1024m -XX:+UseG1GC -cp app:app/lib/* com.anthonykim.web.BlogWebServiceApplication
