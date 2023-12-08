package main;

import input.KeyboardInputs;
import input.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float yDelta = 100, xDelta = 100;
    private BufferedImage img, subImg;
    public GamePanel() {
        mouseInputs= new MouseInputs(this);
        importImage();
        setPannelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/res/FireKnight.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void setPannelSize() {
        Dimension size = new Dimension(1280,720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }
    public void setCharaPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        subImg = img.getSubimage(1 * 64,0,64,64);
        g.drawImage(subImg,(int)xDelta,(int)yDelta,null);

    }
}
