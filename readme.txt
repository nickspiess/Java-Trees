Compare Three Trees:

1. File List : All must be in same directory
- AVLNode.java
- AVLTree.java
- BinarySearchTree.java
- BSTNode.java
- Node.java
- Place.java
- Processor.java
- Queue.java
- QueueList.java
- SplayNode.java
- SplayTree.java
- Stack.java
- StackList.java
- TestSplay.java
- Trees.java
- zips.txt
- data.txt (This file does not need to be included but includes the data output information)

2. In order to run this code, you must run it within an IDE, or from a command line with no arguments.
- If running from the command line, you must first compile all the Java programs using "javac *.java". Then,
you input "java <file_to_run>"
- The "file_to_run" options are TestSplay.java which will input all the results of the TestSplay class to a file called:
TestSplay.txt, or, you can run Trees.java, which will read through all the data, input the trees and give data comparisons
in a file called data.txt.

 3. I decided to make a processor class again.  This helps being able to hold all the data instead of having to constantly
 pass different ArrayLists and trees back and forth and makes them accessible very easily.

 4. Overall, this project wasn't too difficult, I only struggled being able to figure out some Splaying issues on my
 SplayTree.java class.  I had an issue where when you assign a left or right node, it didn't automatically assign the
 parent node, and one of the methods relied on knowing if the parent was null or not, so this messed up a lot and took me
 awhile to figure out.  Regardless, it made me trace the methods thoroughly and helped me understand the trees a lot more.