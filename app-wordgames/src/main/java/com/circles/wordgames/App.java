package com.circles.wordgames;

import java.util.Scanner;

import com.circles.wordgames.Game.IGame;

/**
 * The class used to run and play different Games. 
 */
public class App 
{

    /** The pathname for the collins scrabble word list (2019) */
    static final String ANAGRAMSWORDLIST = "collinsscrabble.txt";

    /** A scanner to get command line input */
    static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args )
    {

        // testing out Anagrams with new implementation

        try {

            Anagrams a = new Anagrams();
            runGame(a, scanner);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // while we're at it, lets play both:

        try { 
            // create a hangman game where you have 8 incorrect guesses
            Hangman h = new Hangman(8); 
            runGame(h, scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();

    }


    /**
     * Code for running any IGame object. 
     * Performs setup, running, and cleanup. 
     * @param game the IGame being run
     * @param s the Scanner that handles input.
     */
    public static void runGame(IGame game, Scanner s) {
        game.setup();
        game.play(s);
        game.end();
    }


}
