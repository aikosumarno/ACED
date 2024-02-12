package ui;

import model.Card;
import model.Collection;
import model.Deck;

import java.util.ArrayList;
import java.util.Scanner;

public class FlashcardApp {
    private Card happy;
    private Card sad;
    private Card angry;
    private Card curious;
    private Card love;
    private Card maths;
    private Card science;
    private Deck emotions;
    private Deck subjects;
    private Collection collection;

    private Scanner input;

    public FlashcardApp() {
        runFlashcard();
    }

    private void runFlashcard() {
        boolean keepStudying = true;
        String command = null;

        init();

        while (keepStudying) {
            displayDeckMenu();
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
        System.out.println("\ts -> select existing card");
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
        String name = input.nextLine();
        Deck newDeck = new Deck(name);
        collection.addNewDeck(newDeck);
    }

    // EFFECTS: prompts user to select an existing deck and returns it
    private Deck selectDeck() {
        int choice = 0;


        while (choice > collection.getCollection().size() || choice <= 0) {
            int count = 1;
            for (Deck current: collection.getCollection()) {
                System.out.println("\n" + count + "->" +  current.getName());
                count++;
            }
            choice = input.nextInt();
        }

        return collection.getCollection().get(choice - 1);
    }
}
