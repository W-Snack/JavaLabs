import java.awt.*;

public class Target {
    private int x;
    private int y;
    private int speed;
    private int size;
    private boolean isFar;

    public Target(int x, int y, int speed, int size, boolean isFar) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.isFar = isFar;
    }

    public void move() {
        y += speed;
    }

    public void resetY(int newY) {
        y = newY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x, y, size, size);
    }

    public int getY() { return y; }
    public int getX() { return x; }
    public int getSize() { return size; }
    public boolean isFar() { return isFar; }
}