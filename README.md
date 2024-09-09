# Blackjack Card Counting Trainer

## Abstract

This application will serve 2 overarching purposes: 
- Allow the user to play blackjack for entertainment 
- Train card counting and calculating the true count

## Introduction

Card counting in Blackjack is a proven method for gaining an edge at the casino. While the advantage is marginal and can often backfire due to variance, it is a unique skill that serves more as a good omen than a gurantee of making money during casino trips. I am creating this project as a memento of being 19 and pave the way for anyone who wants to delve into the world of casino gambling. (This README file assumes that the reader is familiar with the caveats of Blackjack)

## Personal Interest

A month before my 19th birthday, I came upon a book titled "How to Win at Casino Gambling" which outlined card counting and blackjack basic strategy. Since then, the 2 months following my birthday were a rollercoaster of emotions as my friends and I had countless outings at the casino. However, I am glad to say that we are all still net positive despite all the ups and downs. Card counting has not only allowed me to enjoy the perks of being legal age, but it has also helped me strengthen friendships and create unforgettable memories.

## What is Card Counting?
Blackjack is often played with 2-6 decks. In these decks, there are a set amount of high and low cards. Since that the dealer must keep hitting until they reach a total of 17 or higher, the more high cards there are in the deck, the more of a chance they bust; hence, the player will gain a marginal edge. High cards are assigned a positive value and low cards are assigned a negative value. Card counting keeps track of all cards that have been dealt and a high count signals to a player when it is a good time to bet big.

The cards are given the following values: - [2-6] are considered +1 - [7-9] are considered 0 - [10, J, Q, K, A] are considered -1

While it is easy to find the running count (the count since the beginning of the shoe being delt), beginners often overlook the fact that it is the _true count_ that really matters. The true count is calculated by dividing the running count by the amount of decks remaining and rounding it to the nearest integer. This provides a true reflection of the player's edge against the House.

Best of Luck,

Yours truly

## User Stories

- As a user, I want to be able to pick my starting balance and the amount of decks I want to play with
- As a user, I want to be able to choose my betting size
- As a user, I want to be able to decide whether to hit, stand, double my hand
- As a user, I want to be able to check the current running and true count
- As a user, I want to be able to check my current balance;
- As a user, I want to be able to save my game in it's current state, with the same player and dealer hands, balance, decks, and running count and true count
- As a user, I want to be able to load my game in it's saved state, with the saved player and dealer hands, balance, decks, and running count and true count

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by choosing to create a new game, setting a player balance > 0, and then choosing the number of x (decks consisting of 52 cards), which can then be added arbitrarily many times to y (the GameDeck) which keeps a list of all these cards.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by choosing to place a bet, inputting a bet > 0, and choosing the hit button an arbitrary amount of times to remove x (a card) from y (the GameDeck). After the hand is done, the player and dealer will continue drawing cards from the same GameDeck in future hands.
- You can locate my visual component of the cards after placing the bet, hitting, doubling or standing.
- You can locate my visual component of user data throughout the project by using the multiple "check" type buttons in the JPanel.
- You can save the state of my application by choosing the save either in the main menu or mid game.
- You can reload the state of my application by choosing to load right when the application is ran, in the main menu, game menu, or mid game.

## Sample Event Log

Wed Aug 07 02:26:33 PDT 2024
A game deck consisting of 3 decks (156 cards) has been created!
Wed Aug 07 02:26:35 PDT 2024
A game deck consisting of 156 cards has been saved!
Wed Aug 07 02:26:36 PDT 2024
A game deck consisting of 156 cards has been loaded
Wed Aug 07 02:26:40 PDT 2024
A king_of_spades has been dealt to the Player!
Wed Aug 07 02:26:40 PDT 2024
A 2_of_hearts has been dealt to the Dealer!
Wed Aug 07 02:26:44 PDT 2024
Game Over! Game deck has been cleared!
Wed Aug 07 02:26:46 PDT 2024
A game deck consisting of 156 cards has been loaded

## Project Reflection

- Looking at my UML class diagram, I think that I could've implemented the buttons in Phase 3 better as there is a lot of coupling. Aside from that, I think that I did well following the single responsibility principle and increasing cohesion. A consideration I could've implemented is to log an event from the root of the class it is called from. For instance, I log an event for a player or dealer recieving a card in the blackjack class instead of their respective classes. Nonetheless, that is where the deal card is called so my implementation helps with debugging. Considering my project overall, I believe that my final product is what I had envisioned from the start. Perhaps I could include a label for "Dealer Hand" during a game. I could also implement a time delay between dealer moves so the updates to the status JLabel can be read, but these features are merely stylistic changes. Altogether, this project was a great learning experience, but I wished that we went more in depth with persistence in class.
