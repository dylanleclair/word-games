package com.circles.wordgames;

import java.util.List;
import java.util.HashMap;

/**
 * A WordTree implementation that uses HashMaps under the hood for faster operations at the cost of storage.
 * @author Dylan Leclair
 *
 */
public class FastWordTree extends WordTree {

    // to be used when running an anagrams game
	/**
	 * Creates a FastWordTree from a set of given words.
	 * @param words the words to be added to the tree
	 */
    public FastWordTree (List<String> words) {;

        root = new FastNode(null, '0');
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    
    }

    /**
     * Creates an empty FastWordTree.
     */
    public FastWordTree () {;

        root = new FastNode(null, '0');
    
    }


    /**
     * A Node implementation that uses HashMaps to keep track of children.
     * @author Dylan Leclair
     *
     */
    class FastNode extends Node implements INode {

    	/**
    	 * The children of this node.
    	 */
        protected HashMap<Character, FastNode> letters;
    
        /**
         * Creates a fastnode with a given parent and value.
         * @param parent - the FastNode acting as this node's parent
         * @param value - the value to be stored in this node.
         */
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
    
        @Override
        public FastNode getChild (Character value) {
            return letters.get(Character.valueOf(value));
        }
    
    }

}
