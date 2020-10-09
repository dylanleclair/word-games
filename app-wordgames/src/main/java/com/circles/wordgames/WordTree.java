package com.circles.wordgames;


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


