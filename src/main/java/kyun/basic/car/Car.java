package kyun.basic.car;

import java.awt.Color;

public class Car {

    int x;
    int y;
    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(boolean horizontal) {
        if (horizontal) {
            x = x + 1;
        } else {
            y = y + 1;
        }
    }

    @Override
    public String toString() {
        return "Car [x=" + x + ", y=" + y + ", color=" + color + "]";
    }
}