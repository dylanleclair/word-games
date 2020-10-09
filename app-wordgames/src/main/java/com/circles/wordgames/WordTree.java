package com.circles.wordgames;

import java.util.ArrayList;

public abstract class WordTree implements IWordTree {

	/**
	 * The root of the tree.
	 */
    protected INode root;

    /**
     * Adds a word to this WordTree.
     */
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

    /**
     * Blank constructor. Proper constructors are those found in LightWordTree and FastWordTree.
     */
    public WordTree() {

    }

    /**
     * Creates a WordTree with the specified root and list of words. Can be used to implicitly choose between a Fast and Light word tree, depending on the root node provided.
     * @param root - the root node to build the tree onto
     * @param words - the list of words to add to the tree.
     */
    public WordTree(INode root, ArrayList<String> words) {
        this.root = root;
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    }

    @Override
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


    /**
     * Starts at a node and traverses up to the root of the tree, finding the word spelled by the nodes it traverses.
     * @param node - an accepting node that encodes the last letter of a word
     * @return the word encoded by the root-node chain of nodes.
     */
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


    /**
     * An incomplete Node implementation that serves as the base class for LightNode and FastNode.
     * @author Dylan Leclair
     *
     */
    abstract class Node  { 
    
    	/**
    	 * The parent node of this node
    	 */
        private INode parent;
        
        /**
         * The letter stored by this node
         */
        private Character value;
        
        /**
         * True if the node represents the last letter of a word in the tree this node is part of.
         */
        private boolean accepting; 
    
        public Node () {
    
        }
    
        // Self explanatory getters and setters
        
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
    
        /**
         * A default constructor
         * @param parent the node to designate as this nodes parent
         * @param value the value to be stored by this node
         */
        public Node (INode parent, Character value) {
            this.parent = parent;
            this.value = value;
            accepting = false;
        }
    
    
    }
    

}


