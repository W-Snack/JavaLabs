public class GameThread extends Thread {
    private GamePanel gamePanel;

    public GameThread(GamePanel panel) {
        this.gamePanel = panel;
    }

    @Override
    public void run() {
        while (gamePanel.isRunning()) {
            if (!gamePanel.isPaused()) {
                gamePanel.updateGame();
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}