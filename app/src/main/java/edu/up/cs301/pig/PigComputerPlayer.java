package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.actionMsg.PigRollAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
       PigGameState pigGameState = new PigGameState((PigGameState) info);
       if (pigGameState.getPlayerID() == 0){
            return;
       }
       else{
           Random r = new Random();
           int choice = r.nextInt(2);

           if (choice == 0){
              PigHoldAction pigHoldAction = new PigHoldAction(this);
              game.sendAction(pigHoldAction);
           }
           else{
               PigRollAction pigRollAction = new PigRollAction(this);
               game.sendAction(pigRollAction);
           }
       }
    }//receiveInfo

}
