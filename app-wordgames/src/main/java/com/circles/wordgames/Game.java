package com.circles.wordgames;
import java.util.Scanner;

public abstract class Game {
    
    
    interface IGame {
    	/**
    	 * Performs setup operations for a Game.
    	 */
        public void setup();
        
        /**
         * Implements a game's game loop, using a Scanner to get command line input.
         * @param s - the Scanner which will provide input to the Game.
         */
        public void play(Scanner s);
        
        /**
         * Performs cleanup operations for a game (if any)
         */
        public void end();
        
        /**
         * Prints a summary of the state of the game. 
         */
        public void printState();
    }
    
    /**
     * Gets a line of input from the game.
     * @param s - the Scanner from which input is retrieved
     * @return the next line of input from the Scanner (s)
     */
    public String getInput(Scanner s) {

        String output;

        output = s.next();

        output = output.toLowerCase().trim();

        return output;

    }
    


}
