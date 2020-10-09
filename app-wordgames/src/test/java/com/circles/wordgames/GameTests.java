package com.circles.wordgames;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class GameTests {

    @Test
    public void TestAnagrams () {

        // setup the game

        Anagrams a = new Anagrams();
        a.setup();

        // create variable to test against
        boolean out = false;

        try {


            String testAgainst = "";

            List<String> perms = a.contents;
            
            // write out ten permutations, since the Anagrams game should end after this.
            for (int i = 0; i < 10; i++) {
                testAgainst += (perms.get(i));
                testAgainst += System.lineSeparator();
            }

            byte[] wordsToGuess = testAgainst.getBytes();
            InputStream is = new ByteArrayInputStream(wordsToGuess);

            Scanner scanner = new Scanner(is);

            PrintStream orig = System.out;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            System.setOut(new PrintStream(os, false, "UTF-8"));

            a.play(scanner);


            scanner.close();

            a.end();

            out = (os.toString().contains("You've won! :D"));

            System.setOut(orig);
            System.out.println(out);


        } catch (Exception e) {
            e.printStackTrace();
        }
 
        assertTrue(out);

    }

    @Test
    public void TestHangmanWin () {

        Hangman h = new Hangman(10);
        h.setup();

        // create variable to test against
        boolean out = false;

        try {


            String testAgainst = "";

            String w = h.word;
            
            // guess every letter in the word. We should win at this point.
            for (Character c : w.toCharArray()) {
                testAgainst += c;
                testAgainst += System.lineSeparator();
            }

            byte[] lettersToGuess = testAgainst.getBytes();
            InputStream is = new ByteArrayInputStream(lettersToGuess);

            Scanner scanner = new Scanner(is);

            PrintStream orig = System.out;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            System.setOut(new PrintStream(os, false, "UTF-8"));

            h.play(scanner);


            scanner.close();

            h.end();

            out = (os.toString().contains("You won! Congrats :D"));

            System.setOut(orig);
            System.out.println(out);


        } catch (Exception e) {
            e.printStackTrace();
        }
 
        assertTrue(out);

    }

    @Test
    public void TestHangmanLoss () {

        Hangman h = new Hangman(10);
        h.setup();

        // create variable to test against
        boolean out = false;

        try {


            String testAgainst = "";

            String alphabet= "abcdefghijklmnopqrstuvwxyz";

            String w = h.word;
            
            // guess every letter in the word. We should win at this point.
            for (Character c : alphabet.toCharArray()) {

                if (!w.contains(c.toString())) {
                    testAgainst += c;
                    testAgainst += System.lineSeparator();
                }

            }

            byte[] lettersToGuess = testAgainst.getBytes();
            InputStream is = new ByteArrayInputStream(lettersToGuess);

            Scanner scanner = new Scanner(is);

            PrintStream orig = System.out;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            System.setOut(new PrintStream(os, false, "UTF-8"));

            h.play(scanner);


            scanner.close();

            h.end();

            out = (os.toString().contains("You lost!"));

            System.setOut(orig);
            System.out.println(out);


        } catch (Exception e) {
            e.printStackTrace();
        }
 
        assertTrue(out);

    }


}
