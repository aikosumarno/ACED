package ui;

import model.Collection;
import model.Deck;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.pages.DeckUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FlashcardUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/collection.json";
    private Collection collection;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private boolean loaded = false;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel collectionButtonPanel;
    private JPanel decksDisplayPanel;
    private JButton addDeck;
    private JButton loadCollection;
    private JButton saveCollection;

    private List<String> deckNames;
    private JButton[] buttons;

    public FlashcardUI() {
        collection = new Collection("Aiko's Flashcard Collection");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        this.setTitle("ACED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800,600);

        this.getContentPane().setBackground(new Color(205, 239, 255));

        collectionHeading();
        collectionButtonsPanel();
        displayLabel();
        deckCollectionPanel();
        this.setLayout(null);
        this.setVisible(true);
    }

    /**
    helper method for Collection Menu Heading
     */
    public void collectionHeading() {
        ImageIcon img = new ImageIcon("src/main/ui/images/flashcards.png");
        Image resizedImg = img.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(resizedImg);
        JLabel collectionlbl = new JLabel();
        collectionlbl.setText(collection.getName());
        collectionlbl.setIcon(img);
        collectionlbl.setHorizontalTextPosition(JLabel.RIGHT);
        collectionlbl.setVerticalTextPosition(JLabel.CENTER);
        collectionlbl.setFont(new Font("Dialog", Font.PLAIN, 20));
        collectionlbl.setIconTextGap(15);
        collectionlbl.setVerticalAlignment(JLabel.TOP);
        collectionlbl.setHorizontalAlignment(JLabel.CENTER);
        collectionlbl.setBounds(0, 0, 800,50);
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
        this.add(decksDisplayPanel);
    }

    /**
     * helper method to display decks in collection
     */
    public void displayDecks() {
        deckNames = collection.getDeckNames();
        buttons = new JButton[deckNames.size()];

        if (!loaded) {
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton(deckNames.get(i));
                buttons[i].setSize(180, 50);
                buttons[i].setFont(new Font("Dialog", Font.PLAIN, 20));
                buttons[i].addActionListener(this);
                decksDisplayPanel.add(buttons[i]);
                decksDisplayPanel.setVisible(true);
            }
            loaded = true;
        }
        decksDisplayPanel.revalidate();
        decksDisplayPanel.repaint(); // updates the panel
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: loads collection from file
    private void loadCollection() {
        try {
            collection = jsonReader.read();
            System.out.println("Loaded " + collection.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the collection to file
    private void saveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
            System.out.println("Saved " + collection.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new Deck
    private void addDeck() {
        String name = JOptionPane.showInputDialog("Enter Deck Name: ");
        Deck newDeck = new Deck(name);
        collection.addNewDeck(newDeck);
        System.out.println(name + " deck has been added to collection.");
        JButton newDeckButton = new JButton(name);
        newDeckButton.setSize(180, 50);
        newDeckButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        newDeckButton.addActionListener(this);
        decksDisplayPanel.add(newDeckButton);
        decksDisplayPanel.setVisible(true);
        decksDisplayPanel.revalidate();
        decksDisplayPanel.repaint(); // updates the panel
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        new FlashcardUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDeck) {
            addDeck();
        } else if (e.getSource() == loadCollection) {
            loadCollection();
            displayDecks();
        } else if (e.getSource() == saveCollection) {
            saveCollection();
        } else if (collection.getDeckNames().contains(e.getActionCommand())) {
            DeckUI chosenDeck = null;
            System.out.println("testing buttons");
            String action = e.getActionCommand();
            List<String> deckNames = collection.getDeckNames();
            for (int i = 0; i < deckNames.size(); i++) {
                if (deckNames.get(i).equals(action)) {
                    chosenDeck = new DeckUI(collection.getCollection().get(i));
                }
            }
            chosenDeck.setVisible(true);
        }
    }
}
