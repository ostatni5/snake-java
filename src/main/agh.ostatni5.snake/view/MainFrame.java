package agh.ostatni5.snake.view;


import agh.ostatni5.snake.core.KeyMap;
import agh.ostatni5.snake.core.Options;
import agh.ostatni5.snake.core.Rotation;
import agh.ostatni5.snake.core.WorldMap;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    private StartPanel startPanel;
    private JButton clearButton;
    private KeyMap keyMap = new KeyMap();
    private WorldMap worldMap;
    private GameCanvas gameCanvas;
    private StatsCanvas statsCanvas;
    private boolean running = true;
    private boolean paused = false;

    public MainFrame() throws InterruptedException {
        super("Life of moving snake");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        init();
        addKeyListener(this);
    }

    private void init() {
        worldMap = new WorldMap(40, 40, false, 5);
        gameCanvas = new GameCanvas(worldMap);
        worldMap.bindGameCanvas(gameCanvas);
        add(gameCanvas);
        statsCanvas = new StatsCanvas(worldMap, gameCanvas);
        add(statsCanvas);

    }

    public void run() {
        Thread threadLoop = new Thread(() -> {
            while (running) {
                if (!paused)
                    if (keyMap.getLastDirection() != null)
                        worldMap.next(keyMap.getLastDirection());
                gameCanvas.repaint();
                try {
                    Thread.sleep(1000 / 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadLoop.start();
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (!paused)
            keyMap.press(keyEvent.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 80)
            togglePause();
        keyMap.release(keyEvent.getKeyCode());

    }

    private void togglePause() {
        paused = !paused;
        gameCanvas.setPaused(paused);
    }
}