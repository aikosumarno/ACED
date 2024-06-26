package ui;

import model.Collection;
import model.Deck;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// A Flashcard Application's Graphical User Interface
public class FlashcardUI extends JFrame implements ActionListener, WindowListener {
    private static final String JSON_STORE = "./data/collection.json";
    private Collection collection;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private boolean loaded = false;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JWindow loadingWindow;
    private JPanel logoPanel;
    private JPanel collectionButtonPanel;
    private JPanel decksDisplayPanel;
    private JButton addDeck;
    private JButton loadCollection;
    private JButton saveCollection;
    private JProgressBar progressBar;

    private Timer timer;

    private List<String> deckNames;
    private JButton[] buttons;

    private EventLog el;

    // EFFECTS: initializes the frame for the main screen
    public FlashcardUI() {
        loadingAppScreen();
        collection = new Collection("Aiko's Flashcard Collection");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        this.setTitle("ACED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH,HEIGHT);

        this.getContentPane().setBackground(new Color(205, 239, 255));

        collectionHeading();
        collectionButtonsPanel();
        displayLabel();
        deckCollectionPanel();
        this.setLayout(null);

        addWindowListener(this);
    }

    // EFFECTS: creates Collection Menu Heading
    public void collectionHeading() {
        ImageIcon img = new ImageIcon("src/main/ui/images/AcedSmallLogo.png");
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

    // EFFECTS: creates collection buttons panel
    public void collectionButtonsPanel() {
        collectionButtonPanel = new JPanel();
        collectionButtonPanel.setBackground(new Color(000435));
        collectionButtonPanel.setBounds(0,400, 800,200);
        collectionButtonPanel.setLayout(null);
        collectionButtons();
        this.add(collectionButtonPanel);
    }

    // EFFECTS: create buttons for collection menu
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

    // EFFECTS: create labels and panel for displaying the decks in your collection
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

    // EFFECTS: creates a panel to display the buttons that redirect you to the corresponding decks
    public void deckCollectionPanel() {
        decksDisplayPanel = new JPanel();
        decksDisplayPanel.setBackground(new Color(205, 239, 255));
        decksDisplayPanel.setBounds(0, 100, 800,350);
        decksDisplayPanel.setLayout(new FlowLayout());
        this.add(decksDisplayPanel);
    }

    // EFFECTS: displays the decks in your collection as buttons that will redirect you to the corresponding decks
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

    // EFFECT: creates splash screen to show that the app is loading.
    public void loadingAppScreen() {
        loadingWindow = new JWindow(this);
        loadingWindow.setSize(800, 600);
        loadingWindow.setLocationRelativeTo(null);
        loadingWindow.setVisible(true);

        logoPanel();
        loadingWindow.add(logoPanel);

        progressBar = new JProgressBar(0,100);
        progressBar.setForeground(new Color(0, 0, 128));
        loadingWindow.add(BorderLayout.PAGE_END, progressBar);
        loadingWindow.revalidate();
        loadProgressBar();
    }

    // EFFECTS: creates the loading bar on the splash screen when loading the app
    public void loadProgressBar() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = progressBar.getValue();
                if (x == 100) {
                    loadingWindow.dispose();
                    FlashcardUI.this.setVisible(true);
                    timer.stop();
                } else {
                    progressBar.setValue(x + 8);
                }
            }
        });
        timer.start();
    }

    // EFFECTS: displays the logo on the splash screen when the app is loading
    public void logoPanel() {
        logoPanel = new JPanel();
        ImageIcon img = new ImageIcon("src/main/ui/images/AcedLogo.png");
        Image resizedImg = img.getImage().getScaledInstance(275, 365,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(resizedImg);
        JLabel label = new JLabel();
        label.setIcon(image);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        logoPanel.add(label);
        logoPanel.setBackground(new Color(205, 239, 255));
    }

    // EFFECTS: takes in user's action and acts accordingly
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: prints the events log on the console when the user closes the frame
    @Override
    public void windowClosing(WindowEvent e) {
        el = EventLog.getInstance();
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
