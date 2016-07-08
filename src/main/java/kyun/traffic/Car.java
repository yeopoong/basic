package kyun.traffic;

import javax.swing.JButton;

public class Car extends JButton {

    public void moveLocation(boolean horizontal) {
        int x = getLocation().x;
        int y = getLocation().y;

        if (horizontal) {
            x = x + 1;
        } else {
            y = y + 1;
        }

        setLocation(x, y);
    }

    public void removeCar() {
        setVisible(false);
    }

    public void drive(boolean horizontal, int interval) {
        new AutoDriver(this, horizontal, interval).start();
    }
}