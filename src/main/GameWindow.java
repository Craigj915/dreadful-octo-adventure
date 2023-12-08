package main;

import javax.swing.JFrame;

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
    }


}