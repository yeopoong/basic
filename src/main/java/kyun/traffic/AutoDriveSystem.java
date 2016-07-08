package kyun.traffic;

import java.awt.Color;

public class AutoDriveSystem {

    public static void main(String[] args) {
        Road road = new Road();

        Car car = launchCar(20, 100, Color.RED);
        road.add(car);
        car.drive(true, 10);

        car = launchCar(50, 200, Color.YELLOW);
        road.add(car);
        car.drive(true, 20);

        car = launchCar(300, 20, Color.BLUE);
        road.add(car);
        car.drive(false, 30);
    }

    private static Car launchCar(int x, int y, Color color) {
        Car car = new Car();
        car.setBackground(color);
        car.setSize(10, 10);
        car.setLocation(x, y);

        return car;
    }
}