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

Now that I had standardized methods I could use to add a child and retreive a child from either type of Node, it then became possible to **extract the methods** for adding words and finding words from LightWordTree and FastWordTree **into the parent class** for each of these: WordTree (SHA: 4a29870).


4.What code in which files was the result of the refactoring.

This is described above.

5.How was the code tested? 

I simply reused the tests I wrote for each tree in my previous refactoring to ensure that the functionality of my code remained the same after changing it's structure so dramatically.

I did make a small change to the test code by changing the declared type of each tree to IWordTree, and testing out the shared interface methods.

They passed the tests as expected.

6.Why is the code better structured after the refactoring? 

The code is vastly improved after this stage of refactoring, since we can work with each of the different types of trees in exactly the same way. 

This means that regardless of whether we choose to work with a FastTree or a LightTree, we can add and find words in the exact same manner. 

Instead of having to look at different references in my code if it were a library, a developer could simply have a look at the WordTree class and get an idea of how it functions and how exactly to use it -- instead of looking at the separate documentation for each tree and working with the different functions they had provided originally.

7.Does the result of the refactoring suggest or enable further refactorings?

Yes! Now that we've gutted a lot of the duplicated code from both types of Tree and both types of Node, they're looking a little **lazy**.

