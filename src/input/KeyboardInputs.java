package input;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            case KeyEvent.VK_UP -> gamePanel.changeYDelta(-5);
            case KeyEvent.VK_DOWN -> gamePanel.changeYDelta(5);
            case KeyEvent.VK_LEFT -> gamePanel.changeXDelta(-5);
            case KeyEvent.VK_RIGHT -> gamePanel.changeXDelta(5);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
