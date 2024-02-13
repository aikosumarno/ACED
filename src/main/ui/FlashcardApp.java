package ui;

import model.Card;
import model.Collection;
import model.Deck;

import java.util.Scanner;

public class FlashcardApp {
    private Collection collection;

    private Scanner input;

    public FlashcardApp() {
        runFlashcard();
    }

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
        } else if (command.equals("s")) {
            selectDeck();
        } else {
            System.out.println("Choice invalid.");
        }
    }

    // EFFECTS: displays menu of decks users can choose from in collection or create a new deck
    private void displayCollectionMenu() {
        System.out.println("\nMenu:");
        System.out.println("\ts -> select existing deck");
        System.out.println("\ta -> add new deck");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes collection
    private void init() {
        collection = new Collection();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options in deck class to user
    private void displayDeckMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add card");
        System.out.println("\te -> edit card");
        System.out.println("\td -> delete card");
        System.out.println("\tr -> return to collection");
    }

    // MODIFIES: this
    // EFFECTS: adds a new Deck
    private void addDeck() {
        System.out.println("Enter Deck Name: ");
        String name = input.next();
        Deck newDeck = new Deck(name);
        collection.addNewDeck(newDeck);
        System.out.println(name + " deck has been added to collection.");
    }

    // EFFECTS: prompts user to select an existing deck and redirects to their choice
    private void selectDeck() {
        int choice = 0;


        while (choice > collection.getCollection().size() + 1 || choice <= 0) {
            System.out.println("\n1. return to main menu");
            int count = 2;
            for (Deck current: collection.getCollection()) {
                System.out.println(count + ". " +  current.getName());
                count++;
            }
            choice = input.nextInt();
        }

        if (choice != 1) {
            viewDeck(collection.getCollection().get(choice - 2));
        }
    }

    // EFFECTS: prints out all of the cards in the chosen deck
    private void viewDeck(Deck deck) {
        int num = 1;

        for (Card current: deck.viewDeck()) {
            System.out.println(num + ". " + current.getQuestion() + ": " + current.getAnswer());
            num++;
        }

        selectDeckActions(deck);
    }

    private void selectDeckActions(Deck deck) {
        String choice = "";
        boolean validChoice = false;

        while (!validChoice) {
            displayDeckMenu();
            choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("a") || choice.equals("e") || choice.equals("d") || choice.equals("r")) {
                validChoice = true;
            }
        }

        if (choice.equals("a")) {
            addCard(deck);
        } else if (choice.equals("e")) {
            editCard(deck);
        } else if (choice.equals("d")) {
            deleteCard(deck);
        }
    }

    private void addCard(Deck deck) {
        System.out.println("Enter Question: ");
        String question = input.next();
        System.out.println("Enter Answer: ");
        String answer = input.next();
        Card newCard = new Card(question, answer);
        deck.addCard(newCard);
        System.out.println("Card has been added to deck.");
        viewDeck(deck);
    }

    private void editCard(Deck deck) {
        int num = 0;

        while (num <= 0 || num > deck.getSize()) {
            System.out.println("Enter number of card you would like to edit:");
            num = input.nextInt();
        }

        num = num - 1;
        System.out.println("Enter New Question: ");
        String newQuestion = input.next();
        System.out.println("Enter New Answer: ");
        String newAnswer = input.next();

        deck.viewDeck().get(num).editQuestion(newQuestion);
        deck.viewDeck().get(num).editAnswer(newAnswer);
        viewDeck(deck);
    }

    private void deleteCard(Deck deck) {
        int num = 0;

        while (num <= 0 || num > deck.getSize()) {
            System.out.println("Enter number of card you would like to delete:");
            num = input.nextInt();
        }
        num = num - 1;

        deck.deleteCard(deck.viewDeck().get(num));
        viewDeck(deck);
    }
}
