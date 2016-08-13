package vlpa.ch57.task2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIFrame extends JFrame {

    private JLabel countLabel;
    private JTextField enterField;

    public GUIFrame(String title) {
        super(title);
        buildGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildGUI() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //panel 1
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Enter string:"));
        enterField = new JTextField(15);
        enterField.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent ke) {
//                        System.out.println("1-type");
                    }

                    @Override
                    public void keyReleased(KeyEvent ke) {
//                        System.out.println("2-release");
                        countLabel.setText(enterField.getText().length() + "");
                    }

                    @Override
                    public void keyPressed(KeyEvent ke) {
//                        System.out.println("3-press");
                    }
                }
        );
        panel1.add(enterField);

        //panel 2
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Number of chars:"));
        countLabel = new JLabel("");
        panel2.add(countLabel);

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        add(mainPanel);
        setSize(300, 120);
        setVisible(true);
    }


}
