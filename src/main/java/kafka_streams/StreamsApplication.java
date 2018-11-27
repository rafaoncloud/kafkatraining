package kafka_streams;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class StreamsApplication implements Runnable {

    public static final String TOPIC_NAME = "kstreamstopic";

    @Override
    public void run() {
        //String topicName = args[0].toString();
        String topicName = TOPIC_NAME;
        String outTopicName = "resultstopic";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        System.out.println("[Streams App] Reading from topic: " + topicName);
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> lines = builder.stream(topicName);

        System.out.println("[Streams App] Group By Key.");
        KTable<String, Long> outlines = lines.groupByKey().count();
        //outlines.toStream().to(outTopicName);
        outlines.mapValues(v -> "" + v).toStream().to(outTopicName, Produced.with(Serdes.String(), Serdes.String()));

        System.out.println("[Streams App] Start streaming.");
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        System.out.println("[Streams App] End reading from " + topicName);
    }
}
