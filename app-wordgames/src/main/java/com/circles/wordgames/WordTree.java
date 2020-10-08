package com.circles.wordgames;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class WordTree {


    protected final String WORDSNDIR = "wordsbylength";
    protected final String WORDSETDIR = "wordsets";

    protected Node root;

    public ArrayList<String> findPermutations(String word) throws Exception {
        

        ArrayList<String> output = new ArrayList<String>();

        ArrayList<Character> chars = new ArrayList<Character>();

        for (Character c : word.toCharArray()) {
            chars.add(c);
        }
        
        

        findPermutationsHelper(root, chars, output);

        return output;
    }

    private void findPermutationsHelper (Node node, ArrayList<Character> characters, ArrayList<String> permutations) throws Exception { 
        
        if (characters.size() > 0 ) {
            for (char c : characters) {
                boolean hasChild = node.hasChild(c);

                if (hasChild) {
                    Node check = node.letters.get(node.indexOf(c));

                    
                    ArrayList<Character> copyCharacters = new ArrayList<Character>(characters);

                    copyCharacters.remove((Character) c);
                    
                    if (check.accepting) {
                        String word = BubbleUp(check);
                        
                        if (word.length() >= 2 && !permutations.contains(word)) {
                            permutations.add(word);
                            
                        }

                    }

                   
                    
                    findPermutationsHelper(check, copyCharacters, permutations);
                } 

            }


        } else  { // no more characters

            String word = BubbleUp(node);

            if (!permutations.contains(word) && node.accepting) {
                permutations.add(word);
            }
        

        }



    }

    public String BubbleUp(Node node) {
        ArrayList<Character> chars = new ArrayList<Character>();
        
        Node n = node;

        while (n.parent != null) {
            chars.add(n.value);
            n = n.parent;
        }

        java.util.Collections.reverse(chars);

        String out = "";

        for (Character c : chars) {
            out += c;
        }

        return out;

    }



    public void generateAnagramsSets(int minlength, int maxlength, int target) throws Exception {

        // finish porting

        if (minlength > maxlength || minlength < 0 || maxlength < 2 || target < 0)
        {
            throw new Exception("Invalid parameters.");
        }

        // generate list of words in tree with length = maxlength

        InputStream is = getClass().getClassLoader().getResourceAsStream(WORDSNDIR + File.separator + "words" + maxlength +".txt");

        Scanner scanner = new Scanner (is);
        ArrayList<String> wordsFromFile = new ArrayList<String>();

        
        // read 

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.toLowerCase().trim();
            wordsFromFile.add(line);
        }

        // take each word in this list, and generate all of it's permutations


        try {
            
            URI uri = getClass().getClassLoader().getResource(".").toURI();
            String mainpath = Paths.get(uri).toString() + File.separator + WORDSETDIR;
            Path path = Paths.get(mainpath);
            Files.createDirectories(path);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (String word : wordsFromFile) {

            ArrayList<String> perms = findPermutations(word);

            ArrayList<String> output = new ArrayList<String>();
            
            
            for (String perm : perms) {
                // filter these permutations according to minlength
                if (!(perm.length() < Math.max(2, minlength))) {
                    output.add(perm);
                }


                // if the root word has created at least <target> permutations, write this wordset to wordset directory

            }
            

            if (output.size() > target) {


                String lol = getClass().getClassLoader().getResource(WORDSETDIR).getPath();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(lol + File.separator + word +".txt")));
                    for (String s : output) {
                        writer.write(s+"\n");
                    }
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //write the file

            }




        }



        scanner.close();

    }




    public void wordsLengthN (ArrayList<String> words) {

        HashMap<Integer,ArrayList<String>> wordsbylength = new HashMap<Integer,ArrayList<String>>();

        for (String word : words) {

            word = word.toLowerCase().trim();

            if (word.length() > 0) {
                if (!wordsbylength.containsKey(word.length())) {
                    wordsbylength.put(word.length(), new ArrayList<String>());
                } 

                wordsbylength.get(word.length()).add(word);
            }


        }

        try {
            
            URI uri = getClass().getClassLoader().getResource(".").toURI();
            String mainpath = Paths.get(uri).toString() + File.separator + WORDSNDIR;
            Path path = Paths.get(mainpath);
            Files.createDirectories(path);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (Map.Entry<Integer, ArrayList<String>> entry : wordsbylength.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<String> value = entry.getValue();

            String lol = getClass().getClassLoader().getResource(WORDSNDIR).getPath();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(lol + File.separator + "words" + key +".txt")));
                for (String s : value) {
                    writer.write(s+"\n");
                }
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        
        }



    }

    // wordsLengthN. 



    // add code for wordsearch
        // will find a bunch of random words, add them to list
        // will then generate a board
        // scan the board for words not in list
        // add to list / tree



}
