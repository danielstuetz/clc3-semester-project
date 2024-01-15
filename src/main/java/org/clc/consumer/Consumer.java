package org.clc.consumer;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Consumer {

    private Logger logger;
    private Connection natsConnection;

    public Consumer() {
        this.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);
        try {
            this.natsConnection = Nats.connect("nats://10.88.10.81:4222");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double computeBMI(double mass, double height) {
        this.logger.info("calculation started");

        double heightInM = height/100.0;

        double bmi = mass/(heightInM*heightInM);

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

        return bmi;
    }

    public void subscribeToNats(String subject) {
        try {
            Subscription sub = this.natsConnection.subscribe(subject);
            Message msg = sub.nextMessage(0);
            while (msg != null) {
                String data = new String(msg.getData());
                this.logger.info("Received message: " + data);
                msg = sub.nextMessage(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();

        while (true) {
            consumer.subscribeToNats("mass");
            consumer.subscribeToNats("height");
            Thread.sleep(10000);
        }
    }
}
