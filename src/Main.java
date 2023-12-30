import main.utils.consumer.Consumer;
import main.utils.producer.Producer;



public class Main {
    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();


        double[] params = producer.getVitalParameters();
        System.out.printf("Mass: %s \n", params[0]);
        System.out.printf("Height: %s \n", params[1]);
        System.out.printf("BMI: %s \n", consumer.computeBMI(params[0], params[1]));
    }
}