package kyun.traffic;

import javax.swing.JFrame;

public class Road extends JFrame {

    public Road() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Road Environment");
        this.setSize(600, 400);
        this.setLayout(null);

        this.setVisible(true);
    }

    public void addCar(Car car) {
        add(car);
    }
}