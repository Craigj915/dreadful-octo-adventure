package input;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Hello");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gamePanel.setDirection(UP);
            case KeyEvent.VK_DOWN -> gamePanel.setDirection(DOWN);
            case KeyEvent.VK_LEFT -> gamePanel.setDirection(LEFT);
            case KeyEvent.VK_RIGHT -> gamePanel.setDirection(RIGHT);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT -> gamePanel.setMoving(false);
        }
    }
}
