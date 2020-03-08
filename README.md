# Data Intensive Architecture Project

##### Stephen McMullan
###### x19139497@student.ncirl.ie
###### PGDDA Sept 2019

## Setup Instructions

First install Docker and add your user to the docker group

    sudo apt install docker.io
    sudo usermod -aG docker ${USER}
    sudo apt install docker-compose

You need to logout and log back in to take on the new group assignment

You can then check if Docker is running correctly by running the Hello World container
    
    docker run hello-world

Install git in order to retrieve the project

    sudo apt install git
    https://github.com/smcmullan-ncirl/DIAProject.git
    
## Run Instructions

    cd DIAProject
    docker-compose up -d
    docker-compose ps
    docker-compose exec kafka kafka-topics.sh --create --replication-factor 1 --partitions 1 --bootstrap-server localhost:9092 --topic my-topic
    docker-compose exec kafka kafka-topics.sh --list --bootstrap-server localhost:9092

## Kafka Consumer Instructions

    docker-compose exec kafka kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic
   
## Shutdown Instructions

    docker-compose exec kafka kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic my-topic
