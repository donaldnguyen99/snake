import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 600;
    final int DELAY = 200;
    final int CELL_SIZE = 50;
    final int MAX_CELLS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (CELL_SIZE * CELL_SIZE);

    final int[] x = new int[MAX_CELLS];
    final int[] y = new int[MAX_CELLS];

    Random random;
    Timer timer;
    boolean running = false;

    int appleX, appleY;
    int score = 0;
    JLabel scoreLabel;
    int snakeLength = 6;
    char direction = 'R';
    boolean directionChangeAllowed = true;

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (running && directionChangeAllowed) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
                directionChangeAllowed = false;
            } else {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startGame();
                }
            }
        }
    }

    GamePanel() {

        random = new Random();

        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);

        scoreLabel = new ScoreLabel();
        this.add(scoreLabel);

        this.setLayout(null);
        this.setVisible(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    private void startGame() {
        score = 0;
        scoreLabel.setText("Score: " + score);
        snakeLength = 6;
        direction = 'R';
        directionChangeAllowed = true;

        newApple();
        for (int i = 0; i < snakeLength; i++) {
            x[i] = SCREEN_WIDTH / 2;
            y[i] = SCREEN_HEIGHT / 2;
        }
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGridlines(g);
        if (running) {
            drawSnake(g);
            drawApple(g);
        } else {
            gameOver(g);
        }
    }

    private void drawGridlines(Graphics g) {
        g.setColor(Color.darkGray);
        for (int i = 0; i < SCREEN_WIDTH / CELL_SIZE; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, SCREEN_HEIGHT);
        }
        for (int i = 0; i < SCREEN_HEIGHT / CELL_SIZE; i++) {
            g.drawLine(0, i * CELL_SIZE, SCREEN_WIDTH, i * CELL_SIZE);
        }
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeLength; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
            } else {
                g.setColor(new Color(10, 200, 20));
                g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
            }
        }
    }
    
    private void drawApple(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, CELL_SIZE, CELL_SIZE);
    }

    private void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / CELL_SIZE)) * CELL_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / CELL_SIZE)) * CELL_SIZE;
    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (directionChangeAllowed) {
    
            switch (direction) {
                case 'U':
                    y[0] = y[0] - CELL_SIZE;
                    break;
                case 'D':
                    y[0] = y[0] + CELL_SIZE;
                    break;
                case 'L':
                    x[0] = x[0] - CELL_SIZE;
                    break;
                case 'R':
                    x[0] = x[0] + CELL_SIZE;
                    break;
            }
        }
    }

    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            snakeLength++;
            score++;
            scoreLabel.setText("Score: " + score);
            newApple();
        }
    }

    private void checkCollisions() {
        for (int i = snakeLength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        if (x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        new GameOver(g, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            directionChangeAllowed = true;
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }


}
