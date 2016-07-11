package kyun.basic.car;

public class Road {

    private Car[] cars = new Car[10];
    private int index;

    public void addCar(Car car) {
        cars[index++] = car;
    }

    public void allCar() {
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}