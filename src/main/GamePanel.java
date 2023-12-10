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
    private BufferedImage img;
    private BufferedImage[] spawnAnim, idleAnim;
    private int animTick, animIndex, animSpeed = 30;
    public GamePanel() {
        mouseInputs= new MouseInputs(this);

        importImage();
        loadAnimations();

        setPannelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void loadAnimations() {
        spawnAnim = new BufferedImage[19];
        // Load the first set of images (0 to 9) with y-coordinate 6*64
        for (int i = 0; i < 10; i++) {
            spawnAnim[i] = img.getSubimage(i * 64,6 * 64,64,64);
        }
        // Load the second set of images (10 to 18) with y-coordinate 7*64
        for (int j = 10; j < 19; j++) {
            spawnAnim[j] = img.getSubimage((j - 10) * 64,7 * 64,64,64);
        }
        idleAnim = new BufferedImage[2];

        for (int i = 0; i < 2; i++) {
            idleAnim[i] = img.getSubimage(i * 64,0,64,64);
        }
    }
    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/res/FireKnight.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void updateSpawnAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= spawnAnim.length) {
                updateIdleAnim();
            }
        }
    }

    private void updateIdleAnim() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= idleAnim.length) {
                animIndex = 0;
            }
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

        updateSpawnAnimationTick();

        g.drawImage(spawnAnim[animIndex],(int)xDelta,(int)yDelta,null);

    }


}
