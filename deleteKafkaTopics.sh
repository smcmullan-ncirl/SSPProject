#!/bin/bash

docker-compose exec kafka kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic telecom_trento

docker-compose exec kafka kafka-topics.sh --list --bootstrap-server localhost:9092
