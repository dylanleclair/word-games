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

1. What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

LightTree, FastTree, WordTree, LightNode, FastNode and Node.

(include some pictures)

2. What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

The bad code smell in this was alternative classes.

LightTree and FastTree accomplish the same task: providing a word-tree to be used in generating and running a game of anagrams.

LightNode and FastNode both serve the same task as well: provide a node interface for WordTree to use. 

By making the Trees and Nodes implement an interface, I code ensure that they had the necessary functionality, and share even more code with the superclasses (WordTree and Node). 

An example I came across of displays a bit of a shotgun surgery that was resulting from my code structure: whenever I wanted to get a LightNode, I would have to use the hasChild to identify it and letters.get() to actually retreive the child node. Whenever I modified a bit of this code, I would have to change it across every method this action was performed.

By making INode implement a getChild method that returns the child node, I was able to make this consistent across implementations and carry the code into the parent class.

3. What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 


I started by first defining actual interfaces for Node and WordTree, called INode (SHA: 78a54fd) and IWordTree (SHA: 870c464). 


Then I went ahead and implemented INode in both types of nodes. (SHA: 184a683).

Now that I had standardized methods I could use to add a child and retreive a child from either type of Node, it then became possible to **extract the methods** for adding words and finding words from LightWordTree and FastWordTree **into the parent class** for each of these: WordTree (SHA: 4a29870). I **renamed** the methods of LightNode to match the interface. Then, my interface allowed me to **extract** the findWord and addWord methods in the different forms of trees to  **to the superclass**.


4. What code in which files was the result of the refactoring.

This is described above.

5. How was the code tested? 

I simply reused the tests I wrote for each tree in my previous refactoring to ensure that the functionality of my code remained the same after changing it's structure so dramatically.

I did make a small change to the test code by changing the declared type of each tree to IWordTree, and testing out the shared interface methods.

They passed the tests as expected.

Then, to really capitalize on how useful interfaces can be, I created a list of IWordTrees - containing both a FastWordTree and a LightWordTree, then iterated over it, testing if the expected words were found and the fake words were not. 

At first this didn't pass, but after some debugging, I found that I forgot to remove a field from FastWordTree that was replaced by WordTree. After fixing this, the tests worked! My interface implementation was spotless.  (SHA: 595b50a)

6. Why is the code better structured after the refactoring? 

The code is vastly improved after this stage of refactoring, since we can work with each of the different types of trees in exactly the same way. 

This means that regardless of whether we choose to work with a FastTree or a LightTree, we can add and find words in the exact same manner. 

Instead of having to look at different references in my code if it were a library, a developer could simply have a look at the WordTree class and get an idea of how it functions and how exactly to use it -- instead of looking at the separate documentation for each tree and working with the different functions they had provided originally.

7. Does the result of the refactoring suggest or enable further refactorings?

Yes! Now that we've gutted a lot of the duplicated code from both types of Tree and both types of Node, they're looking a little **lazy**.

# Change 3: Combining the classes for Nodes and Trees. 

1. What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

The code in Node and WordTree were paired together into IWordTree.

The code in LightWordTree and LightNode were paired together into LightWordTree.

The code in FastWordTree and FastNode were paired together into FastWordTree.

2. What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

I had a number of **lazy classes**. After moving around so much code in the first two refactorings, all of these classes were looking pretty empty. Additionally, it just makes sense for the nodes of a tree, which are only ever accessed by that tree, to be inline with the tree class.

3. What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 

Along with the **move method** in other refactorings, I eliminated these problems with inline class. 

I brought Node inline with WordTree (SHA: e95a49e).

I brought LightNode inline with LightWordTree (SHA: 4da1e89).

Finally, I brought FastNode inline with FastWordTree (SHA: 84e10ff)


4. What code in which files was the result of the refactoring.

This is described above.

5. How was the code tested? 

Like the last refactoring, I ran all my tests again to ensure that my code maintained it's functionality with these changes.

6. Why is the code better structured after the refactoring? 

Instead of having to open a huge amount of files to change the inner workings of a given kind of tree, they are now all in one (appropriately labelled) place. 

All relevant code is grouped nicely together for future development!

7. Does the result of the refactoring suggest or enable further refactorings?

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

Next, I removed the switch and implemented the interface methods in place of the switch logic in Anagrams. This also gave me the oppourtunity to remove the gamestate field from Anagrams (SHA: 6c5e30b).

On the same commit, I quickly whipped up a static method in App to run any sort of IGame (SHA: 6c5e30b), since this is where games would be loaded and run.

After this, I did the exact same sort of modifications to Hangman, which had a similar structure to Anagrams thanks to my switch statement and States enum (SHA: 30bba00).

Now, my States enum was completely defunct and no longer necessary to keep around so I booted it from the project. 

5. How was the code tested? 

I tested this code by running each of the games in my command line and ensuring they still behaved the same way after the structural changes.

Then, I took this one step further, researched how to simulate actual console input / output and implemented this into a test. 

I started with Anagrams. 

It took a while and I had to pull it into a regular class to make sure it ran properly, but I eventually configured it correctly (SHA: 4095480). 

The code essentially sets up and runs the game, "guesses" a bunch of anagrams of the rootword, until it reaches 10 complete words. 

After which, it examines the (simulated) console output to see if the winning message was printed out - which it should. 

After I got this working, I took this test code and modified it to work with and test Hangman - both for a win and loss instances, since there's actually a way to lose in this game (at least with the current implementations) (SHA: 18504f1). 

6. Why is the code better structured after the refactoring? 

Not only is it well tested for future changes to the structure of the code, we eliminated the switch statement and made great use of object oriented paradigms. Whenever we want to alter the code for a part of a game, we simply just have to change the part of the class that deals with it. 

It is also much more readable and compact than the switch statement, which was huge. 

Additionally, this completely avoids a problem that might crop up in the future of the development of these games, where they may have different varieties, or the gameplay is more advanced (time limits, score goals, etc.). Instead of having to use (one or several) switch statements, or god forbid, having a gargantuan switch that handles everything (and adding more and more states to States.java) they can simply add methods to the IGame interface and implement them as necessary. (ex: runGameTimed(...) if they wanted to make a timed version of anagrams)

7. Does the result of the refactoring suggest or enable further refactorings?

Yes. Specifically in my test code. They're not the prettiest or paradigm-perfect tests, but they get the job done and took me too long to make for me to try and refactor them... at least not until I've finished the rest of the assignment.


# Change 5: Divergent Change (large classes)

1. What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

My code for WordTree was altered. If I wanted to change the code concerning the actual tree structure, I would have to scroll through tons of code for generating sets of anagrams instead.

2. What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

The bad code smell I have here is large classes - the WordTree class is huge and a lot of it’s methods, while concerned with WordTrees, don’t actually serve a purpose in the structure of the tree and would be more suited to a static helper class.

When I wanted to change the behaviour of the tree, I was dealing with addWord, findWord and it's constructors. 

This left findPermutations, generateAnagramsSets, and wordsLengthN left out to dry. 

I was doing exactly what is described: changing half the code in WordTree for one purpose, and changing the rest for another.

3. What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 

I used the **extract class** method. I simply moved the operations of this class into a new helper class titled "WordLists.java". (SHA: 1cbc521)

I later decided BubbleUp would be more appropriate in the WordTree class as it is concerned with a common tree operation: returning a word in the tree from an accepting node. (SHA: b4fa64c)

5. How was the code tested? 

This code will be tested once I finish refactoring the code it's concerned with (see change #6). 

6. Why is the code better structured after the refactoring? 

The code is more organized and code for specific purposes is sectioned off to where it actually belongs. 

7. Does the result of the refactoring suggest or enable further refactorings?

I don't think so, but it could be argued. 

# Change 6: Speculative generality

1.What code in which files was altered. (don’t include the full source, only the parts relevant to the refactoring).

At first I had been trying to configure my paths / resources so that I could just package a set of generated lists of anagrams with my code, but kept having different issues with file reading from an IDE vs a JAR and have given up on this specific feature. 

I now face the task of getting rid of it all and replacing it with a runtime anagram-list generator instead. 

While this hurts the memory for a teensy bit (loading large wordlists can take a chunk of memory), once it has the wordlist it needs, the massive wordlist can be tossed into the trash.

2.What needed to be improved? That is, what “bad code smell” was detected? Use the terminology found in the Fowler text. 

The bad code smell I have here is speculative generality. I added code for future expansion, but if I’m being honest with myself, the feature is a lot more trouble to implement then it seems to be worth (at least given the time I have to finish this assignment!)

3.What refactoring was applied? What steps did you follow? Use the terminology and mechanics outlined in the Fowler text. 

I completely gutted all the file I/O code used to generate and toy with word lists, and replaced them with runtime alternatives.

I also had to change how generateAnagramsWordsets worked to only generate a single wordset. 

Both of the above changes were committed on (SHA: 5653357). Sorry I didn't break it up, but I ended up changing more than I anticipated.

As I was updating the class however, I realized I was using a long list of parameters in some of my functions.

To solve this problem pre-emptively, I created an inline Parameters class, and used it to send minlength, maxlength and target all in one go. This fits under **introducing a parameter object**.

Why refactor when you can write good code the first time!

Next, I realized that with my new code to generate an anagrams list in WordLists made it so that the constructors default I had in FastWordTree and LightWordTree were unnecessary, so I removed the file I/O from them and made proper default constructors (SHA: 9ff26ce, c84caa7).

5.How was the code tested? 

I used the tests I had written beforehand for each of the games to ensure those were still functioning. 

I also added a few tests to ensure that my parameters were being enforced.

I started by writing a test to ensure that only lists of anagrams with the target number of words would be generated. (SHA: a88e09d)

Then I moved on to making sure that words were of the correct length (SHA: 4697c14). This exposed a bug where I chose to do Math.min instead of Math.max in a filterPermutations. I fixed it on SHA: c016aa3.

Next, I tested to see whether or not findPermutations only gave valid words in the tree that were, in fact, permutations of the first word. (SHA: 86a47bb). Note that there are a number of valid anagrams that are tested to be false - this because they are not in the wordlist the tree is built from and should not be considered valid permutations.

I also made sure to test that any permutation contained only letters in the root word. (SHA: cb2f34e)

6.Why is the code better structured after the refactoring? 

Now, only code that is actually useful and runs - whether it be in an IDE or in a JAR, is included in the project.

It also allowed me to make a few other changes to simplify my code in other areas. 

7.Does the result of the refactoring suggest or enable further refactorings?

No, finally. While more functionality could be added and later refactored, I think the core project code (excluding tests) is pretty much the best it's going to get.
