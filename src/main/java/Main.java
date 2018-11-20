public class Main {

    public static void main(String[] args) {

        try {

            new Thread(new ConsumerExample()).start();

            ProducerExample.produce();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
