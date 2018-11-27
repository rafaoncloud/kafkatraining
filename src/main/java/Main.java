import exercise1.ProducerExample;
import exercise1.ConsumerExample;
import kafka_streams.SimpleConsumer;
import kafka_streams.SimpleProducer;
import kafka_streams.StreamsApplication;

public class Main {

    public static void main(String[] args) {

        //example1();
        kafkastreams1();


    }

    public static void example1(){
        try {

            new Thread(new ConsumerExample()).start();

            ProducerExample.produce();

            //new Thread(new ConsumerExample()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void kafkastreams1(){
        try {

            new Thread(new SimpleConsumer()).start();

            SimpleProducer.start();

            new Thread(new StreamsApplication()).start();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
