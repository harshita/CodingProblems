package com.harshitakarande;

/**
 * @author Harshita Karande
 * @version 0.1
 */

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class TicTacToeTest {
    /* Instance for the TicTacToe game we would be testing */
    private TicTacToe tictactoe;

    /* Board states that we would be comparing our end results in */
    private BoardState gameTree, newGameTree;

    /**
     * This takes a 2d char array and sets up the entire board
     * position.
     *
     * Char arrays are easier to use/modify and this stub makes
     * it easier to translate those values to an actual board
     * state that TicTacToe understands.
     */
    private void setUpBoard(char[][] boardState) {
        tictactoe = new TicTacToe();
        gameTree = new BoardState();

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (Character.isWhitespace(boardState[i][j])) {
                    continue;
                }
                gameTree.setCharInBoardPosition(i, j, boardState[i][j]);
            }
        }
    }

    @Test
    public void testisGameDraw() {
        /* Assert true that game state is drawn */
        char[][] boardState = new char[][]{
            { 'X', '0', 'X' },
            { 'X', 'X', '0' },
            { '0', 'X', '0' }
        };
        setUpBoard(boardState);

        gameTree.setNextPlayer(2);
        gameTree.setLookForChar('0');
        gameTree.checkGameEnd();
        assertTrue(gameTree.isDraw());

        gameTree.setNextPlayer(1);
        gameTree.setLookForChar('X');
        gameTree.checkGameEnd();
        assertTrue(gameTree.isDraw());

        /* A spot is still left, game cannot be drawn */
        boardState = new char[][]{
            { 'X', '0', '0' },
            { 'X', 'X', '0' },
            { '0', 'X', ' ' }
        };
        setUpBoard(boardState);

        gameTree.setNextPlayer(2);
        gameTree.setLookForChar('0');
        gameTree.checkGameEnd();
        assertFalse(gameTree.isDraw());

        gameTree.setNextPlayer(1);
        gameTree.setLookForChar('X');
        gameTree.checkGameEnd();
        assertFalse(gameTree.isDraw());
    }

    @Test
    public void testHasGameEnded() {
        /* X wins, game ended should return true
         * for either of the players.
         */
        char[][] boardState = new char[][]{
            { 'X', '0', ' ' },
            { ' ', 'X', ' ' },
            { '0', '0', 'X' }
        };
        setUpBoard(boardState);

        gameTree.setNextPlayer(2);
        gameTree.setLookForChar('0');
        gameTree.checkGameEnd();
        assertTrue(gameTree.hasGameEnded());

        gameTree.setNextPlayer(1);
        gameTree.setLookForChar('X');
        gameTree.checkGameEnd();
        assertTrue(gameTree.hasGameEnded());

        /* Game has not ended yet, should return false
         * for both players */
        boardState = new char[][]{
            { 'X', '0', ' ' },
            { ' ', ' ', ' ' },
            { '0', '0', 'X' }
        };
        setUpBoard(boardState);

        gameTree.setNextPlayer(2);
        gameTree.setLookForChar('0');
        gameTree.checkGameEnd();
        assertFalse(gameTree.hasGameEnded());

        gameTree.setNextPlayer(1);
        gameTree.setLookForChar('X');
        gameTree.checkGameEnd();
        assertFalse(gameTree.hasGameEnded());
    }

    @Test
    public void testNextMoveWin() {
        /* '0' to play and win */
        char[][] boardState = new char[][]{
            { 'X', '0', 'X' },
            { 'X', ' ', ' ' },
            { '0', '0', 'X' }
        };
        setUpBoard(boardState);

        gameTree.setNextPlayer(2);
        tictactoe.constructGameTree(gameTree);
        newGameTree = tictactoe.playNextMove(gameTree, tictactoe.gameTree);

        assertTrue(newGameTree.hasGameEnded());
        assertEquals(newGameTree.winner(), 2);

        /* X clearly wins this. A test to verify it. */
        boardState = new char[][]{
            { 'X', ' ', '0' },
            { ' ', '0', ' ' },
            { 'X', ' ', 'X' }
        };
        setUpBoard(boardState);

        gameTree.setNextPlayer(1);
        tictactoe.constructGameTree(gameTree);
        newGameTree = tictactoe.playNextMove(gameTree, tictactoe.gameTree);

        assertTrue(newGameTree.hasGameEnded());
        assertEquals(newGameTree.winner(), 1);
    }
}
