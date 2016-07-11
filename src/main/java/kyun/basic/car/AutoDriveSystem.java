package kyun.basic.car;

import java.awt.Color;

public class AutoDriveSystem {

    public static void main(String[] args) {
        Road road = new Road();

        Car car = new Car();
        car.setColor(Color.RED);
        car.setLocation(20, 100);
        road.addCar(car);
        car.move(true);

        car = new Car();
        car.setColor(Color.YELLOW);
        car.setLocation(50, 200);
        road.addCar(car);
        car.move(true);

        car = new Car();
        car.setColor(Color.BLUE);
        car.setLocation(300, 20);
        road.addCar(car);
        car.move(false);

        road.allCar();
    }
}