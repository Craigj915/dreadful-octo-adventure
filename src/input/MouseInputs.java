package input;

import main.GamePanel;

import java.awt.event.*;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("MOUSE DRAGGED");
    }
    @Override
    public void mouseMoved(MouseEvent e) {
//        gamePanel.setRectPos(e.getX(), e.getY());
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("MOUSE CLICKED");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("MOUSE PRESSED");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("MOUSE RELEASED");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("MOUSE ENTERED");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("MOUSE EXITED");
    }
}