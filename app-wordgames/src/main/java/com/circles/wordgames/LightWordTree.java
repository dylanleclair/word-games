package com.circles.wordgames;

import java.util.ArrayList;
import java.util.List;

/**
 * List based implementation of WordTree
 * @author Dylan Leclair
 *
 */
public class LightWordTree extends WordTree {

	/**
	 * Creates an empty LightWord tree and adds a list of provided words to it
	 * @param words the words to construct the tree from
	 */
    public LightWordTree (List<String> words) {;

        root = new LightNode(null, '0');
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    
    }

    /**
     * Creates an empty LightWord tree
     */
    public LightWordTree () {;

        root = new LightNode(null, '0');
    
    }


    /**
     * List based implementation of Node
     * @author Dylan Leclair
     *
     */
    public class LightNode extends Node implements INode {
    
    /**
     * The set of child nodes of this node
     */
    protected ArrayList<LightNode> letters;

    /**
     * Constructs a new node with specified parent and value
     * @param parent - the parent of the new Node
     * @param value - the letter this Node represents
     */
    public LightNode (LightNode parent, Character value) {
        super(parent,value);
        letters = new ArrayList<LightNode>();
    }

    @Override
    public INode addChild(Character value) {
        LightNode n = new LightNode(this,value);
        letters.add(n);
        return n;
    }

    @Override
    public INode getChild(Character value) {

        for (INode item : letters) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }


}

}

