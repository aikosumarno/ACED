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
 
### Instructions for Grader:
- You can generate the first required action related to the user story 
"adding multiple cards to a deck" by 
  1.  Click on the "add new deck" button to create a new deck or
      if you want to add a new card to an existing deck by first 
      clicking on the "load existing collection" button.
  2. Then, you will see that new buttons beneath new decks are created. 
     These buttons will redirect you to a new frame according to the 
     deck name you have chosen. 
  3. A new frame with your chosen deck will pop up where you can click on 
     the "add card" button. 
  4. Next, you will be prompted to add the new question and new answer. 
  5. Now, if you continue to click on the "next card" button, you should 
     be able to see your new card.
- You can generate the second required action related to the user story 
  "viewing the cards in a deck" by:
  1. Click on "load existing collection"
  2. Click on any of the "deck name buttons" below the text your deck 
  3. You should be redirected to a new frame corresponding to your chosen deck
  4. Click on next and show answer to view all of the cards in your deck
- You can locate my visual component by running the application
  - upon running the application, you will see a splash screen with a loading bar 
    indicating that the app is loading. 
- You can save the state of my application by clicking on the "save collection" 
  button on the main menu. 
- You can reload the state of my application by clicking on the "load existing collection" 
  button on the main menu.

### Reference List:
- CPSC210. (2024, February 15). CPSC210/JsonSerializationDemo:
    Simple application to illustrate JSON serialization. 
    GitHub Enterprise. https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
- Using Swing Components: Examples (The JavaTM Tutorials > 
  Creating a GUI with Swing > Using Swing Components). (2022). 
  Oracle.com. https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
- CPSC210. (2023, September 5). CPSC210/AlarmSystem. 
  GitHub Enterprise. https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
