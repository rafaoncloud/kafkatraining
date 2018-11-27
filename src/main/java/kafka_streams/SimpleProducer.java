package kafka_streams;

//import util.properties packages
import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

//Create java class named “SimpleProducer”
public class SimpleProducer {

    public static final String TOPIC_NAME = "kstreamstopic";

    public static void start() {

        //Assign topicName to string variable
        //String topicName = args[0].toString();
        final String topicName = TOPIC_NAME;

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.LongSerializer");

        Producer<String, Long> producer = new KafkaProducer<>(props);

        for(int i = 1000; i >= 0; i--)
            producer.send(new ProducerRecord<String, Long>(topicName, Integer.toString(i), (long) i));

        System.out.println("[Producer] Message sent successfully to topic " + topicName);
        producer.close();
        System.out.println("[Producer] Producer was closed.");
    }
}