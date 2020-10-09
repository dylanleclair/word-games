package com.circles.wordgames;

import java.util.ArrayList;
import java.util.List;

public class LightWordTree extends WordTree {

    public LightWordTree (List<String> words) {;

        root = new LightNode(null, '0');
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    
    }

    public LightWordTree () {;

        root = new LightNode(null, '0');
    
    }


    public class LightNode extends Node implements INode {
    
    protected ArrayList<LightNode> letters;

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

