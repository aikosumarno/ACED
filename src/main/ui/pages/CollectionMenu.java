package ui.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectionMenu implements ActionListener {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu add;
    private JMenu select;
    private JMenu save;
    private JMenu load;

    public CollectionMenu() {
        frame = new JFrame();
        frame.setTitle("ACED");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        add = new JMenu("Add");
        select = new JMenu("Select");
        save = new JMenu("Save");
        load = new JMenu("Load");

        menuBar.add(add);
        menuBar.add(select);
        menuBar.add(save);
        menuBar.add(load);
        frame.setJMenuBar(menuBar);

        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();
            String text = item.getText();
            System.out.println(text);
        }
    }

    public static void main(String[] args) {
        new CollectionMenu();
    }
}
