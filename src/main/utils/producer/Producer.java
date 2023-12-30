package main.utils.producer;

import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Producer {

    Random rng = new Random(); 

    public double[] getVitalParameters() {
        double mass = getMass(70, 10);
        double height = getHeight(175, 10);

        double[] res = {mass, height};
        return res;
    }

    public double computeFromNormDist(double mu, double sigma) {
        return rng.nextGaussian() * sigma + mu;
    }

    public double getMass(double mu, double sigma) {
        return computeFromNormDist(mu, sigma);
    }

    public double getHeight(double mu, double sigma) {
        return computeFromNormDist(mu, sigma);
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);

        while (true) {
            double[] params = producer.getVitalParameters();
            logger.info("Mass: "+ String.valueOf(params[0]));
            logger.info("Height: "+ String.valueOf(params[1]));
            Thread.sleep(10000);
        }
    }
}