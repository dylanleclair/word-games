package com.circles.wordgames;

public interface INode {
    

	/**
	 * Returns the parent of this node
	 * @param parent
	 */
    void setParent (INode parent);
    INode getParent ();

    /**
     * Sets the accepting value
     * @param accepting - whether or not this node is an end of a word.
     */
    void setAccepting (boolean accepting);
    
    /**
     * Retrieves the accepting value for this node.
     * @return whether or not this node is an end of a word.
     */
    boolean getAccepting ();

    /**
     * Sets the letter value represented by this node
     * @param value the letter value
     */
    void setValue (Character value);
    
    /**
     * Retrieves the letter value of this node
     * @return the letter value
     */
    Character getValue ();

    /**
     * Adds a node to the collection of child nodes for this node
     * @param value the value of the node to be created and added as a child
     * @return the created node
     */
    INode addChild(Character value);
    /**
     * Retrieves a child node from a node, if it exists. 
     * @param value
     * @return The child node, if it exists. null otherwise.
     */
    INode getChild(Character value);


}
