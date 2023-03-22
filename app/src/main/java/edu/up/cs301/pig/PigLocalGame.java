package edu.up.cs301.pig;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

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
    protected boolean makeMove(GameAction action) {
        if (action instanceof PigHoldAction == true){

        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // make copy of curr game state
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
