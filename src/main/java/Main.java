import exercise1.ProducerExample;
import exercise1.ConsumerExample;

public class Main {

    public static void main(String[] args) {

        example1();


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
}
