package edu.up.cs301.pig;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    /**
     * This ctor creates a new game state
     */

    PigGameState pigGameState;

    public PigLocalGame () {

        pigGameState = new PigGameState();

    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override

    protected boolean canMove(int playerIdx) {
        if (playerIdx == pigGameState.getPlayerID()){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action)
    {
        if (action instanceof PigHoldAction == true)
        {
            // check to see if the action is a PigRollAction
            if (action instanceof PigRollAction)
            {
                // roll the dice
                int dieValue = (new Random()).nextInt(6) + 1;

                // if the die value is 1, set the current running total to 0 and make it the other player's turn (if there is another player)
                if (dieValue == 1)
                {
                    pigGameState.setRunningTotal(0);

                    if (players.length > 1)
                    {
                        pigGameState.getPlayerID((pigGameState.getPlayerID() + 1) % 2);
                    }
                }
                // if the die value is anything other than 1, add it to the current running total
                else {
                    pigGameState.setCurrentRunningTotal(pigGameState.getCurrentRunningTotal() + dieValue);
                }

                // return true to indicate that the move was legal
                return true;
            }
            // check to see if the action is a PigHoldAction
            else if (action instanceof PigHoldAction)
            {
                // add the current running total to the score of the current player
                int currentPlayer = pigGameState.getCurrentPlayer();
                int currentScore = pigGameState.getScore(currentPlayer);
                pigGameState.setScore(currentPlayer, currentScore + pigGameState.getCurrentRunningTotal());

                // set the current running total to 0
                pigGameState.setCurrentRunningTotal(0);

                // if there is more than one player, make it the other player's turn
                if (players.length > 1)
                {
                    pigGameState.setCurrentPlayer((currentPlayer + 1) % 2);
                }

                // return true to indicate that the move was legal
                return true;
            }
            // if the action is neither a PigRollAction nor a PigHoldAction, return false to indicate that

        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        pigGameState = new PigGameState(pigGameState); // creates copy
        p.sendInfo(pigGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if (pigGameState.getPlayer1Score() >= 50){
            return "Player 1 wins!";
        }
        else if (pigGameState.getPlayer2Score() >= 50){
            return "Player 2 wins";
        }
        else{
            return null;
        }
    }

}// class PigLocalGame
