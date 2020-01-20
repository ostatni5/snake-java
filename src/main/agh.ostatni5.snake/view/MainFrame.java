package agh.ostatni5.snake.view;


import agh.ostatni5.snake.core.Options;

import javax.swing.*;

public class MainFrame extends JFrame {
    private StartPanel startPanel;
    private JButton clearButton;
    private SimulationPanel [] simulationPanels;
    public MainFrame() {
        super("Evolution of moving creatures");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        startPanel = new StartPanel(this);
        add(startPanel);
        clearButton = new JButton("CLEAR SIMULATIONS AND SAVE HISTORY");
        add(clearButton);
        clearButton.setVisible(false);
        clearButton.addActionListener(actionEvent -> {
            clearButton.setVisible(false);
            startPanel.setVisible(true);
            for (SimulationPanel simulationPanel : simulationPanels) {
                simulationPanel.willBeClosed();
                remove(simulationPanel);
            }
            pack();
        });
    }

    public void startSimulations(Options options){
        clearButton.setVisible(true);
        simulationPanels = new SimulationPanel[options.values[8]];
        for (int i = 0; i < simulationPanels.length; i++) {
            simulationPanels[i]= new SimulationPanel(options);
            add(simulationPanels[i]);
        }
        pack();
    }


}