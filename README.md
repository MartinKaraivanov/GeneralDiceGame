# General Dice Game Documentation

## Project Overview

The "General Dice Game" is a Java-based game application where players roll dice, select combinations, and accumulate points. The game provides a graphical user interface (GUI) for players to interact with the game board.

## Project Structure

The project consists of several classes, each responsible for specific aspects of the game. Here is an overview of the main classes and their functionalities:

### 1. Main Class (GeneralDiceGame.java)

- Entry point of the application.
- Manages the game's main menu and controls game flow.

### 2. GameBoard Class (GameBoard.java)

- Represents the game board's graphical user interface.
- Manages the state of the game, including dice rolling, combination selection, and player scoring.
- Handles user interactions and displays game components.
- Allows the player to start a new game, exit the game, or view the leaderboard.
- Implements methods to run the game, set the player's name, save player scores, and display the leaderboard.

### 3. Dice Class (Dice.java)

- Represents individual dice used in the game.
- Allows rolling of the dice to generate random values between 1 and 6.
- Tracks whether a dice has been used in a combination.

### 4. Player Class (Player.java)

- Manages the player's information, including their name and total score.
- Provides methods to update and reset the player's score.

### 5. BackgroundPanel Class (BackgroundPanel.java)

- Custom panel class for displaying background images in the GUI.
- Loads and displays the game board's background image.

### 6. ImageService Class (ImageService.java)

- Provides image-related utility functions for loading and updating images.
- Used for loading and updating dice images in the GUI.

### 7. LeaderboardEntry Class (LeaderboardEntry.java)

- Represents an entry in the leaderboard, including the player's name and score.
- Used to store and display leaderboard data.

### 8. CombinationsUse Class (CombinationsUse.java)

- Manages the state of combination selections during the game.
- Keeps track of which combinations have been used and the total number of used combinations.
- Provides methods to mark combinations as used and reset combination states.

### 9. DiceRolling Class (DiceRolling.java)

- Manages the remaining rolls available to the player.
- Provides methods to get the remaining rolls, roll the dice, and reset the roll count.

## How to Play

1. Start the game by running the "GeneralDiceGame" class.
2. Enter your name to begin.
3. Use the "Roll" button to roll the dice.
4. Select combinations on the game board based on your dice rolls.
5. Accumulate points by strategically choosing combinations.
6. The game ends when all combinations are selected or the player chooses to exit.
7. View the leaderboard to see high scores.

## Additional Information

- The game includes various combinations such as pairs, triples, full house, and more.
- Player scores are saved in a "leaderboard.txt" file.
- The leaderboard displays the top scores achieved by players.
- The repository is given in the zip file, in the HIDDEN folder named .git.
- Link to the GitHub repository: https://github.com/MartinKaraivanov/GeneralDiceGame.git
- The Product Backlog is in the Product_Backlog_General_Dice_Game.md file.