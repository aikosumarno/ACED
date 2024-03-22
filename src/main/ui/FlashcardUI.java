package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FlashcardUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public FlashcardUI() {
        this.setTitle("ACED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800,600);

        this.getContentPane().setBackground(new Color(205, 239, 255));

        ImageIcon img = new ImageIcon("src/main/ui/images/flashcards.png");
        Image resizedImg = img.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(resizedImg);
        JLabel collectionlbl = new JLabel();
        collectionlbl.setText("Flashcard Collection");
        collectionlbl.setIcon(img);
        collectionlbl.setHorizontalTextPosition(JLabel.RIGHT);
        collectionlbl.setVerticalTextPosition(JLabel.CENTER);
        collectionlbl.setFont(new Font("Dialog", Font.PLAIN, 20));
        collectionlbl.setIconTextGap(15);
        collectionlbl.setVerticalAlignment(JLabel.TOP);
        collectionlbl.setHorizontalAlignment(JLabel.CENTER);
        collectionlbl.setBounds(250, 0, 300,50);


        this.add(collectionlbl);
        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FlashcardUI();
    }

}
