# wordgames
 My first CPSC 501 assignment.


 # Change 1: Code duplication

1. What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

2. What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

The bad code smell in this was definitely duplicated code. More specifically, I had similar code in sibling classes - specifically FastTree and WordTree, as well as FastNode and Node. 

3. What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 

I used the form template method. Essentially, I took the code that was actually shared between the classes and moved them into a superclass. 

To do this, I first duplicated WordTree, and renamed this copy to LightWordTree (SHA: 56c419a) - named in accordance with the fact that Lists often take up less storage then Hashmaps and this sort of tree would be "lighter" than FastWordTree.

I then declared LightWordTree and FastWordTree as child classes to WordTree (SHA: 418fa54). 

Then, I removed all of the duplicated methods from both LightWordTree and FastWordTree - which highlighted some changes I would have to make to privacy (SHA: a048997).   

At this point, the similar code in sibling classes had been extracted into a **superclass** and the differing code remained in the **subclassess**, following the **form template method** to a T!

However, that was only for the trees as a whole. It was time to do the same thing for the nodes, and remove any duplicated properties they had!

I started by duplicating Node and renaming this duplicate "LightNode" as I had done with "LightTree". (SHA: 02fffe4)

Then, I went ahead and remove duplicated code by turning Node into a superclass of FastNode and LightNode and removing the code shared between them (SHA: a41eff2). 

4. What code in which files was the result of the refactoring.

The code described above was changed.

5. How was the code tested? 

I wrote some tests to build and find words in each of the different types of trees (SHA: ba60d95). 

This would make sure that the trees were working properly with the change in structure. 

I kept these simple, since I knew I would be making some more changes to the trees and could always build on them in the future.

To try and limit test (SHA: f5bd486), I also tested to see if the tree would accept the empty string, substrings of words that are included, and single letters. 

6. Why is the code better structured after the refactoring? 

A lot of duplicated code became neatly wrapped in a parent class, making the new subclasses far more simple. The differences are a lot clearer and whenever changes need to be made for a WordTree in general, they only need to be made to the parent class, and will get automatically carried down to the child classes. 

7. Does the result of the refactoring suggest or enable further refactorings?

Now that we've changed the structure of trees, it's time for the nodes to follow. They have a similar problem with duplicated code, but also 

# Change 2: Implementing some interfaces (BRANCH & MERGE)

1.What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

LightTree, FastTree, WordTree, LightNode, FastNode and Node.

(include some pictures)

2.What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

The bad code smell in this was alternative classes.

LightTree and FastTree accomplish the same task: providing a word-tree to be used in generating and running a game of anagrams.

LightNode and FastNode both serve the same task as well: provide a node interface for WordTree to use. 

By making the Trees and Nodes implement an interface, I code ensure that they had the necessary functionality, and share even more code with the superclasses (WordTree and Node). 

An example I came across of displays a bit of a shotgun surgery that was resulting from my code structure: whenever I wanted to get a LightNode, I would have to use the hasChild to identify it and letters.get() to actually retreive the child node. Whenever I modified a bit of this code, I would have to change it across every method this action was performed.

By making INode implement a getChild method that returns the child node, I was able to make this consistent across implementations and carry the code into the parent class.

3.What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 


I started by first defining actual interfaces for Node and WordTree, called INode (SHA: 78a54fd) and IWordTree (SHA: 870c464). 


Then I went ahead and implemented INode in both types of nodes. (SHA: 184a683).

Now that I had standardized methods I could use to add a child and retreive a child from either type of Node, it then became possible to **extract the methods** for adding words and finding words from LightWordTree and FastWordTree **into the parent class** for each of these: WordTree (SHA: 4a29870). I **renamed** the methods of LightNode to match the interface. Then, my interface allowed me to **extract** the findWord and addWord methods in the different forms of trees to  **to the superclass**.


4.What code in which files was the result of the refactoring.

This is described above.

5.How was the code tested? 

I simply reused the tests I wrote for each tree in my previous refactoring to ensure that the functionality of my code remained the same after changing it's structure so dramatically.

I did make a small change to the test code by changing the declared type of each tree to IWordTree, and testing out the shared interface methods.

They passed the tests as expected.

Then, to really capitalize on how useful interfaces can be, I created a list of IWordTrees - containing both a FastWordTree and a LightWordTree, then iterated over it, testing if the expected words were found and the fake words were not. 

At first this didn't pass, but after some debugging, I found that I forgot to remove a field from FastWordTree that was replaced by WordTree. After fixing this, the tests worked! My interface implementation was spotless.  (SHA: 595b50a)

6.Why is the code better structured after the refactoring? 

The code is vastly improved after this stage of refactoring, since we can work with each of the different types of trees in exactly the same way. 

This means that regardless of whether we choose to work with a FastTree or a LightTree, we can add and find words in the exact same manner. 

Instead of having to look at different references in my code if it were a library, a developer could simply have a look at the WordTree class and get an idea of how it functions and how exactly to use it -- instead of looking at the separate documentation for each tree and working with the different functions they had provided originally.

7.Does the result of the refactoring suggest or enable further refactorings?

Yes! Now that we've gutted a lot of the duplicated code from both types of Tree and both types of Node, they're looking a little **lazy**.

# Change 3: Combining the classes for Nodes and Trees. 

1.What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

The code in Node and WordTree were paired together into IWordTree.

The code in LightWordTree and LightNode were paired together into LightWordTree.

The code in FastWordTree and FastNode were paired together into FastWordTree.

2.What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

I had a number of **lazy classes**. After moving around so much code in the first two refactorings, all of these classes were looking pretty empty. Additionally, it just makes sense for the nodes of a tree, which are only ever accessed by that tree, to be inline with the tree class.

3.What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 

Along with the **move method** in other refactorings, I eliminated these problems with inline class. 

I brought Node inline with WordTree (SHA: e95a49e).

I brought LightNode inline with LightWordTree (SHA: 4da1e89).

Finally, I brought FastNode inline with FastWordTree (SHA: 84e10ff)


4.What code in which files was the result of the refactoring.

This is described above.

5.How was the code tested? 

Like the last refactoring, I ran all my tests again to ensure that my code maintained it's functionality with these changes.

6.Why is the code better structured after the refactoring? 

Instead of having to open a huge amount of files to change the inner workings of a given kind of tree, they are now all in one (appropriately labelled) place. 

All relevant code is grouped nicely together for future development!

7.Does the result of the refactoring suggest or enable further refactorings?

We are at the point where we are pretty much done with all the Nodes (Node, FastNode, LightNode).

However, our main WordTree class is huge and a lot of it's code, while relevant to WordTrees, is largely unrelated to the actual function of the tree, suggesting that this class is **too long**!

# Change 4: Switch statements!

1. What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

The classes for Anagrams and Hangman. 

2. What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

Switch statements. This could easily be replaced with a much nicer polymorphic implementation. 

3. What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 

I used the replaced conditional with polymorphism method. 

To start this process, I first defined an interface, IGame, and an abstract class Game to serve as the building blocks / parent class to Anagrams and Hangman (SHA: fa2c2ab). While doing this, I took some of the duplicated code (getInput) and threw it into the Game class.
 

4. What code in which files was the result of the refactoring.

5. How was the code tested? 

6. Why is the code better structured after the refactoring? 

7. Does the result of the refactoring suggest or enable further refactorings?
