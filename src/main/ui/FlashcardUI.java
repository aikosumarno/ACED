package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardUI extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel collectionButtonPanel;
    private JPanel decksDisplayPanel;
    private JButton addDeck;
    private JButton loadCollection;
    private JButton saveCollection;

    private String[] questions;
    private JButton[] buttons;

    public FlashcardUI() {
        this.setTitle("ACED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800,600);

        this.getContentPane().setBackground(new Color(205, 239, 255));

        collectionhHeading();
        collectionButtonsPanel();
        displayLabel();
        deckCollectionPanel();
        this.setLayout(null);
        this.setVisible(true);
    }

    /**
    helper method for Collection Menu Heading
     */
    public void collectionhHeading() {
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
    }

    /**
     * helper method to create collection buttons panel
     */
    public void collectionButtonsPanel() {
        collectionButtonPanel = new JPanel();
        collectionButtonPanel.setBackground(new Color(000435));
        collectionButtonPanel.setBounds(0,400, 800,200);
        collectionButtonPanel.setLayout(null);

        collectionButtons();

        this.add(collectionButtonPanel);
    }

    /**
     * helper method to create buttons for collection menu
     */
    public void collectionButtons() {
        addDeck = new JButton("Add New Deck");
        addDeck.setBounds(0, 5, 800, 50);
        addDeck.addActionListener(this);
        addDeck.setBackground(new Color(205, 239, 255));
        addDeck.setFont(new Font("Dialog", Font.PLAIN, 20));
        addDeck.setFocusable(false);

        loadCollection = new JButton("Load Existing Collection");
        loadCollection.setBounds(0, 60, 800, 50);
        loadCollection.addActionListener(this);
        loadCollection.setBackground(new Color(205, 239, 255));
        loadCollection.setFont(new Font("Dialog", Font.PLAIN, 20));
        loadCollection.setFocusable(false);

        saveCollection = new JButton("Save Collection");
        saveCollection.setBounds(0, 115, 800, 50);
        saveCollection.addActionListener(this);
        saveCollection.setBackground(new Color(205, 239, 255));
        saveCollection.setFont(new Font("Dialog", Font.PLAIN, 20));
        saveCollection.setFocusable(false);

        collectionButtonPanel.add(addDeck);
        collectionButtonPanel.add(loadCollection);
        collectionButtonPanel.add(saveCollection);
    }

    /**
     * helper method to create label and panel for displaying decks
     */
    public void displayLabel() {
        JLabel deckCollectionlbl = new JLabel();
        deckCollectionlbl.setText("  Your Decks:");
        deckCollectionlbl.setFont(new Font("Dialog", Font.PLAIN, 20));
        deckCollectionlbl.setVerticalAlignment(JLabel.TOP);
        deckCollectionlbl.setHorizontalAlignment(JLabel.LEFT);
        deckCollectionlbl.setBounds(0, 50, 200,20);
        JPanel labelDisplayPanel = new JPanel();
        labelDisplayPanel.setBackground(new Color(205, 239, 255));
        labelDisplayPanel.setBounds(0, 50, 200,20);
        labelDisplayPanel.setLayout(new BorderLayout());
        labelDisplayPanel.add(deckCollectionlbl);
        this.add(labelDisplayPanel);
    }

    /**
    helper method to create panel to display decks
     */
    public void deckCollectionPanel() {
        decksDisplayPanel = new JPanel();
        decksDisplayPanel.setBackground(new Color(205, 239, 255));
        decksDisplayPanel.setBounds(0, 100, 800,350);
        decksDisplayPanel.setLayout(new FlowLayout());

        displayDecks();
        this.add(decksDisplayPanel);
    }

    /**
     * helper method to display decks in collection
     */
    public void displayDecks() {
        questions = new String[]{"Question 1", "Question 2", "Question 3"};
        buttons = new JButton[questions.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(questions[i]);
            decksDisplayPanel.add(buttons[i]);
        }
    }


    public static void main(String[] args) {
        new FlashcardUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDeck) {
            System.out.println("New Deck Created");
        } else if (e.getSource() == loadCollection) {
            System.out.println("Collection Loaded");
        } else if (e.getSource() == saveCollection) {
            System.out.println("Collection Saved");
        }
    }
}
