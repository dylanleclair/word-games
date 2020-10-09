package com.circles.wordgames;

public interface IWordTree {
	/**
	 * Adds a word to this tree.
	 * @param word the word to add
	 */
    void addWord(String word);
    
    /**
     * Searches for a word in this tree
     * @param word - the word to search for
     * @return true if the word is found, false if it is not
     */
    boolean findWord(String word);
}
