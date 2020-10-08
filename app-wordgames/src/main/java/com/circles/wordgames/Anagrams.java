package com.circles.wordgames;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.circles.wordgames.Game.IGame;

public class Anagrams extends Game implements IGame {

    private LightWordTree wordset;
    private ArrayList<Character> rootword;
    private ArrayList<String> correctlyGuessed = new ArrayList<String>();
    private int score = 0;

    public Anagrams() {

    }

    // chooses a random wordset from a WORDSETDIR directory
    // we now need to choose a random file from word lists
    private LightWordTree chooseWordSet() throws Exception {


        ArrayList<String> files = new ArrayList<String>();
        

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(
                final InputStream is = loader.getResourceAsStream("./" + App.WORDSETDIR);
                final InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                final BufferedReader br = new BufferedReader(isr)) {
            br.lines().forEach(files::add);
        }

        System.out.println(files.size());

        Random rand = new Random();

        String filename = files.get(rand.nextInt(files.size()));

        InputStream is = loader.getResourceAsStream("./" + App.WORDSETDIR + File.separator + filename);

        BufferedReader lol = new BufferedReader(new InputStreamReader(is));
        List<String> contents = lol.lines().collect(Collectors.toList());

        String root = filename.substring(0,filename.length()-4);


        rootword = new ArrayList<Character>();

        for (char ch : root.toCharArray()) {
            rootword.add(ch);
        }

        System.out.println(contents);

        lol.close();

        return new LightWordTree(contents);


    }

    

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
        System.out.println("Letters: " + rootword + " (guess \"s\" to shuffle!)" + "\n");
    }

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

    public void end() {
        System.out.println("Moving into end state.");

        // if timeout, print "run out of time!" and summarize state

        System.out.println("You've won! :D");
        printState();
    }


    public void printState() {
        System.out.println(summarizeState());
    }

    public String summarizeState() {
        String output = "\n";
        output += "Current score: " + score + ".\n";
        output += "You've correctly guessed " + correctlyGuessed.size() + " words so far.\n";
        output += "Letters: " + rootword + " (guess \"s\" to shuffle!)" + "\n";
        return output;
    }




}
