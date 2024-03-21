package ui;

import model.Card;
import model.Collection;
import model.Deck;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

// Flashcard application
public class FlashcardApp extends JFrame {
    private static final String JSON_STORE = "./data/collection.json";
    private Collection collection;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Scanner input;
    private JLabel deckslbl;

    private JMenuBar menuBar;
    private JMenu add;
    private JMenu select;
    private JMenu save;
    private JMenu load;

    private JDesktopPane desktop;

    // EFFECTS: runs the flashcard application
    public FlashcardApp() {
        input = new Scanner(System.in);
        collection = new Collection("Aiko's Flashcard Collection");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());

        initializeGraphics();
//        addButtonPanel();
//        collectionMenu();
        runFlashcard();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this FlashcardApp will operate, and populates the buttons to be used
    //           to manipulate this flashcard collection
    private void initializeGraphics() {
        setTitle("ACED");
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(800,500));
        setBackground(new Color(205, 239, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        deckslbl = new JLabel(collection.getName());
        deckslbl.setPreferredSize(new Dimension(200,30));
        add(deckslbl);

        Font font = new Font("Nunito", Font.BOLD, 20);
        UIManager.put("Menu.font", font);

        menuBar = new JMenuBar();
        add = new JMenu("Add");
        select = new JMenu("Select");
        save = new JMenu("Save");
        load = new JMenu("Load");

        menuBar.add(add);
        menuBar.add(select);
        menuBar.add(save);
        menuBar.add(load);
        setJMenuBar(menuBar);

//        Font font = new Font("Rubik Doodle Shadow", Font.PLAIN, 20);
//        UIManager.put()
    }

    /**
     * Helper to add action buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
//        buttonPanel.add(new JButton(new addNewDeckAction()));
//        buttonPanel.add(new JButton(new LoadCollectionAction()));
//        buttonPanel.add(new JButton(new selectDeckAction()));
//        buttonPanel.add(new JButton(new saveCollectionAction()));
        add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Adds menu bar.
     */
    private void collectionMenu() {

        menuBar = new JMenuBar();
        add = new JMenu("Add");
        select = new JMenu("Select");
        save = new JMenu("Save");
        load = new JMenu("Load");

        menuBar.add(add);
        menuBar.add(select);
        menuBar.add(save);
        menuBar.add(load);

        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all buttons
    private void createTools() {
//        JPanel buttonArea = new JPanel();
//        buttonArea.setLayout(new GridLayout(0,1));
//        buttonArea.setSize(new Dimension(0, 0));
//        add(buttonArea, BorderLayout.SOUTH);
//
//        Card cra = new RectangleTool(this, toolArea);
//        tools.add(rectTool);
//
//        ShapeTool ovalTool = new OvalTool(this, toolArea);
//        tools.add(ovalTool);
//
//        MoveTool moveTool = new MoveTool(this, toolArea);
//        tools.add(moveTool);
//
//        ResizeTool resizeTool = new ResizeTool(this, toolArea);
//        tools.add(resizeTool);
//
//        DeleteTool deleteTool = new DeleteTool(this, toolArea);
//        tools.add(deleteTool);
//
//        PlayShapeTool playShapeTool = new PlayShapeTool(this, toolArea);
//        tools.add(playShapeTool);
//
//        PlayDrawingTool playDrawingTool = new PlayDrawingTool(this, toolArea);
//        tools.add(playDrawingTool);
//
//        setActiveTool(rectTool);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFlashcard() {
        boolean keepStudying = true;
        String command;

        init();

        while (keepStudying) {
            displayCollectionMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepStudying = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\n Great job studying today!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addDeck();
        } else if (command.equals("e")) {
            selectDeck();
        } else if (command.equals("l")) {
            loadCollection();
        } else if (command.equals("s")) {
            saveCollection();
        } else {
            System.out.println("Choice invalid.");
        }
    }

    // EFFECTS: displays menu of decks users can choose from in collection or create a new deck
    private void displayCollectionMenu() {
        System.out.println("\nMenu:");
        System.out.println("\te -> select existing deck");
        System.out.println("\ta -> add new deck");
        System.out.println("\tl -> load existing collection from file");
        System.out.println("\ts -> save collection to file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes collection
    private void init() {
        collection = new Collection("Aiko's Flashcard Collection");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options in deck class to user
    private void displayDeckMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> study card");
        System.out.println("\ta -> add card");
        System.out.println("\te -> edit card");
        System.out.println("\td -> delete card");
        System.out.println("\tr -> return to collection");
    }

    // MODIFIES: this
    // EFFECTS: adds a new Deck
    private void addDeck() {
        System.out.println("\nEnter Deck Name: ");
        String name = input.next();
        Deck newDeck = new Deck(name);
        collection.addNewDeck(newDeck);
        System.out.println(name + " deck has been added to collection.");
//        add(newDeck, BorderLayout.CENTER);
    }

    // EFFECTS: prompts user to select an existing deck and redirects to their choice
    private void selectDeck() {
        int choice = -1;

        if (collection.getCollection().size() == 0) {
            System.out.println("You have no existing decks.");
        } else {
            while (choice > collection.getCollection().size() || choice < 0) {
                int count = 1;
                System.out.println("Decks: ");
                for (Deck current : collection.getCollection()) {
                    System.out.println(count + ". " + current.getName());
                    count++;
                }
                System.out.println("\nEnter the deck number you want to select or 0 to return to main menu: ");
                choice = input.nextInt();

                if (choice >= 1 && choice <= collection.getCollection().size()) {
                    viewDeck(collection.getCollection().get(choice - 1));
                } else if (choice != 0) {
                    System.out.println("Deck number is invalid!");
                }
            }
        }
    }

    // EFFECTS: prints out all of the cards in the chosen deck
    private void viewDeck(Deck deck) {
        int num = 1;

        System.out.println("\nCards in " + deck.getName() + ":");
        for (Card current: deck.viewDeck()) {
            System.out.println(num + ". " + current.getQuestion() + ": " + current.getAnswer());
            if (!current.getStatus()) {
                System.out.println("\t status: studied " + current.getStudyCounter() + " time");
            } else {
                System.out.println("\t status: mastered");
            }

            num++;
        }

        selectDeckActions(deck);
    }

    // EFFECTS: prints and prompts user to select an action and redirects to their choice
    private void selectDeckActions(Deck deck) {
        String choice = "";
        boolean validChoice = false;

        while (!validChoice) {
            displayDeckMenu();
            choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("a") || choice.equals("e") || choice.equals("d")
                    || choice.equals("r") || choice.equals("s")) {
                validChoice = true;
            }
        }
        if (choice.equals("s") && deck.getSize() == 0) {
            System.out.println("No cards to study.");
            viewDeck(deck);
        } else if (choice.equals("s")) {
            selectStudyMethod(deck);
        } else if (choice.equals("a")) {
            addCard(deck);
        } else if (choice.equals("e")) {
            editCard(deck);
        } else if (choice.equals("d")) {
            deleteCard(deck);
        }
    }

    // EFFECTS: prints and prompts user to select a study method and redirects to their choice
    private void selectStudyMethod(Deck deck) {
        int choice = 0;

        while (choice <= 0 || choice > 2) {
            System.out.println("\nChoose your study method:");
            System.out.println("1. Review a single card");
            System.out.println("2. Review the entire deck");
            System.out.println("Enter Choice: ");
            choice = input.nextInt();
            if (choice < 0 || choice > 2) {
                System.out.println("Invalid option!");
            } else if (choice == 1) {
                studyCard(deck);
            } else if (choice == 2) {
                studyEntireDeck(deck);
            }

        }
    }

    // REQUIRES: deck.getSize() >= 1
    // MODIFIES: card in chosen deck
    // EFFECTS: print question of chosen card and updates card study counter and status if answer is entered correctly
    private void studyCard(Deck deck) {
        int num = 0;
        String answer;

        while (num <= 0 || num > deck.getSize()) {
            System.out.println("\nEnter card number you would like to study: ");
            num = input.nextInt();
            if (num <= 0 || num > deck.getSize()) {
                System.out.println("Invalid card number!");
            }
        }

        System.out.println("\n" + deck.viewDeck().get(num - 1).getQuestion());
        System.out.println("What is the answer to the question above? ");
        answer = input.next();
        if (answer.equals(deck.viewDeck().get(num - 1).getAnswer())) {
            System.out.println("That's correct!");
            deck.viewDeck().get(num - 1).updateStudyCounter();
            deck.viewDeck().get(num - 1).updateStatus();
        } else {
            System.out.println("Incorrect Answer.");
            System.out.println("The correct answer is " + deck.viewDeck().get(num - 1).getAnswer());
        }
        viewDeck(deck);
    }

    // REQUIRES: deck.getSize() >= 1
    // MODIFIES: cards in chosen deck
    // EFFECTS: prints question of every card in deck, checks user's answer
    //           and updates card study counter and status accordingly
    private void studyEntireDeck(Deck deck) {
        int score = 0;
        int num = 0;
        String answer;
        List<Card> cards = deck.viewDeck();
        for (Card c : cards) {
            System.out.println("\nQuestion: " + c.getQuestion());
            System.out.println("Enter Answer: ");
            answer = input.next();
            if (answer.equals(c.getAnswer())) {
                score++;
                System.out.println("Correct!");
                deck.viewDeck().get(num).updateStudyCounter();
                deck.viewDeck().get(num).updateStatus();
            } else {
                System.out.println("Incorrect!");
                System.out.println("Correct Answer: " + c.getAnswer());
            }
            num++;
        }
        System.out.println("You got " + score + " out of " + cards.size() + " questions right.");
        viewDeck(deck);
    }

    // MODIFIES: adds card to deck
    // EFFECTS: adds a new card to chosen deck
    private void addCard(Deck deck) {
        System.out.println("\nEnter Question: ");
        String question = input.next();
        System.out.println("Enter Answer: ");
        String answer = input.next();
        Card newCard = new Card(question, answer);
        deck.addCard(newCard);
        System.out.println("Card has been added to deck.");
        viewDeck(deck);
    }

    // REQUIRES: card exists in the deck
    // MODIFIES: changes the details of the chosen card in deck
    // EFFECTS: edits the details of the card in the deck
    private void editCard(Deck deck) {
        int num = 0;

        while (num <= 0 || num > deck.getSize()) {
            System.out.println("\nEnter number of card you would like to edit:");
            num = input.nextInt();
        }

        num = num - 1;
        System.out.println("Enter New Question: ");
        String newQuestion = input.next();
        System.out.println("Enter New Answer: ");
        String newAnswer = input.next();

        deck.viewDeck().get(num).editQuestion(newQuestion);
        deck.viewDeck().get(num).editAnswer(newAnswer);
        deck.viewDeck().get(num).resetStatus();
        deck.viewDeck().get(num).resetStudyCounter();
        viewDeck(deck);
    }

    // REQUIRES: card exists in the deck
    // MODIFIES: removes card from deck
    // EFFECTS: deletes card from the deck
    private void deleteCard(Deck deck) {
        int num = 0;

        while (num <= 0 || num > deck.getSize()) {
            System.out.println("\nEnter number of card you would like to delete:");
            num = input.nextInt();
        }
        num = num - 1;

        deck.deleteCard(deck.viewDeck().get(num));
        viewDeck(deck);
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

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            FlashcardApp.this.requestFocusInWindow();
        }
    }
}
