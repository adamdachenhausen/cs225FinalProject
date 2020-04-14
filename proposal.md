# CSIS 225 Final Project

Space invaders game and Catan board game simulation

## Team members

Lindsay Clark (le03clar@siena.edu), Kate Nelligan (ke10nell@siena.edu), Adam Dachenhausen (ad10dach@siena.edu)

## Overview
We are interested in developing one game and one simulation for our final project for CSIS 225.


## Space Invaders Game (150 Total Points)
For our game, we plan to implement a version of Space Invaders.  In this game, the player operates a laser cannon by moving it horizontally across the bottom of the screen and firing at descending aliens. The aim is to defeat five rows of eleven aliens.  

This project will cover many of the requirements of the project: it will use Java Swing components and Java graphics with event-handlers for use of the keyboard (to control the cannon that fires at the aliens).  There are also several objects we will need to create to make the game work that will require a class hierarchy.

### Satifies
- Object oriented design
- Event driven programming
- Multi-threading
- Feature rich

### Milestones
- Create individual game objects using sprites (ship, alien ships, etc.)
- Create gameboard that uses these objects, as well as keep track of a score
- Add sounds to actions (death, shoot, etc.)
- Create a prompt so user can save score, to a file (and possibly send that score to a server)

### Timeline
By: 4/19/2020 - Have game objects and gameboard created (*Necessary*)
By: 4/22/2020 - Implement extra features (audio, sockets)

## Catan Simulation (150 Total Points)
For the simulation, we plan to simulate the game Settlers of Catan.  The players in the game represent settlers establishing colonies on the island of Catan. Players build settlements, cities, and roads to connect them as they settle the island. The game board, which represents the island, is composed of hexagonal tiles (hexes) of different land types, which are laid out randomly at the beginning of each game. Players build by spending resources (sheep, wheat, wood, brick and ore).  On each player's turn, two six-sided dice are rolled to determine which hexes produce resources. 

The goal of the game is to reach ten victory points. Players score one point for each settlement they own and two for each city. Catan will use Java Swing components and Java graphics with event-handlers for buttons and placing pieces on the map. We will need to keep track of each player's resources, points, settlements, cities and roads.  To run the game smoothly we will also need a class hierarchy to code this game.

### Satifies
- Object oriented design
- Event driven programming
- Multi-threading
- Data Structures
- Feature rich

### Milestones
- Create individual game objects (hexagonal tiles, resources, dice, etc.)
- Combine these into one graphical window
- **Create bot class to fill in seats, so a single person can play?

### Timeline
By: 4/27/2020 Have game objects and gameboard created (*Necessary*)
By: 4/29/2020 Have extra features added
