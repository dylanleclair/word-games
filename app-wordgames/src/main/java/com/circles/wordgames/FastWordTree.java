package com.circles.wordgames;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

public class FastWordTree extends WordTree {


    public FastNode root;

    public FastWordTree () throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(App.ANAGRAMSWORDLIST);

        Scanner scanner = new Scanner (is);
        ArrayList<String> wordsFromFile = new ArrayList<String>();

        root = new FastNode(null, '0');

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
    public FastWordTree (List<String> words) throws Exception {;

        root = new FastNode(null, '0');


        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    
    }


    class FastNode extends Node implements INode {

        protected HashMap<Character, FastNode> letters;
    
        public FastNode (FastNode parent, char value)   {
            super(parent,value);
            letters = new HashMap<Character, FastNode>();
        }
    
        @Override
        public FastNode addChild (Character value) {
            FastNode n = new FastNode(this,value);
            letters.put(value,n);
            return n;
        }
    
        public boolean hasChild (char value) {
            if (letters.containsKey(Character.valueOf(value))) {
                return true;
            } else {
                return false;
            }
        }
    
        @Override
        public FastNode getChild (Character value) {
            return letters.get(Character.valueOf(value));
        }
    
    }

}
