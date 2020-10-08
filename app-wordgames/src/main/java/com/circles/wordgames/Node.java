package com.circles.wordgames;

public abstract class Node  { 
    
    private INode parent;
    private Character value;
    private boolean accepting;

    public Node () {

    }

    void setParent (INode parent) {
        this.parent = parent;
    }
    INode getParent () {
        return parent;
    }

    void setAccepting (boolean accepting) {
        this.accepting = accepting;
    }
    boolean getAccepting () {
        return accepting;
    }

    void setValue (Character value) {
        this.value = value;
    }
    Character getValue () {
        return value;
    }


    public Node (INode parent, Character value) {
        this.parent = parent;
        this.value = value;
        accepting = false;
    }


}
