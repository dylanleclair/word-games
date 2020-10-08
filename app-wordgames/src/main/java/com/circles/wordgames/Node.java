package com.circles.wordgames;

public abstract class Node  { 
    
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
