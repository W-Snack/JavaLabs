import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {
    private GamePanel gamePanel;
    private JButton startButton, pauseButton, stopButton;
    private JLabel scoreLabel, shotsLabel;

    public Game() {
        setTitle("Меткий стрелок");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        startButton = new JButton("Старт");
        pauseButton = new JButton("Пауза");
        stopButton = new JButton("Стоп");
        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);

        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        scoreLabel = new JLabel("Счет: 0");
        shotsLabel = new JLabel("Выстрелов: 15");
        infoPanel.add(scoreLabel);
        infoPanel.add(shotsLabel);

        add(controlPanel, BorderLayout.SOUTH);
        add(infoPanel, BorderLayout.NORTH);

        setupListeners();
        gamePanel.addPropertyChangeListener(e -> {
            if ("score".equals(e.getPropertyName())) {
                scoreLabel.setText("Счет: " + e.getNewValue());
            } else if ("shots".equals(e.getPropertyName())) {
                shotsLabel.setText("Выстрелов: " + e.getNewValue());
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupListeners() {
        startButton.addActionListener(e -> {
            gamePanel.startGame();
        });

        pauseButton.addActionListener(e -> gamePanel.togglePause());
        stopButton.addActionListener(e -> gamePanel.stopGame());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}