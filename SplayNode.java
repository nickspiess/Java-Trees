public class SplayNode<E> {

    /** Node for a Splay tree*/

    private E element; // Value for node
    private SplayNode<E> left;
    private SplayNode<E> right;
    private SplayNode<E> parent;

    /** Constructor for Splay node
     * @param it - The element the node contains
     */
    public SplayNode(E it) {
        element = it;
        left = null;
        right = null;
        parent = null;
    }

    /**
     * A constructor for a SplayNode
     * @param it - The element the node will contain
     * @param l - Node to the left of this node
     * @param r - Node to the right of this node
     */
    public SplayNode(E it, SplayNode<E> l, SplayNode<E> r){
        element = it;
        left = l;
        right = r;
    }

    /**
     * @return E - The element of the node
     */
    public E getElement() {
        return element;
    }

    /**
     * @return the string representation of what is in the node
     */
    public String toString(){
        return element.toString();
    }

    /** sets the element in the node
     * @param it the item to be placed in the node
     */
    public E setElement(E it) {
        return element = it;
    }

    /** determines if the node has kids
     * @return if the node is a leaf
     */
    public boolean isLeaf(){
        return( left == null && right == null);
    }

    /**
     * Method to get the node to the left
     * @return The node to the left
     */
    public SplayNode<E> getLeft() {
        return left;
    }

    /**
     * Method to get the node to the right
     * @return The node to the right
     */
    public SplayNode<E> getRight() {
        return right;
    }

    /**
     * Method to get the parent node
     * @return The node of the parent
     */
    public SplayNode<E> getParent() {
        return parent;
    }

    /**
     * Method to set the node to the left
     */
    public void setLeft(SplayNode<E> left) {
        this.left = left;
    }

    /**
     * Method to set the node to the right
     */
    public void setRight(SplayNode<E> right) {
        this.right = right;
    }

    /**
     * Method to set the parent node
     */
    public void setParent(SplayNode<E> parent) {
        this.parent = parent;
    }

    /**
     * @return if the node has a left child
     */
    public boolean hasLeft(){
        return left != null;
    }
    /**
     * @return if the node has a right child
     */
    public boolean hasRight(){
        return right !=null;
    }
}
