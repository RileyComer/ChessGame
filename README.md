# ChessGame

This project not only contains an entire working chess gamne with a GUI, but also a chess ai you can choose to play against.

## Game

- The game currently allows for a local game of chess be played but has some uncompleted files that would allow for online games.
- Run Chess.java to start the game.
- Currently the players may click on any of their peices on the board on their turn and the availble moves will be highlighted.
- Once players click a highlighted tile they will move their piece there and their turn will end.

There are 2 known issues.
- Players may move into check.
- Castling isn't currently possible.

## There are a couple of values that can be changed inside the Chess.java

### AI
There is a boolean value called AI.
- Set this value to false if you want to play a local game with a friend.
- Set it to true if you want to play against an AI.

### Player
There is a char called player. If AI is set to true then this determins what team the player controls.
- 'W' The player plays as white.
- 'B' The player plays as black.

### AI player
There is a char called AIPlayer. If AI is set to true then this determins what team the AI controls.
- 'W' The AI plays as white.
- 'B' The AI plays as black.

### AIINT
AIINT is a boolean value that gives insight into the ai.
IF AIINT is set to true then a square will appear in the top corner of the screen.
Depending on who the ai thinks is winning the color of the square will change.
-If white is winning then the square will appear green.
-If black is winning then the square will appear red.
-If its even the square will be black.
-The deeper the color the more confident the ai is.

### AIOnly
AIOnly is a boolean value that can be changed to true if you want both sides to be play by anh AI.

### randomGame
Random game randomizes the board peices.

### semiRandomGame
Randomizes all pieces except for pawns.

### AIWGame and AIBGame
These values are set to any of the created ai. 
- AIWGame plays as white.
- AIBGame plays as black.

## AI
AI require the following arguements when intialized (Pawn,Rook,Knight,Bishop,Queen,BishopPair,RookPair,KnightPair,defensive,level).
The arguement Pawn to Queen require a int from 10 to 100. This value will determin how much the ai values pieces of that type.
The Pair arguements are how much the ai values peices of that type only if its pair is still in play. For example a player might value a knight less if they still have another knight in play. int(10-100)
The defensive arguement is a boolean that will tell an ai to play more defensively if set to true. (Less willing to trade pieces)
The level arguement sets the level of the ai. The higher the level the smarter the ai. Its an int from 1-5. The higher the level the longer the ai will take to make a move. (5 takes about a minute per move)

### How does the ai work?
The ai uses a minmax algorithm with alpha beta prunning. The higher the level the greater depth of moves the ai will view. When the ai reaches a desired depth it will evaulate the board position.
The current eval function just adds or subtracts points based on the number of peices on the board and how the ai values those peices.
