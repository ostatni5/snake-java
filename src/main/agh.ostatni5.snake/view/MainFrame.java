package agh.ostatni5.snake.view;


import agh.ostatni5.snake.core.KeyMap;
import agh.ostatni5.snake.core.WorldMap;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    private StartPanel startPanel = new StartPanel(this);
    private KeyMap keyMap = new KeyMap();
    private WorldMap worldMap;
    private GameCanvas gameCanvas;
    private StatsCanvas statsCanvas;
    private boolean running = true;
    private boolean paused = false;
    public int bestScore = 0;
    private boolean firstInit = true;
    private String snakeName = "Mr Snake";
    private boolean borderless = false;

    public MainFrame() throws InterruptedException {
        super("Life of moving snake");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(startPanel);
        startInit();
        addKeyListener(this);
    }

    private void startInit() {
        System.out.println("START INIT");
        startPanel.setVisible(true);
        if (!firstInit) {
            gameCanvas.setVisible(false);
            statsCanvas.setVisible(false);
        }
        pack();

    }

    private void init() {
        System.out.println("INIT");
        running = true;
        worldMap = new WorldMap(40, 40, borderless, 5, snakeName);
        gameCanvas = new GameCanvas(worldMap);
        worldMap.bindGameCanvas(gameCanvas);
        statsCanvas = new StatsCanvas(worldMap, gameCanvas, this);
        add(gameCanvas);
        add(statsCanvas);
        pack();
        if (firstInit)
            run();
        firstInit = false;
        requestFocusInWindow();
    }

    private void run() {
        System.out.println("RUN");
        Thread threadLoop = new Thread(() -> {
            while (running) {
                if (!paused)
                    if (keyMap.getLastDirection() != null)
                        worldMap.next(keyMap.getLastDirection());
                gameCanvas.repaint();
                try {
                    Thread.sleep(1000 / 8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadLoop.start();
    }

    private void restart() {
        paused = true;
        keyMap.reset();
        remove(gameCanvas);
        remove(statsCanvas);
        startInit();
        paused = false;
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (!paused )
            keyMap.press(keyEvent.getKeyCode());

        if (keyEvent.getKeyCode() == KeyEvent.VK_R)
            restart();

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 80 && worldMap != null && worldMap.getSnake().isAlive())
            togglePause();
        keyMap.release(keyEvent.getKeyCode());

    }

    private void togglePause() {
        paused = !paused;
        gameCanvas.setPaused(paused);
    }

    public void updateBestScore(int score) {
        bestScore = Math.max(bestScore, score);
    }

    public void afterConfiguration(String snakeName, boolean borderless) {
        System.out.println("AFTER CONFIGURATION");
        this.snakeName = snakeName;
        this.borderless = borderless;
        init();
    }
}