package agh.ostatni5.snake.view;

import agh.ostatni5.snake.core.Creature;
import agh.ostatni5.snake.core.Options;
import agh.ostatni5.snake.core.WorldMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class SimulationPanel extends JPanel {
    private Options options;

    private boolean runThread= true;
    private GridBagConstraints c;
    private MyLabel jLabel;
    private GameCanvas gameCanvas;
    private JButton markDominantButton;
    private JButton stopButton;
    private WorldMap worldMap;
    private JButton watcherButton;
    private JComboBox<String> idCombo;
    private JComboBox<Object> idInfoCombo;
    private MyLabel historyOfCreatureLabel;
    private JButton showInfoButton;
    private MyLabel genomeLabel;
    private JPanel moreActionsPanel;

    private AtomicReference<Boolean> showInfo = new AtomicReference<>(false);
    private AtomicReference<Boolean> simulationRunning = new AtomicReference<>(false);


    public SimulationPanel(Options options) {
        this.options = options;
        setLayout(new GridBagLayout());
        c= new GridBagConstraints();
        init();
    }

    private void init() {
        worldMap = new WorldMap(options);
        initComponents();
        simulationRunning.set(true);
        Thread simulationLoop = new Thread(() -> {
            try {
                for (int i = 0; i < options.values[9] && runThread; i++) {
                    if (simulationRunning.get()) {
                        worldMap.nextDay();

                        gameCanvas.repaint();
                    } else {
                        i--;
                    }
                    Thread.sleep(1000 / 24);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        simulationLoop.start();
    }

    public void willBeClosed()
    {

        runThread = false;
    }

    private void initComponents()
    {
        gameCanvas = new GameCanvas(worldMap, options.values[10]);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(gameCanvas,c);

    }

}
