package com.circles.wordgames;

import java.util.ArrayList;

public abstract class WordTree implements IWordTree {


    protected INode root;

    public void addWord (String word) {
        INode n = root;
        
        for (int i = 0; i<word.length();i++) {
            
            INode check = n.getChild(word.charAt(i));
            if (check != null) {
                n = check;
            } else {
                n = n.addChild((Character) word.charAt(i));
            }
        }

        n.setAccepting(true);

    }

    public WordTree() {

    }

    public WordTree(INode root, ArrayList<String> words) {
        this.root = root;
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    }

    public boolean findWord (String word) {
        INode n = root;
        for (int i = 0; i < word.length(); i++) {

            INode check = n.getChild(word.charAt(i));

            if (check != null) {
                n = check;
            } else {
                return false;
            }
            
        }

        // at final node
        if (n.getAccepting()) {
            return true;
        } else {
            return false;
        }

    }



    public static String BubbleUp(INode node) {
        ArrayList<Character> chars = new ArrayList<Character>();
        
        INode n = node;

        while (n.getParent() != null) {
            chars.add(n.getValue());
            n = n.getParent();
        }

        java.util.Collections.reverse(chars);

        String out = "";

        for (Character c : chars) {
            out += c;
        }

        return out;

    }



    abstract class Node  { 
    
        private INode parent;
        private Character value;
        private boolean accepting;
    
        public Node () {
    
        }
    
        public void setParent (INode parent) {
            this.parent = parent;
        }
        public INode getParent () {
            return parent;
        }
    
        public void setAccepting (boolean accepting) {
            this.accepting = accepting;
        }
        public boolean getAccepting () {
            return accepting;
        }
    
        public void setValue (Character value) {
            this.value = value;
        }
        public Character getValue () {
            return value;
        }
    
    
        public Node (INode parent, Character value) {
            this.parent = parent;
            this.value = value;
            accepting = false;
        }
    
    
    }
    

}


