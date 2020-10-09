package com.circles.wordgames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.circles.wordgames.Game.IGame;
import com.circles.wordgames.WordLists.Parameters;

public class Anagrams extends Game implements IGame {

    protected LightWordTree wordset;
    protected ArrayList<Character> rootword;
    private ArrayList<String> correctlyGuessed = new ArrayList<String>();
    private int score = 0;
    public ArrayList<String> contents;

    private final boolean debug = true;

    public Anagrams() {

    }


    // chooses a random wordset from a WORDSETDIR directory
    // we now need to choose a random file from word lists
    private LightWordTree chooseWordSet() throws Exception {


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
            int addToScore = 0;
            String guess = getInput(s);

            if (guess.contentEquals("s")) {
                Collections.shuffle(rootword);

                System.out.println("Words have been shuffled!");

            } else {
                if (!correctlyGuessed.contains(guess)) {
                    try {
                        if (wordset.findWord(guess)) {
                            
                            addToScore = (guess.length() * guess.length() ) * 100;
                            correctlyGuessed.add(guess);
                            System.out.println("Correct! +" + addToScore + "!");
                        } else { 
                            System.out.println("Not an anagram! :(");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("You've already guessed that!");
                }
                score += addToScore;
            }


            printState();
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
