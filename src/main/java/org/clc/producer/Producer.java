package org.clc.producer;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Producer {

    Random rng = new Random();

    private Logger logger;
    private Connection natsConnection;

    public Producer() {
        this.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);
        try {
            this.natsConnection = Nats.connect("nats://10.88.10.81:4222");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) throws InterruptedException, IOException {
        Producer producer = new Producer();

        while (true) {
            double[] params = producer.getVitalParameters();
            producer.logger.info("Mass: "+ String.valueOf(params[0]));
            producer.logger.info("Height: "+ String.valueOf(params[1]));

            // Convert parameters to a string
            String message = String.valueOf(params[0]) + ";"+ String.valueOf(params[1]);

            // Publish message to NATS server
            producer.natsConnection.publish("vitalparameters", message.getBytes());
        }
    }
}
