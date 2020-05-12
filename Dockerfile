FROM openjdk:8-jdk-alpine

RUN apk add --no-cache bash

COPY ./wait-for-it.sh /wait-for-it.sh