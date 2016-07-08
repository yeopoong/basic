package kyun.traffic;

import java.awt.Point;

public class AutoDriver extends Thread {

    private Car car;

    private long interval;
    boolean horizontal;

    public AutoDriver(Car car, boolean horizontal, long interval) {
        this.car = car;
        this.horizontal = horizontal;
        this.interval = interval;
    }

    public void run() {
        Point p = car.getLocation();

        while (p.x < 590 && p.y < 390) {
            car.moveLocation(horizontal);

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            p = car.getLocation();
        }

        car.removeCar();
    }
}