# spring-cloud-stream-kafka
Trying spring cloud stream with kafka binder

## Pre-requisites to run this code (The below steps have been tried on Ubuntu 19.10)
1. Install jdk 8
2. Download and unzip Kafka 2.4 and run the following commands after _**cd**_ to the kafka install directory
```
    bin/zookeeper-server-start.sh config/zookeeper.properties
    bin/kafka-server-start.sh config/server.properties. 
```
3. Create 4 topics in Kafka using the following commands 
```
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic raw
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic valid
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic unique
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic duplicate
    bin/kafka-topics.sh --list --bootstrap-server localhost:9092 //This is to see that the topics have been created
```

## Running the code
1. Clone this repo and _**cd**_ to the cloned folder
2. Run mvn clean packages
3. Run java -jar target/spring-cloud-kafka-0.0.1-SNAPSHOT.jar
3. Add messages to the raw topic by issuing the following commands after _**cd**_ to the kafka install directory
```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic raw
```
4. Use a console consumer on the valid, duplicate and unique topic to see the messages that have been processed by the streaming consumer. The StreamingValidator consumes all messages from the raw topic and outputs to the valid topic. The StreamingDedupe consumes all messages from the valid and routes the messages to the unique or duplicate(all messages that say hello are marked as duplicate for the demo) topic.
```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic valid --from-beginning
```

## Understanding the source code
Source code has comments. Some reading on spring-cloud-streams would help
