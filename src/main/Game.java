package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public final static int TILES_DEFAULT_SIZE = 16;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 80;
    public final static int TILES_IN_HEIGHT = 45;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
        System.out.println("GAME IS ALIVE!!" + "\n PLAYER IS ALIVE");
    }

    private void initClasses() {
        player = new Player(200,200);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update() {
        player.update();
    }
    public void render(Graphics g) {
        player.render(g);
    }
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long prevTime = System.nanoTime();


        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - prevTime) / timePerUpdate;
            deltaF += (currentTime - prevTime) / timePerFrame;
            prevTime = currentTime;
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    public void windowLostFocus() {
        player.resetBooleans();
    }
    public Player getPlayer() {
        return player;
    }
}