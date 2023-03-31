package edu.up.cs301.pig;
import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState
{
    // instance variables
    private int playerID; // the ID of the player whose turn it is
    private int player1Score; // player 0's score
    private int player2Score; // player 1's score
    private int runningTotal; // the running total for the current turn
    private int diceValue; // the value of the last rolled dice

    // constructor
    public PigGameState()
    {
        playerID = 0;
        player1Score = 0;
        player2Score = 0;
        runningTotal = 0;
        diceValue = 0;
    }

    // copy constructor
    public PigGameState(PigGameState original)
    {
        this.playerID = original.getPlayerID();
        this.player1Score = original.player1Score;
        this.player2Score = original.player2Score;
        this.runningTotal = original.runningTotal;
        this.diceValue = original.diceValue;
    }

    // getter methods
    public int getPlayerID()
    {
        return playerID;
    }

    public int getPlayer1Score()
    {
        return player1Score;
    }

    public int getPlayer2Score()
    {
        return player2Score;
    }

    public int getRunningTotal()
    {
        return runningTotal;
    }

    public int getDiceValue()
    {
        return diceValue;
    }

    // setter methods
    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    public void setPlayer1Score(int player0Score)
    {
        this.player1Score = player0Score;
    }

    public void setPlayer2Score(int player1Score)
    {
        this.player2Score = player1Score;
    }

    public void setRunningTotal(int runningTotal)
    {
        this.runningTotal = runningTotal;
    }

    public void setDiceValue(int diceValue)
    {
        this.diceValue = diceValue;
    }

    // roll the dice and update the state accordingly
    public int rollDice()
    {
        Random rand = new Random();
        diceValue = rand.nextInt(6) + 1;
        if (diceValue == 1)
        {
            runningTotal = 0;
            return -1;
        } else {
            runningTotal += diceValue;
            return 0;
        }
    }

    // add the running total to the current player's score
    public void endTurn()
    {
        if (playerID == 0)
        {
            player1Score += runningTotal;
        } else
        {
            player2Score += runningTotal;
        }
        runningTotal = 0;
    }

    public void switchTurns(){
        runningTotal = 0;
        playerID = 1 - playerID; // switch to the other player's turn
    }
}