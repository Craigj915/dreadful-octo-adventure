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
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gamePanel.getGame().getPlayer().setJumping(true);
            case KeyEvent.VK_DOWN -> gamePanel.getGame().getPlayer().setFalling(true);
            case KeyEvent.VK_LEFT -> gamePanel.getGame().getPlayer().setLeft(true);
            case KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlayer().setRight(true);
            case KeyEvent.VK_E -> gamePanel.getGame().getPlayer().setInteracting(true);
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setAttacking(true);
            case KeyEvent.VK_Q -> gamePanel.getGame().getPlayer().setHeavyAttacking(true);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> gamePanel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlayer().setRight(false);
        }
    }
}
