import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class game extends JPanel implements ActionListener, KeyListener {
    private int paddleX = 200;
    private int ballX = 200;
    private int ballY = 100;
    private int ballXSpeed = 2;
    private int ballYSpeed = 2;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private Timer timer;

    public game() {
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the paddle
        g.setColor(Color.BLUE);
        g.fillRect(paddleX, 350, 100, 10);

        // Draw the ball
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (leftPressed && paddleX > 0) {
            paddleX -= 2;
        }
        if (rightPressed && paddleX < 400) {
            paddleX += 2;
        }

        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Ball collision with walls
        if (ballX <= 0 || ballX >= 380) {
            ballXSpeed = -ballXSpeed;
        }

        // Ball collision with paddle
        if (ballY >= 340 && ballX >= paddleX && ballX <= paddleX + 100) {
            ballYSpeed = -ballYSpeed;
        }

        // Ball out of bounds
        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed;
        }

        // Ball falls off the screen (game over)
        if (ballY >= 400) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over!");
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game");
        game game = new game();
        frame.add(game);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}