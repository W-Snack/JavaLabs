import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Target> targets;
    private List<Arrow> arrows;
    private int score = 0;
    private int shots = 15;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private GameThread gameThread;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        targets = new ArrayList<>();
        arrows = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isRunning && !isPaused && shots > 0) {
                    shots--;
                    arrows.add(new Arrow(0, e.getY()));
                    firePropertyChange("shots", null, shots);
                }
            }
        });
    }

    public void startGame() {
        isRunning = true;
        isPaused = false;
        score = 0;
        shots = 15;
        targets.clear();
        arrows.clear();
        int middleY = getHeight() / 2;
        targets.add(new Target(300, middleY, 2, 40, false));
        targets.add(new Target(500, middleY, 4, 20, true));
        targets.add(new Target(700, middleY, 2, 40, false));
        targets.add(new Target(900, middleY, 4, 20, true));
        firePropertyChange("score", null, score);
        firePropertyChange("shots", null, shots);
        if (gameThread == null) {
            gameThread = new GameThread(this);
            gameThread.start();
        }
    }

    public void stopGame() {
        isRunning = false;
        gameThread = null;
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Target t : targets) {
            t.draw(g);
        }
        for (Arrow a : arrows) {
            a.draw(g);
        }
    }

    public void updateGame() {
        if (!isRunning || isPaused) return;

        for (Target t : targets) {
            t.move();
            if (t.getY() > getHeight()) {
                t.resetY(0);
            }
        }

        Iterator<Arrow> arrowIter = arrows.iterator();
        while (arrowIter.hasNext()) {
            Arrow a = arrowIter.next();
            a.move();
            if (a.getX() > getWidth()) {
                arrowIter.remove();
                continue;
            }
            for (Target t : targets) {
                if (checkCollision(a, t)) {
                    score += t.isFar() ? 2 : 1;
                    firePropertyChange("score", null, score);
                    arrowIter.remove();
                    break;
                }
            }
        }
        repaint();
    }

    private boolean checkCollision(Arrow a, Target t) {
        Rectangle arrowRect = new Rectangle(a.getX(), a.getY(), 20, 5);
        Rectangle targetRect = new Rectangle(t.getX(), t.getY(), t.getSize(), t.getSize());
        return arrowRect.intersects(targetRect);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }
}