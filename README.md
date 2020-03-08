# Data Intensive Architecture Project

Stephen McMullan (x19139497@student.ncirl.ie)

Semester 2, Data Intensive Architectures, Postgraduate Diploma in Data Analytics

National College of Ireland

## GitHub Project Code Repository

https://github.com/smcmullan-ncirl/DIAProject

## About

https://www.measurementlab.net/

https://www.measurementlab.net/tests/mobiperf/

## Datasets

16806 files, 8564395 records

https://console.cloud.google.com/storage/browser/openmobiledata_public

## Prerequisites
1. VM
2. Linux OS
3. RAM
4. CPU
5. Disk Space

## Setup Build Environment Instructions

    sudo apt-get install maven
    sudo apt install git
    git clone https://github.com/smcmullan-ncirl/DIAProject.git
    cd DIAProject
    mvn clean package

## Setup Runtime Environment Instructions

First install Docker and add your user to the docker group

    sudo apt install docker.io
    sudo usermod -aG docker ${USER}
    sudo apt install docker-compose

You need to logout and log back in to take on the new group assignment

You can then check if Docker is running correctly by running the Hello World container
    
    docker run hello-world

## Run Instructions

    docker-compose up -d
    docker-compose ps
    docker-compose exec kafka kafka-topics.sh --create --replication-factor 1 --partitions 1 --bootstrap-server localhost:9092 --topic my-topic
    docker-compose exec kafka kafka-topics.sh --list --bootstrap-server localhost:9092

## Kafka Consumer Instructions

    docker-compose exec kafka kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic

## Postgres Setup Instructions

    sudo apt-get install postgresql-client
    sudo apt install postgresql-client-common

## Starting Processing Instructons

    cd dia-data-import
    mvn exec:java
    
or

    java -jar dia-data-import/target/gcpdataimport-jar-with-dependencies.jar
    
## Shutdown Instructions

    docker-compose exec kafka kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic my-topic
