# Aced

## Your Flashcard Learning Tool

An education based application that allows users to **create flashcards and 
improve their memory recall**. Users can build their own deck of flashcards 
(i.e. listing glossary terms) and track their study progress. The goal of 
the application is to provide users with a platform to store and study their 
flashcard easily. 

### Why study using flashcards?

Flashcards are a form of learning that engages our **active recall**. It improves
our *memory retention* and helps us *build connections* with the material.
It is also useful for *visual learners* like myself or students who have a short 
retention span and have a hard time reading long texts. By implementing flashcards 
as a rewarding game, it will also build the competitive spirit within the user 
and make learning more fun. 

### User Story:
- As a user, I want to be able to add more cards into my deck of flashcards.
- As a user, I want to be able to see the cards in my current deck of flashcards.
- As a user, I want to be able to delete a card from my deck of flashcards. 
- As a user, I want to be able to edit the details of a card in my deck of flashcards.
- As a user, I want to be able to add a new deck of flashcards into my collection. 
- As a user, I want to be able to study the cards in my decks. 
- As a user, I want to be able to track how well I have studied the card.
- As a user, I want to be able to save my flashcards so that I can review it in the future. 
- As a user, I want to be able to load my previous flashcards from file. 
 
### User Instructions:
- How to add cards to a deck:
  1.  Click on the "load existing collection" button to see the available 
      decks you want to add a card to.
  2. Then, you will see that new buttons beneath new decks are created. 
     These buttons will redirect you to a new frame according to the 
     deck name you have chosen. 
  3. A new frame with your chosen deck will pop up where you can click on 
     the "add card" button. 
  4. Next, you will be prompted to add the new question and new answer. 
  5. Now, if you continue to click on the "next card" button, you should 
     be able to see your new card added to the end of the deck.
- How to view the cards in a deck:
  1. Click on "load existing collection"
  2. Click on any of the "deck name buttons" below the text your deck.
  3. You should be redirected to a new frame corresponding to your chosen deck.
  4. Click on next and show answer to view all the cards in your deck.
- Visual Component
  - upon running the application, you will see a splash screen with a loading bar 
    indicating that the app is loading. 
- How to delete cards from a deck:
  1. Click on "load existing collection".
  2. Click on the deck from which you want to delete cards from. 
  3. You should be redirected to a new frame according to your deck.
  4. Click on the "delete card" button. 
  5. A frame should pop up where you can just click on the question you 
     want to delete. 
  6. You should now see that when you keep on clicking "next" that the 
     question you have deleted is no longer visible. 
- How to edit card details:
  1. Click on "load existing collection". 
  2. Click on the deck where the card you want to edit is in. 
  3. Click on edit card. 
  4. Click on the card details you want to edit. 
  5. You will be prompted to enter the new question and answer. 
  6. Now when you look at you cards in the deck by clicking on "show answer" 
     and "next", you will see the updated cards. 
- You can save the state of my application by clicking on the "save collection" 
  button on the main menu. 
- You can reload the state of my application by clicking on the "load existing collection" 
  button on the main menu.

### Sample Event Log
Wed Apr 03 13:11:41 PDT 2024
Added deck 'emotions' to collection.

Wed Apr 03 13:11:41 PDT 2024
Added 'Define sad' card to emotions deck.

Wed Apr 03 13:11:41 PDT 2024
Added 'Define angry' card to emotions deck.

Wed Apr 03 13:11:41 PDT 2024
Added 'Define joy' card to emotions deck.

Wed Apr 03 13:11:41 PDT 2024
Added deck 'jobs' to collection.

Wed Apr 03 13:11:41 PDT 2024
Added 'What is the job of a student?' card to jobs deck.

Wed Apr 03 13:11:41 PDT 2024
Added 'What is the job of a professor?' card to jobs deck.

Wed Apr 03 13:11:41 PDT 2024
Added deck 'random' to collection.

Wed Apr 03 13:11:41 PDT 2024
Added 'Where is the main UBC campus located?' card to random deck.

Wed Apr 03 13:11:41 PDT 2024
Added 'What is the name of the business school in UBC?' card to random deck.

Wed Apr 03 13:11:41 PDT 2024
Added deck 'countries' to collection.

Wed Apr 03 13:11:41 PDT 2024
Added 'In which country is Vancouver located?' card to countries deck.

Wed Apr 03 13:11:41 PDT 2024
Added 'In which country is Bali located?' card to countries deck.

Wed Apr 03 13:11:41 PDT 2024
Loaded Aiko's Flashcard Collection collection from file.

Wed Apr 03 13:12:13 PDT 2024
Added 'In which continent is Singapore located?' card to countries deck.

Wed Apr 03 13:13:13 PDT 2024
Edited question to: 'In which province is Vancouver located?'

Wed Apr 03 13:13:13 PDT 2024
Edited answer to: 'British Columbia'

Wed Apr 03 13:13:36 PDT 2024
Deleted 'Where is the main UBC campus located?' card from randomdeck.

Wed Apr 03 13:13:44 PDT 2024
Saved Aiko's Flashcard Collection collection to file.

### Improvements to be made:
If I had more time to work on this project, I would ensure that each class follows 
the single responsibility principle. Currently, even though the UI is divided up 
into their respective frames, there are other things that they are also doing. 
For instance, the splash screen is found in the FlashcardUI as well. If 
I had more time, I would extract them into their own class. Moreover, I would 
implement exception handling to improve the usability and efficiency of my program as 
there is nothing that currently restricts the users ability to make new decks and cards. 
If I had more time, I would remove the requires clauses and implement exceptions that 
would ensure that the user is unable to create a new deck with a name that already exists. 
This would also help when I only had the console based application by not just having 
the user keep on inputting their choice until they enter a valid option by prompting 
them with a meaningful error message instead. 

### Reference List:
- Using Swing Components: Examples (The JavaTM Tutorials > 
  Creating a GUI with Swing > Using Swing Components). (2022). 
  Oracle.com. https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
