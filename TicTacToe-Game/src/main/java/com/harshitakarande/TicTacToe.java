package com.harshitakarande;

/**
 * @author Harshita Karande
 * @version 0.1
 */
import java.util.Scanner;

public class TicTacToe {
    /* Game tree used to calculate next moves. */
    public BoardState gameTree;

    /**
     * This function gets the board configuration with the nextPlayer set
     * on the board config, generates the game tree that computes the
     * min and max nodes, sets the score, and other member variables at
     * each node in the tree.
     *
     * This tree will be used by the program to decide which move is to be
     * taken next for each human move made such that it will always result
     * in either a win or draw for the program.
     *
     * In order to print the entire tree for debugging add:
     *    tree.printTree()
     *
     * @param initialConfig Initial board state from which to generate the tree.
     *
     */
    public void constructGameTree(BoardState initialConfig) {
        generateGameTree(initialConfig);
        initialConfig.computeMinMaxScore();

        this.gameTree = initialConfig;
    }


    /**
     * Function to return the next best board state given the current board
     * position.
     *
     * @param newGame                Current board position.
     * @param currentGameTreePointer Pointer to the gameTree node that matches
     *                               current position.
     *
     * @return BoardState            Board state with the next best move played.
     *
     */
    public BoardState playNextMove(BoardState newGame, BoardState currentGameTreePointer) {
        BoardState currentPointerInGameTree = currentGameTreePointer;

        newGame.checkGameEndForChar();

        if (newGame.hasGameEnded() && !newGame.isDraw()) {
            System.out.println(String.format("Game over. Player %d wins", newGame.winner()));
            return newGame;
        }

        if (newGame.hasGameEnded() && newGame.isDraw()) {
            System.out.println("Game draw");
            return newGame;
        }

        int tempLevel = Integer.MAX_VALUE;
        BoardState tempFutureState = null;

        // Search game tree here.
        for (BoardState futureGameTreeState : currentPointerInGameTree.nextMoves) {
            if (futureGameTreeState.getScore() == currentPointerInGameTree.getScore()
                && futureGameTreeState.getLevelFromBottom() < tempLevel) {

                tempLevel = futureGameTreeState.getLevelFromBottom();
                tempFutureState = futureGameTreeState;
            }
        }

        return tempFutureState;
    }

    /**
     * This function starts with an empty board game prompting the user
     * to enter his/her move first, searches the game tree for the next best
     * move that will guarantee either a win/draw and continues this sequence
     * until the computer wins or the game is drawn.
     */
    public void play() {
        BoardState newGame = new BoardState();
        System.out.println("Game has begun.");

        newGame.setNextPlayer(1);
        newGame.setLookForChar('X');
        newGame.setInsertChar('X');

        Scanner scan = new Scanner(System.in);
        BoardState currentPointerInGameTree = this.gameTree;

        while (!newGame.hasGameEnded()) {
            try {
                System.out.println();
                System.out.println(
                    String.format("Player %d: Enter your move %c in <row>,<column>",
                      newGame.getNextPlayer(),
                      newGame.getInsertChar()));

                // Read Only if second player is human player.
                if (newGame.getInsertChar() != '0')  {
                    String input = scan.nextLine();
                    String[] splitStr = input.split(",");
                    if (splitStr.length != 2) {
                        throw new IllegalArgumentException("Input format is wrong");
                    }

                    int row = 0;
                    int column = 0;

                    row = Integer.parseInt(splitStr[0]);
                    column = Integer.parseInt(splitStr[1]);

                    if(row < BoardState.BOARD_SIZE
                        && column < BoardState.BOARD_SIZE
                        && row > -1 && column > -1) {
                        if(newGame.getCharInBoardPosition(row, column) != '\0') {
                            throw new IllegalArgumentException("Wrong location. This position is already filled");
                        }
                        newGame.setCharInBoardPosition(row, column, newGame.getInsertChar());
                    }
                    else {
                        throw new IllegalArgumentException("Row & Column index cannot exceed " +
                            (BoardState.BOARD_SIZE - 1) + " or less than 0");
                    }

                }
                else {
                    // Search game tree here.
                    currentPointerInGameTree = searchGameTree(currentPointerInGameTree, newGame);
                    // Copy the next move found in game tree onto newGame.
                    newGame.copyCompleteBoardPositionFrom(currentPointerInGameTree);
                }

                newGame.printTicTacToe();

                newGame.checkGameEndForChar();

                newGame.setNextPlayer(newGame.getNextPlayer() == 1? 2 : 1);
                newGame.setLookForChar(newGame.getInsertChar() == 'X'? '0' : 'X');
                newGame.setInsertChar(newGame.getInsertChar() == 'X'? '0' : 'X');


                if (newGame.hasGameEnded() && !newGame.isDraw()) {
                    System.out.println(String.format("Game over. Player %d wins", newGame.winner()));
                }

                if (newGame.hasGameEnded() && newGame.isDraw()) {
                    System.out.println("Game draw");
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }

        }

    }

    /**
     * This function starts searching the game tree at the position of the
     * last selected move and checks with all next moves to see which one
     * of the moves match the opponent's move.
     *
     * Once it gets a match, it selects the the next move from the opponent's
     * move for which the score matches the score of the current position in
     * the game tree and for which levelFromBottom is minimum i.e. win/draw
     * in minimum moves. When it finds such a node, it returns it as its next
     * move.
     *
     * @param currentPointerInGameTree Position in the game tree of the last
     *                                 selected move by the program/computer.
     * @param currentState             Current board configuration.
     *
     * @return                         The board state that will be the
     *                                 computer/program's next move to the
     *                                 human move.
     */
    public BoardState searchGameTree(BoardState currentPointerInGameTree, BoardState currentState) {
        BoardState tempFutureState = null;
        int tempLevel = Integer.MAX_VALUE;

        for (BoardState gameTreeState : currentPointerInGameTree.nextMoves) {
            if (currentState.equals(gameTreeState)) {
                currentPointerInGameTree = gameTreeState;
                return playNextMove(currentState, currentPointerInGameTree);
            }
        }
        return null;
    }




    /**
     * This function generates the game tree from a board state and
     * computes the min max score at each node if it is not a leaf node.
     *
     * @param boardState Starting config from which to generate game tree.
     */
    public void generateGameTree(BoardState boardState) {
        boardState.generateNextPossibleMovesForPlayer();
        if (boardState.hasGameEnded()) {
            return;
        }
        for (BoardState bs : boardState.nextMoves) {
            generateGameTree(bs);
            if (!bs.hasGameEnded())
                // Compute min max score from the child moves.
                bs.computeMinMaxScore();
        }
    }

    /*
     *
     *  Let the games begin! :)
     *
     */
    public static void main(String args[]) {
        TicTacToe tictactoe = new TicTacToe();
        BoardState gameTree, newGameTree;

        gameTree = new BoardState();

        gameTree.setNextPlayer(1);
        tictactoe.constructGameTree(gameTree);
        tictactoe.play();
  }


}
