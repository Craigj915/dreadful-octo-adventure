package main;

import input.KeyboardInputs;
import input.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float yDelta = 100, xDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 24;
    private int playerAction = SPAWN;
    private int playerDirection = -1;
    private boolean moving = false;
    private boolean spawned = false;
    private boolean interacting = false;
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
        animations = new BufferedImage[10][25];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 96,i * 80,96,80);
            }
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
    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(playerAction)) {
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
    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }
    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }
    private void setAnimation() {
        if (!spawned) {
            playerAction = SPAWN;
            if (animIndex == GetSpriteAmount(SPAWN) - 1) {
                spawned = true;
            }
        } else {
            playerAction = IDLE;
            if (moving && playerDirection == LEFT) {
                playerAction = RUN_LEFT;
            } else if (moving && playerDirection == RIGHT){
                playerAction = RUN_RIGHT;
            } else if (moving && playerDirection == UP){
                // OR JUMPING
                playerAction = JUMP;
            }  else if (moving && playerDirection == DOWN){
                // OR FALLING
                playerAction = FALL;
            }
            if (interacting) {
                playerAction = INTERACT;
            }
        }
    }
    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case UP -> yDelta -=5;
                case DOWN -> yDelta +=5;
                case LEFT -> xDelta -=5;
                case RIGHT -> xDelta+=5;
            }
        }
    }
    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(animations[playerAction][animIndex],(int)xDelta,(int)yDelta,512,448,null);
    }
}
