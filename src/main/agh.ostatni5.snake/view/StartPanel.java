package agh.ostatni5.snake.view;



import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    public StartPanel(MainFrame mainFrame) {

        Dimension dimension = new Dimension(400,100);
        setMaximumSize(dimension);
        setLayout(new GridLayout(0, 2));

        JLabel jLabel = new JLabel("Snake name");
        add(jLabel);

        JTextField snakeName = new JTextField("Mr Snake");
        add(snakeName);

        JCheckBox borderlessMap = new JCheckBox("Borderless Map");
        borderlessMap.setSelected(false);
        add(borderlessMap);

        JButton jButton = new JButton("Launch");
        add(jButton);
        jButton.addActionListener(actionEvent -> {
            setVisible(false);
            mainFrame.afterConfiguration(snakeName.getText(),borderlessMap.isSelected());
        });


    }


}
