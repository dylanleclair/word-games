package com.circles.wordgames;

import java.util.Scanner;

import com.circles.wordgames.Game.IGame;

/**
 * Hello world!
 *
 */
public class App 
{

    static final String ANAGRAMSWORDLIST = "collinsscrabble.txt";
    static final String WORDSNDIR = "wordsbylength";
    static final String WORDSETDIR = "wordsets";


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


    public static void runGame(IGame game, Scanner s) {
        game.setup();
        game.play(s);
        game.end();
    }


}
