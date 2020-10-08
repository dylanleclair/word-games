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
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LightWordTree {

    private final String WORDSNDIR = "wordsbylength";
    private final String WORDSETDIR = "wordsets";

    public Node root;

    public LightWordTree () throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(App.ANAGRAMSWORDLIST);

        Scanner scanner = new Scanner (is);
        ArrayList<String> wordsFromFile = new ArrayList<String>();

        root = new Node(null, '0');

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.toLowerCase().trim();
            addWord(line);
            wordsFromFile.add(line);
        }

        wordsLengthN(wordsFromFile);
        
        scanner.close();
    }

    // to be used when running an anagrams game
    public LightWordTree (List<String> words) throws Exception {;

        root = new Node(null, '0');
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    
    }



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


    public void addWord (String word) throws Exception {
        Node n = root;
        
        for (int i = 0; i<word.length();i++) {
            if (n.hasChild(word.charAt(i))) {
                n = n.letters.get(n.indexOf(word.charAt(i)));
            } else {
                n = n.addChild((Character) word.charAt(i));
            }
        }

        n.accepting = true;

    }


    public boolean findWord (String word) throws Exception{
        Node n = root;
        for (int i = 0; i < word.length(); i++) {
            if (n.hasChild(word.charAt(i))) {
                n = n.letters.get( n.indexOf(word.charAt(i)));
            } else {
                return false;
            }
        }

        // at final node
        if (n.accepting) {
            return true;
        } else {
            return false;
        }

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