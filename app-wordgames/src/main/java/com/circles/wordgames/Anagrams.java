package com.circles.wordgames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.circles.wordgames.Game.IGame;
import com.circles.wordgames.WordLists.Parameters;

/**
 * The Game class that implements IGame to run a simple game of Anagrams.
 * @author Dylan Leclair
 *
 */
public class Anagrams extends Game implements IGame {

    protected LightWordTree wordset;
    protected ArrayList<Character> rootword;
    private ArrayList<String> correctlyGuessed = new ArrayList<String>();
    private int score = 0;
    public ArrayList<String> contents;

    private final boolean debug = false;

    public Anagrams() {

    }


    /**
     * Generates a list of words to play anagrams with, updating the root word and building a LightWordTree to play the game with.
     * @return a LightWordTree containing all the anagrams created with default parameters.
     */
    private LightWordTree chooseWordSet() {


        contents = WordLists.generateAnagramsList(new Parameters(3,6,70));

        String root = contents.get(0);

        rootword = new ArrayList<Character>();

        for (char ch : root.toCharArray()) {
            rootword.add(ch);
        }

        if (debug) {
            System.out.println(contents);
        }



        return new LightWordTree(contents);


    }

    @Override
    public void setup() {
        System.out.println("Moving into setup state.");
    
        try {
            System.out.println("Loading word list.");
            this.wordset = chooseWordSet();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.shuffle(rootword);
        System.out.println("Starting game! Find as many anagrams as you can!");
        System.out.println("There are " + contents.size() + " anagrams to find!");
        System.out.println("Letters: " + rootword + " (guess \"s\" to shuffle!)" + "\n");
    }

    
    @Override
    public void play(Scanner s) {
        while (correctlyGuessed.size() < 10) {
            int addToScore = 0; // score for this turn
            String guess = getInput(s); // guess / input for this turn

            if (guess.contentEquals("s")) {
                Collections.shuffle(rootword); // if input is s, shuffle the letters.

                System.out.println("Words have been shuffled!");

            } else { // otherwise, run the turn normally
                if (!correctlyGuessed.contains(guess)) { // if this turns guess has not already been inputted
                    try {
                        if (wordset.findWord(guess)) { // look for the guess in the wordset
                            //if it's found
                            addToScore = (guess.length() * guess.length() ) * 100; 
                            correctlyGuessed.add(guess);
                            System.out.println("Correct! +" + addToScore + "!");
                        } else { 
                        	// if it isn't
                            System.out.println("Not an anagram! :(");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("You've already guessed that!");
                }
                score += addToScore; // update score
            }


            printState(); // print the state summary
        }

    }


    @Override
    public void end() {
        System.out.println("Moving into end state.");

        // if timeout, print "run out of time!" and summarize state

        System.out.println("You've won! :D");
        printState();
    }

    @Override
    public void printState() {
        System.out.println(summarizeState());
    }

    private String summarizeState() {
        String output = "\n";
        output += "Current score: " + score + ".\n";
        output += "You've correctly guessed " + correctlyGuessed.size() + " words so far.\n";
        output += "Letters: " + rootword + " (guess \"s\" to shuffle!)" + "\n";
        return output;
    }




}
