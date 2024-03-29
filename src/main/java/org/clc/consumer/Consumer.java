package org.clc.consumer;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Consumer {

    private Random rng = new Random();

    private Logger logger;
    private Connection natsConnection;

    public Consumer() {
        this.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);
        try {
            this.natsConnection = Nats.connect("nats://10.84.4.49:4222");
        } catch (Exception e) {
            this.logger.severe("Connection to NATS failed:" + e.getMessage());
        }
    }

    public void computeBMI(double mass, double height) {
        double bmi;
        if (rng.nextInt(1, 50) == 5) {
            this.logger.severe("Consumer crash provoked.");
            throw new RuntimeException("Consumer crash provoked.");
        }

        double heightInM = height/100.0;

        bmi = mass/(heightInM*heightInM);

        bmi = Math.round(bmi*100.0) / 100.0;
        if (bmi < 18.5) {
            this.logger.warning("Untergewicht! BMI: " + String.valueOf(bmi));
        }
        else if (bmi < 25) {
            this.logger.info("Normalgewicht :) BMI: " + String.valueOf(bmi));
        }
        else if (bmi < 30) {
            this.logger.warning("Übergewicht! BMI: " + String.valueOf(bmi));
        }
        else if (bmi < 35) {
            this.logger.warning("Starkes Übergewicht! BMI: " + String.valueOf(bmi));
        }
        else if (bmi < 40) {
            this.logger.warning("Adipositas Grad 2! BMI: " + String.valueOf(bmi));
        }
        else {
            this.logger.warning("Adipositas Grad 3! BMI: " + String.valueOf(bmi));
        }
    }

    public void subscribeToNats(String subject) throws InterruptedException {
        Subscription sub = this.natsConnection.subscribe(subject);
        Message msg = sub.nextMessage(0);
        while (msg != null) {
            String vitalParameters = new String(msg.getData());

            String[] params = vitalParameters.split(";");
            double mass = Double.parseDouble(params[0]);
            double height = Double.parseDouble(params[1]);

            this.logger.info("Mass: " + mass);
            this.logger.info("Height: " + height);
            this.computeBMI(mass, height);

            msg = sub.nextMessage(0);
        }
    }

    public static void main(String[] args) throws InterruptedException, RuntimeException {
        Consumer consumer = new Consumer();
        consumer.subscribeToNats("vitalparameters");
    }
}
