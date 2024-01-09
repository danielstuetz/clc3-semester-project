package main.utils.consumer;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Consumer {
    
    private Logger logger;

    public Consumer() {
        this.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);
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

    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();

        while (true) {
            consumer.computeBMI(75, 180);
            Thread.sleep(10000);
        }

        
    }


}
