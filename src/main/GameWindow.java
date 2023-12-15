package main;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
public class GameWindow {
    private JFrame frame;
    public GameWindow(GamePanel gamePanel) {

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                System.out.println("BAI~ BAI~ !!");
            }
        });
    }
}