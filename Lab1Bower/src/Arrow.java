import java.awt.*;

public class Arrow {
    private int x;
    private int y;
    private int speed = 3;

    public Arrow(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + 20, y);
        int[] x1 = { x-2, x-2, x+8 };
        int[] y1 = { y+10 , y-10, y };
        g.drawPolygon(x1, y1, 3);
        int[] x2 = { x+13, x+13, x+23 };
        int[] y2 = { y+10 , y-10, y };
        g.fillPolygon(x2, y2, 3);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}