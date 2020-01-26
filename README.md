# spring-cloud-stream-kafka
Trying spring cloud stream with kafka binder

## Pre-requisites to run this code (The below steps have been tried on Ubuntu 19.10)
1. Install jdk 8
2. Download and unzip Kafka 2.4 and run the following commands after _**cd**_ to the kafka install directory
```
    bin/zookeeper-server-start.sh config/zookeeper.properties
    bin/kafka-server-start.sh config/server.properties. 
```
3. Create 2 topics in Kafka using the following commands 
```
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic raw
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic valid
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
4. Use a console consumer on the valid, duplicate and unique topic to see the messages that have been processed by the streaming consumer. The StreamingValidator consumes all messages from the raw topic and outputs to the valid topic.
```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic valid --from-beginning
```

## Understanding the source code
**StreamingValidator** has the simple boilerplate code to read the stream from the input, process and stream to the output
**Validation** interface defines the destinations for spring to generate the bindings 
**application.yml** has the input and output topic names that can be configured. It defaults to raw (input topic) and valid (output topic) along with other configuration


## Benchmarking the code on your workstation
1. Insert messages into the raw topic using the below command. The below command would insert 10 mil messages of 100 chars to the raw topic
```
bin/kafka-producer-perf-test.sh --topic raw --num-records 10000000 --record-size 100 --throughput 5000000 --producer-props bootstrap.servers=localhost:9092
```
2. Execute the program so that it starts streaming from raw to valid.
3. You can look at the lag for the consumer group (t1 by default, can be changed in application.yml) of the valid topic every 10 seconds (using watch command) by executing the following command
```
   bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group t1
```

## Observations
I was getting a streaming throughput of 100K messages a second using Samza on the same workstation, but with the spring-cloud-stream app I was getting a throughput of about 45K messages (only) a second with some basic optimizations of the consumer configuration (in application.yml). Also please note that the default streaming class has no processing and just returns the same data that is read from the input to the output.