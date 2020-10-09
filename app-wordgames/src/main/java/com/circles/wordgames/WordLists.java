package com.circles.wordgames;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordLists {


    public static class Parameters {
        final int minlength;
        final int maxlength;
        final int target;

        Parameters(int minlength, int maxlength, int target) {
            this.minlength = minlength;
            this.maxlength = maxlength;
            this.target = target;
        }

    }


    /**
     * Creates an Anagrams word list such that the root word is the first item in the list,
     * followed by it's anagrams.
     * @param parameters the specifications for the list to meet
     * @return the list of anagrams
     */
    public static ArrayList<String> generateAnagramsList (Parameters parameters) {


        ArrayList<String> output;
        ArrayList<String> words = new ArrayList<String>();

        InputStream is = WordLists.class.getClassLoader().getResourceAsStream(App.ANAGRAMSWORDLIST);

        Scanner s = new Scanner(is);

        
        LightWordTree light = new LightWordTree(Arrays.asList());

        while (s.hasNext()) {
            String word = s.next();
            word = word.toLowerCase().trim();
            light.addWord(word);
            if (word.length() == parameters.maxlength) {
                words.add(word);
            }
        }

        s.close();

        // select a random rootword from words and generate permutations!

        Random r = new Random();
    

        do {
            int index = r.nextInt(words.size());
            output = findPermutations(light, words.get(index), parameters);
        } 
        while (output.size() < parameters.target);
        
        

        return output;

    }
    
    private static ArrayList<String> findPermutations(WordTree wordtree, String word, Parameters parameters) {
        

        ArrayList<String> output = new ArrayList<String>();

        output.add(word);

        ArrayList<Character> chars = new ArrayList<Character>();

        for (Character c : word.toCharArray()) {
            chars.add(c);
        }
        
        findPermutationsHelper(wordtree.root, chars, output);

        return filterPermutations(output, parameters);
    }


    private static void findPermutationsHelper (INode node, ArrayList<Character> characters, ArrayList<String> permutations) { 
        
        if (characters.size() > 0 ) {
            for (Character c : characters) {
                
                INode check = node.getChild(c);

                if (node.getChild(c) != null) {
                    
                    ArrayList<Character> copyCharacters = new ArrayList<Character>(characters);

                    copyCharacters.remove((Character) c);
                    
                    if (check.getAccepting()) {
                        String word = WordTree.BubbleUp(check);
                        
                        if (word.length() >= 2 && !permutations.contains(word)) {
                            permutations.add(word);
                            
                        }

                    }

                   
                    
                    findPermutationsHelper(check, copyCharacters, permutations);
                }

            }


        } else  { // no more characters

            String word = WordTree.BubbleUp(node);

            if (!permutations.contains(word) && node.getAccepting()) {
                permutations.add(word);
            }
        

        }



    }


    private static ArrayList<String> filterPermutations(ArrayList<String> perms, Parameters p) {

        ArrayList<String> output = new ArrayList<String>();

        for (String word : perms) {
            int size = word.length();
            if (size <= p.maxlength && size >= Math.min(2, size)) {
                output.add(word);
            }
        }

        return output;
        

    }

}
