/**file: SplayTree.java
 * Description: Implements a lot of Binary Search Tree - Most of this code
 * is a mix from our lab and a mix from lecture.
 */

public class SplayTree<E extends Comparable<E>> {

    private SplayNode<E> root;
    private int count = 0;
    private int size = 0;

    /** Constructor **/
    public SplayTree() {
        root = null;
    }

    /** Function to check if the tree is empty **/
    private boolean isEmpty() {
        return root == null;
    }

    /** Function to clear the tree **/
    public void clear() {
        root = null;
        size = 0;
    }

    /** Method to get the size of the tree
     * @return size - The size of the tree
     * **/
    public int getSize() {
        return size;
    }


    /**Add the element to the correct location
     * all elements to the left are less than the parent
     * all elements to the rights are greater than the parent
     * Do not allow duplicates
     * @param it the element to insert
     */
    public void insert(E it){
        // Create our node containing the element we want to insert
        SplayNode<E> newNode = new SplayNode<E>(it);
        // If we don't have a root, make a root
        if(root == null){
            root = newNode;
            size++;
            return;
        }
        // Already have a root
        // Create null parent
        SplayNode<E> parent = null;
        // create a node to represent the root
        SplayNode<E> node = root;
        // While we are not at our leaf node
        while(node != null) {
            // Our parent becomes the root
            parent = node;
            // Comparing new element to create with the root
            int compareResult = it.compareTo(node.getElement());
            // Node is the root
            // New node is smaller than the root, checking to see if there is a left node
            // If it is a null value, we leave the while
            if(compareResult < 0) {
                node = node.getLeft();
            }
            // New node is larger than the root, checking for right node
            // If it is a null value, we leave the while
            else if(compareResult > 0) {
                node = node.getRight();
            }
            // Duplicate, leaving
            else {
                return;
            }
        }
        // Comparing our new node to its parent (our current leaf)
        int res = it.compareTo(parent.getElement());
        // Less than parent, setting node to the left
        if (res < 0){
            parent.setLeft(newNode);
            newNode.setParent(parent);
            Splay(newNode);
            size++;
        }
        // Greater than parent, setting node to the right
        else{
            parent.setRight(newNode);
            newNode.setParent(parent);
            Splay(newNode);
            size++;
        }
    }

    /**Returns the height of the tree
     * if tree is empty, height is -1
     * if tree only has one node, height is 0
     * @return the integer height of the tree
     *
     */
    public int getHeight(){
        int height = -1;
        QueueList<SplayNode>queue = new QueueList<SplayNode>();
        if (isEmpty()) {
            System.out.println("Tree is empty");
            return height;
        }
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            int count = queue.size();
            height++;
            // dequeue all the nodes on current level and
            // enqueue all the nodes on the level below
            while (count > 0) {
                SplayNode<E> node = queue.dequeue();
                if (node.hasLeft()) {
                    queue.enqueue(node.getLeft());
                }
                if (node.hasRight()) {
                    queue.enqueue(node.getRight());
                }
                count--;
            }
        }
        return height;
    }

    /** Method to get the root
     * @return root - the root of our tree
     */
    public SplayNode<E> getRoot() {
        return root;
    }

    /** The same as the search method, but will determine the number of comparisons made to find the Element
     * @return comparisons - The amount of comparisons to find the Element
     */
    public int getComparisons(E it) {
        SplayNode<E> previousNode = null;
        SplayNode<E> node = root;
        int comparisons = 0;
        while (node != null) {
            previousNode = node;
            int compareResult = it.compareTo(node.getElement());
            comparisons++;
            if (compareResult > 0) {
                node = node.getRight();
            }
            else if (compareResult < 0) {
                node = node.getLeft();
            }
            else {
                Splay(node);
                return comparisons;
            }
        }
        if (previousNode != null) {
            Splay(previousNode);
            return comparisons;
        }
        return comparisons;
    }

    /**
     * Method to find a node in the tree, helps the search method
     * @param it - The Element we are looking for
     * @return node if we find it or null if it does not exist
     */
    public SplayNode<E> findNode(E it) {
        SplayNode<E> previousNode = null;
        SplayNode<E> node = root;
        int comparisons = 0;
        while (node != null) {
            previousNode = node;
            int compareResult = it.compareTo(node.getElement());
            comparisons++;
            if (compareResult > 0) {
                node = node.getRight();
            }
            else if (compareResult < 0) {
                node = node.getLeft();
            }
            else {
                Splay(node);
                return node;
            }
        }
        if (previousNode != null) {
            Splay(previousNode);
            return null;
        }
        return null;
    }

    /**Remove a specific element**/
    public void remove(E it) {
        SplayNode<E> node = findNode(it);
        remove(node);
    }

    /**Removes the node that contains it.
     * If the tree does not contain it, it prints that to
     * the user and does nothing else.
     * Otherwise it removes the node and maintains the
     * Splay properties
     * if removing a node with two children, replace it
     * with its inorder predecessor.
     * @param node the node you want to remove
     */
    private void remove(SplayNode<E> node) {
        if (node == null) {
            return;
        }
        Splay(node);
        if ((node.getLeft() != null) && (node.getRight() != null)) {
            SplayNode<E> minimum = node.getLeft();
            while (minimum.getRight() != null) {
                minimum = minimum.getRight();
            }
            minimum.setRight(node.getRight());
            node.getRight().setParent(minimum);
            node.getLeft().setParent(null);
            root = node.getLeft();
        }
        else if (node.getRight() != null) {
            node.getRight().setParent(null);
            root = node.getRight();
        }
        else {
            root = null;
        }
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);
        node = null;
        size--;
    }


    /** Function to search for an element **/
    public boolean search(E it) {
        return findNode(it) != null;
    }

    /** Helper method
     * For removal you need to swap elements of nodes
     * @param node1 , node2 the nodes whose contents you are swapping
     */
    private void swapElements(SplayNode<E> node1, SplayNode<E> node2){
        E element1 = node1.getElement();
        E element2 = node2.getElement();
        node1.setElement(element2);
        node2.setElement(element1);
    }


    /** Method to Splay our tree which will reorganize it whenever we add, remove or search for a node.
     */
    private void Splay(SplayNode<E> x) {
        // While we are not at the root
        while (x.getParent() != null) {
            // Grab the parent
            SplayNode<E> Parent = x.getParent();
            // Grab the grandparent
            SplayNode<E> GrandParent = Parent.getParent();
            //zig if we are only one level down
            if (GrandParent == null) {
                // If we are the left child of the parent
                if (x == Parent.getLeft()) {
                    makeLeftChildParent(x, Parent);
                }
                // If we are the right child of the parent
                else {
                    makeRightChildParent(x, Parent);
                }
            }
            // If we are two levels down
            else {
                // If we are left child of the parent
                if (x == Parent.getLeft()) {
                    // left-left child zig zig
                    // If our parent is the grandparents left child : zig-zig
                    if (Parent == GrandParent.getLeft()) {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    // zig zag
                    // If our parents is the right child of the grandparent
                    else {
                        makeLeftChildParent(x, Parent);
                        makeRightChildParent(x, x.getParent());
                    }
                }
                // zig-zag
                // If we are the right child of our parent
                else {
                    // If our parent is the left child of the grandparent
                    if (Parent == GrandParent.getLeft()) {
                        makeRightChildParent(x, Parent);
                        makeLeftChildParent(x, x.getParent());
                    }
                    // zig-zig
                    // If our parent is the right child of the grandparent
                    else {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        } //End the while
        // Our value passed is now the root
        root = x;
    }

    /** Helper method for our Splay to rotate making our left child a parent
     * @param c - The child node
     * @param p - The parent node
     */
    private void makeLeftChildParent(SplayNode<E> c, SplayNode<E> p) {
        if(p.getParent() != null) {
            if (p == p.getParent().getLeft()) {
                p.getParent().setLeft(c);
            }
            else {
                p.getParent().setRight(c);
            }
        }
        if (c.getRight() != null) {
            c.getRight().setParent(p);
        }
        c.setParent(p.getParent());
        p.setParent(c);
        p.setLeft(c.getRight());
        c.setRight(p);
    }

    /** Helper method for our Splay to rotate making our right child a parent
     * @param c - The child node
     * @param p - The parent node
     */
    private void makeRightChildParent(SplayNode<E> c, SplayNode<E> p) {
        if (p.getParent() != null) {
            if (p == p.getParent().getLeft()) {
                p.getParent().setLeft(c);
            }
            else {
                p.getParent().setRight(c);
            }
        }
        if (c.getLeft() != null) {
            c.getLeft().setParent(p);
        }
        c.setParent(p.getParent());
        p.setParent(c);
        p.setRight(c.getLeft());
        c.setLeft(p);
    }

    /**prints each level of the tree on its own line
     * use your Queue class
     */
    public void printLevelOrder(){
        QueueList<SplayNode> queue = new QueueList<SplayNode>();
        int levelNodes = 0;
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            levelNodes = queue.size();
            while(levelNodes>0) {
                SplayNode<E> n = queue.dequeue();
                System.out.print(n.getElement() + "   ");// separate the elements on that level by a space
                if (n.hasLeft()) {
                    queue.enqueue(n.getLeft());
                }
                if (n.hasRight()) {
                    queue.enqueue(n.getRight());
                }
                levelNodes--;
            }
            System.out.println(); // Go to next line
        }
    }

    /** Function for inorder traversal **/
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(SplayNode r)
    {
        if (r != null)
        {
            inorder(r.getLeft());
            System.out.print(r.getElement() +" ");
            inorder(r.getRight());
        }
    }

    /** Function for preorder traversal **/
    public void preorder()
    {
        preorder(root);
    }

    private void preorder(SplayNode r)
    {
        if (r != null)
        {
            System.out.print(r.getElement() +" ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    /** Function for postorder traversal **/
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(SplayNode r)
    {
        if (r != null)
        {
            postorder(r.getLeft());
            postorder(r.getRight());
            System.out.print(r.getElement() +" ");
        }
    }

    /**prints the tree in an inorder fashion.
     * uses a stack to push left children onto the stack
     */
    public void printInOrder(){
        StackList<SplayNode> stack = new StackList<SplayNode>();
        SplayNode<E> node = root;
        pushLeftNodesToStack(stack, node);
        while (!stack.isEmpty()) {
            SplayNode<E> n = stack.pop();
            System.out.println(n.getElement());
            n = n.getRight();
            pushLeftNodesToStack(stack, n);
        }
    }

    /** helper method for in order printByDepth
     */
    private void pushLeftNodesToStack(StackList<SplayNode> s, SplayNode<E> b) {
        while (b != null) {
            s.push(b);
            b = b.getLeft();
        }
    }

}
