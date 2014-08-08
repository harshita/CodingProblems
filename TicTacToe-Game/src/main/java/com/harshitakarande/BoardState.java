package com.harshitakarande;

/**
 * @author Harshita Karande
 * @version 0.1
 */
import java.util.ArrayList;
import java.util.List;

public class BoardState {
    /* Constant size of the current board */
    static final int BOARD_SIZE = 3;

    /* Next possible moves for each board state */
    List<BoardState> nextMoves = new ArrayList<BoardState>();

    /* Board configuration represented in 'X' and '0's */
    private char[][] boardState;

    /* Variable used to assign scores and verify endgame state */
    private char lookForChar;

    /* Character that the next player will use for her move */
    private char insertChar;

    /* Player for the next round, takes value 1 or 2 */
    private int nextPlayer;

    /* End game indicator */
    private boolean gameEnds;

    /* True if game has ended in a truce */
    private boolean isDraw;

    /* Min/max score */
    private int score;

    /* No. of levels to reach win or draw from current level */
    private int levelFromBottom;

    public BoardState() {
        this.boardState = new char[BOARD_SIZE][BOARD_SIZE];
    }

    public char getInsertChar() {
        return insertChar;
    }

    public void setInsertChar(char insertChar) {
        this.insertChar = insertChar;
    }

    public char getLookForChar() {
        return lookForChar;
    }

    public void setLookForChar(char lookForChar) {
        this.lookForChar = lookForChar;
    }

    /**
     * Sets the char in a row/column of the board state.
     *
     * @param row    Row index.
     * @param column Column index.
     * @param c      Character to insert in that [row][column].
     */
    public void setCharInBoardPosition(int row, int column, char c) {
        this.boardState[row][column] = c;
    }

    /**
     * Gets the char from a given row/column of the board state.
     *
     * @param row    Row index.
     * @param column Column index.
     *
     * @return       Character in that [row][column].
     */
    public char getCharInBoardPosition(int row, int column) {
        return this.boardState[row][column];
    }

    /**
     * Deep copy of the complete board position from the given
     * state.
     *
     * @param copyFrom BoardState to copy from
     */
    public void copyCompleteBoardPositionFrom(BoardState copyFrom) {
        for(int k = 0; k < BOARD_SIZE; k++) {
            for(int m = 0; m < BOARD_SIZE; m++) {
                 this.boardState[k][m] = copyFrom.boardState[k][m];
            }
        }
    }

    /**
     * Checks if the game is over or is yet to be played.
     */
    public void checkGameEnd() {
        for (char c: new char[]{'X', '0'}) {
            this.lookForChar = c;
            checkGameEndForChar();
        }
    }


    /**
     * Checks the row, column, two diagonals for either X or 0
     * depending on the value of lookForChar member variable
     * to see if there a win.
     *
     * If there is no win, it checks the complete board for empty
     * slots. If no empty slots are found, game results in a draw.
     * In both win and draw case, this function sets the gameEnds,
     * isDraw, score and levelFromBottom member variables.
     *
     */
    public void checkGameEndForChar() {
        int count;

        // Check for rows.
        for (int i = 0; i < BOARD_SIZE; i++) {
            count = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (boardState[i][j] == lookForChar)
                    count++;
            }

            if (count == BOARD_SIZE) {
                gameEnds = true;
                isDraw = false;
                levelFromBottom = 0;
                assignScore();
                return;
            }
        }

        // Check for columns.
        for (int i = 0; i < BOARD_SIZE; i++) {
            count = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (boardState[j][i] == lookForChar)
                    count++;

            }
            if (count == BOARD_SIZE) {
                gameEnds = true;
                isDraw = false;
                levelFromBottom = 0;
                assignScore();
                return;
            }
        }

        count = 0;
        // Check for top-left to bottom-right diagonal.
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (boardState[i][i] == lookForChar)
                count++;
            if (count == BOARD_SIZE) {
                gameEnds = true;
                isDraw = false;
                levelFromBottom = 0;
                assignScore();
                return;
            }
        }

        count = 0;
        // Check for bottom-left to top-right diagonal.
        for (int i = 0; i < BOARD_SIZE; i++) {
            if(boardState[BOARD_SIZE-1-i][i] == lookForChar)
                count++;
            if (count == BOARD_SIZE) {
                gameEnds = true;
                isDraw = false;
                levelFromBottom = 0;
                assignScore();
                return;
            }


        }

        // If no win, check for draw.
        count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (this.boardState[i][j] == '\0')
                    count++;

            }
        }

        if (count == 0) {
            gameEnds = true;
            isDraw = true;
            levelFromBottom = 0;
            assignScore();
            return;
        }

    }


    /**
     * Generates the next set of moves for a particular board state.
     *
     * * This function first sets the lookforChar and insertChar member
     * variables depending on whether the player is 1 or 2. insertChar
     * is the character to be inserted in empty slots in the next possible
     * moves. lookForChar is the char last entered in the current board state.
     *
     * * It then checks if the game has reached an end via a win/draw.
     *
     * * If it has, we don't have to generate next set of possible moves.
     *
     * * But if the game has not ended yet, we generate the set of next
     * possible moves by adding the insertChar in each of the empty slots
     * one at a time thereby generating a new board state.
     *
     * * All these newly generated board states are saved in the nextMoves
     * member variable for the current board state
     *
     */
    public void generateNextPossibleMovesForPlayer() {

        if (nextPlayer == 1) {
            lookForChar = '0';
            insertChar = 'X';
        }
        else {
            lookForChar = 'X';
            insertChar = '0';
        }

        checkGameEndForChar();

        if (!hasGameEnded()) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (this.boardState[i][j] == '\0') {

                        BoardState nextBoardState = new BoardState();
                        nextBoardState.nextPlayer = this.nextPlayer == 1 ? 2 : 1;
                        nextBoardState.boardState = new char[BOARD_SIZE][BOARD_SIZE];

                        for (int k = 0; k < BOARD_SIZE; k++) {
                            for (int m = 0; m < BOARD_SIZE; m++) {
                                nextBoardState.boardState[k][m] = this.boardState[k][m];
                            }
                        }

                        nextBoardState.boardState[i][j] = insertChar;
                        this.nextMoves.add(nextBoardState);
                    }
                }
            }
        }
    }


    /**
     * This function simply sets a score of either -1, 1 or 0
     * for the leaf nodes of the game tree depending on which
     * player wins in the current board state.
     *
     * 1. If the lookForChar is X and it is a win, the score of the leaf node is 1.
     * 2. If the lookforChar is 0 and it is a win, the score of the leaf node is -1.
     * 3. If it is a draw for any lookForChar, the score of the leaf node is 0.
     *
     *
     */
    public void assignScore() {
        if (!isDraw && lookForChar == 'X')
            this.score = 1;
        else if (!isDraw && lookForChar == '0')
            this.score = -1;
        else
            this.score = 0;

    }

    /**
     * This function computes the min max score and levelFromBottom at a board state.
     */
    public void computeMinMaxScore() {

        if (this.insertChar == 'X') {
            int max = Integer.MIN_VALUE;
            for (BoardState bschild : this.nextMoves) {
                if (bschild.score > max)
                    max = bschild.score;
            }
            this.score = max;
        }
        else {
            int min = Integer.MAX_VALUE;
            for(BoardState bschild : this.nextMoves) {
                if (bschild.score < min)
                    min = bschild.score;
            }
            this.score = min;
        }

        // Now that we found min or max score, compute the
        // best level to win or draw from that position and
        // that score.
        int templevelFromBottom = Integer.MAX_VALUE;

        for (BoardState bschild : this.nextMoves) {
            if (bschild.score == this.score
                && bschild.levelFromBottom < templevelFromBottom)
                templevelFromBottom = bschild.levelFromBottom;
        }

        this.levelFromBottom = templevelFromBottom + 1;
    }

    /**
     * This function returns the winner (1/2) if any, else returns 0.
     */
    public int winner() {
        if (hasGameEnded())
            return this.nextPlayer == 2 ? 1 : 2;

        return 0;
    }

    /**
     * Returns true if draw, false otherwise.
     */
    public boolean isDraw() {
        return this.isDraw;
    }

    /**
     * Returns true if game ends, false otherwise.
     */
    public boolean hasGameEnded() {
        return this.gameEnds;
    }

    /**
     * Returns score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns level from bottom (leaf) node i.e. in how
     * many moves will there be a guaranteed win/draw.
     */
    public int getLevelFromBottom() {
        return this.levelFromBottom;
    }

    /**
     * Returns next player.
     */
    public int getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Sets next player.
     */
    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
    }


    /**
     * This function compares the game tree state with the current state
     * and returns true only if all the spots in the game tree config
     * matches the current state config.
     *
     * @param gameTreeState Board state of any position in the game tree.
     *
     * @return true/false
     */
    public boolean equals(BoardState gameTreeState) {
        for (int i = 0; i < BoardState.BOARD_SIZE; i++) {
            for (int j = 0; j< BoardState.BOARD_SIZE; j++) {
                if (gameTreeState.boardState[i][j] != this.boardState[i][j])
                    return false;
            }
        }
        return true;
    }

    /**
     * This function prints the game tree with all the values set
     * at each node in the game tree.
     */
    public void printTree() {
        printTicTacToe();

        System.out.println("Score: "+ getScore() + " look for: " + lookForChar + " insert: " + insertChar + " game finished: " +hasGameEnded() + " level from bottom to win or draw: "+getLevelFromBottom());
        System.out.println();
        for (BoardState bs : this.nextMoves) {
            bs.printTree();
        }
    }

    /**
     * Prints the board configuration.
     */
    public void printTicTacToe() {
        for (int i = 0; i < BoardState.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardState.BOARD_SIZE; j++) {
                if (this.boardState[i][j] == '\0')
                    System.out.print("_");
                else
                    System.out.print(this.boardState[i][j]);
            }
            System.out.println();
        }
    }
}
