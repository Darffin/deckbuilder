# JavaFX Roguelike Card Game

A prototype of a roguelike card-based game built using **JavaFX** and **Spring Boot**, following a modular architecture with clean separation between model, service, and controller layers.

## Key Features

- Dynamic card effects via Java Reflection
- Discard-deck reshuffle system (cards go back into the deck once it’s empty)
- UI built with JavaFX (player HP, enemy HP, and card buttons update in real-time)
- Game logic handled via Services, maintaining separation of concerns
- Modular architecture following MVC principles
- Uses **H2 Database** to persist card data and, in the future, user progress

## Tech Stack

- Java 17+
- JavaFX
- Spring Boot
- H2 Database

## Project Structure

model/ – Entities like Card, Player, Enemy

repository/ – Spring Data JPA repositories (e.g., CardRepository)

service/ – Business logic (GameService, CardService)

controller/ – JavaFX UI logic (e.g., FightController, CardController)

config/ – Configuration classes (e.g., DatabaseInitializer)

resources/ - *.fxml – JavaFX UI layouts. And application.properties

JavaFxApp.java – Main entry point combining JavaFX and Spring Boot

## Future Plans

- Add enemy types and player progression
- Add maps with stores and events
- Implement status effects and multi-turn logic
- Save/load progress with full H2 persistence
- Enhance UI polish and animations with some basic arts

