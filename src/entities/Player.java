package entities;

import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.Game.SCALE;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 22;
    private int playerAction = SPAWN;
    private boolean left, right;
    private boolean moving, interacting, spawned, jumping, falling, attacking, heavyAttacking = false;
    private float playerSpeed = 1.0f;
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }
    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex],(int)x,(int)y,(int) (96 * SCALE), (int) (80 * SCALE),null);
    }
    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(playerAction)) {
                animIndex = 0;
                attacking = false;
                heavyAttacking = false;
                spawned = true;
                falling = false;
                jumping = false;
                interacting = false;
            }
        }
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    public void setHeavyAttacking(boolean heavyAttacking) {
        this.heavyAttacking = heavyAttacking;
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }
    private void setAnimation() {
        int startAnimation  = playerAction;

        if (!spawned) {
            left = false;
            right = false;
            falling = false;
            jumping = false;
            attacking = false;
            heavyAttacking = false;
            interacting = false;
            playerAction = SPAWN;
        } else {
            playerAction = IDLE;
            if (moving && left == true) {
                interacting = false;
                playerAction = RUN_LEFT;
            } else if (moving && right == true){
                interacting = false;
                playerAction = RUN_RIGHT;
            }
            if (jumping == true && right == true) {
                attacking = false;
                heavyAttacking = false;
                interacting = false;
                playerAction = JUMP;
            } else if (jumping == true && left == true){
                attacking = false;
                heavyAttacking = false;
                interacting = false;
                playerAction = JUMP_LEFT;
            } else if (jumping == true) {
                attacking = false;
                heavyAttacking = false;
                interacting = false;
                playerAction = JUMP;
            }
            if (falling == true && right == true){
                attacking = false;
                heavyAttacking = false;
                interacting = false;
                playerAction = FALL;
            } else if (falling == true && left == true){
                attacking = false;
                heavyAttacking = false;
                interacting = false;
                playerAction = FALL_LEFT;
            } else if (falling == true) {
                attacking = false;
                heavyAttacking = false;
                interacting = false;
                playerAction = FALL;
            }
            if (interacting) {
                moving = false;
                playerAction = INTERACT;
            }
            if (attacking == true && right == true) {
                interacting = false;
                playerAction = ATTACK_2;
            } else if (attacking == true && left == true){
                interacting = false;
                playerAction = ATTACK_2_LEFT;
            }
            if (heavyAttacking == true && right == true) {
                interacting = false;
                playerAction = ATTACK_1;
            } else if (heavyAttacking == true && left == true){
                interacting = false;
                playerAction = ATTACK_1_LEFT;
            } else if (heavyAttacking == true) {
                interacting = false;
                playerAction = ATTACK_1;
            }
        }
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        animTick = 0;
        animIndex = 0;
    }

    private void updatePosition() {

        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }
        if (jumping) {
            y -= playerSpeed;
            moving = true;
        } else if (falling) {
            y += playerSpeed;
            moving = true;
        }
    }
    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[14][26];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 96,i * 80,96,80);
            }
        }
    }
    public void resetBooleans() {
        left = false;
        right = false;
    }
//    public boolean isLeft() {
//        return left;
//    }
    public void setLeft(boolean left) {
        this.left = left;
    }
//    public boolean isRight() {
//        return right;
//    }
    public void setRight(boolean right) {
        this.right = right;
    }
}