package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.actionMsg.PigRollAction;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        sleep(1000);
        PigGameState pigGameState = new PigGameState((PigGameState) info);
        if (pigGameState.getPlayerID() != this.playerNum) {
            return;

        } else {
            int myScore;
            int oppScore;

            // sets the AI and player's score based on their ID
            if (this.playerNum == 0) {
                myScore = pigGameState.getPlayer1Score();
                oppScore = pigGameState.getPlayer2Score();
            } else {
                myScore = pigGameState.getPlayer2Score();
                oppScore = pigGameState.getPlayer1Score();
            }

            // will hold if the current score and running total is greater than or
            // equal to 50
            if (myScore + pigGameState.getRunningTotal() >= 50) {
                game.sendAction(new PigHoldAction(this));
            }
            // Determines if the player is in the lead or not
            // if the player is in the lead: will roll until running total is <= 15
            // if not: will roll until the running total is <= 10
            else {
                if (oppScore > myScore) {
                    if (pigGameState.getRunningTotal() <= 10) {
                        game.sendAction(new PigRollAction(this));
                    } else {
                        game.sendAction(new PigHoldAction(this));
                    }
                }
                else {
                    if (pigGameState.getRunningTotal() <= 5) {
                        game.sendAction(new PigRollAction(this));
                    } else {
                        game.sendAction(new PigHoldAction(this));
                    }
                }
            }
        }//receiveInfo

    }
}
