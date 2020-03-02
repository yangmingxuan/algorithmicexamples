package algorithms.btree;

/****
 * @author Mingxuan Yang
 * 
 */
public class BTreeNode {
    int data;
    BTreeNode left, right;
    
    public BTreeNode() {
        this.left = null;
        this.right = null;
    }
    public BTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
