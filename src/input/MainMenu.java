package input;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {

    public JButton newGameButton(Font PixelFont) {
        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(100, 100, 140, 32);
        newGameButton.setPreferredSize(newGameButton.getPreferredSize());
        newGameButton.setFont(PixelFont);
        newGameButton.setForeground(Color.BLUE);
        newGameButton.setBackground(new Color(0, 200, 0, 100));
        newGameButton.setBorderPainted(false);
        newGameButton.addActionListener(this);
        return newGameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click events here
        System.out.println("New Game button clicked!");
    }
}
