package com.circles.wordgames;

import java.util.ArrayList;

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
