/**
 *
 */
package algorithms.btree;


/**
 * @author Mingxuan Yang
 *
 */
public class BTreeExamples {

    /**
     * @param args
     */
    public static void main(String[] args) {
		/*
		 TODO Auto-generated method stub
		if(args[0])
			printNodeFromPreIn();
			printNodeFromPostIn();
    	   printFullNodeFromPrePost();
	*/
        printNode();

    }


    public static BTreeNode buildOneBtree() {
        BTreeNode btree = new BTreeNode(4);
        btree.left = new BTreeNode(3);
        btree.right = new BTreeNode(2);
        btree.left.left = new BTreeNode(6);
        btree.left.right = new BTreeNode(7);
        btree.right.left = new BTreeNode(8);
        btree.right.right = new BTreeNode(9);
        btree.left.left.left = new BTreeNode(0);
        btree.left.left.right = new BTreeNode(1);
        btree.left.right.left = new BTreeNode(10);
        btree.left.right.right = new BTreeNode(5);
        btree.right.left.left = new BTreeNode(12);
        btree.right.left.right = new BTreeNode(11);
        btree.right.right.left = new BTreeNode(17);
        btree.right.right.right = new BTreeNode(16);
        return btree;
    }

    public static void printNode() {
        BTreeNode btree = buildOneBtree();
        BTreeMethod.printNodePreorder(btree);
        System.out.println();
        BTreeMethod.printNodeInorder(btree);
        System.out.println();
        BTreeMethod.printNodePostorder(btree);
        System.out.println();

        BTreeMethod.reverseBtree(btree);

        BTreeMethod.printNodePreorder(btree);
        System.out.println();
        BTreeMethod.printNodeInorder(btree);
        System.out.println();
        BTreeMethod.printNodePostorder(btree);
    }

    public static void printNodeFromPreIn() {
        int prequeue[] = {4,3,6,1,7,10,2,8,9,16};
        int inqueue[] =  {6,1,3,10,7,4,8,2,9,16};

        BTreeNode btree = BTreeMethod.buildFromPreIn(prequeue, inqueue);
        if(btree != null) {
            BTreeMethod.printNodePreorder(btree);
            System.out.println();
            BTreeMethod.printNodeInorder(btree);
            System.out.println();
            BTreeMethod.printNodePostorder(btree);
        }
    }

    public static void printNodeFromPostIn() {
        int postqueue[] = {1,6,10,7,3,8,16,9,2,4};
        int inqueue[] =  {6,1,3,10,7,4,8,2,9,16};

        BTreeNode btree = BTreeMethod.buildFromPostIn(postqueue, inqueue);
        if(btree != null) {
            BTreeMethod.printNodePreorder(btree);
            System.out.println();
            BTreeMethod.printNodeInorder(btree);
            System.out.println();
            BTreeMethod.printNodePostorder(btree);
        }
    }

    public static void printFullNodeFromPrePost() {
        int prequeue[] = {4,3,6,7,10,5,2,8,12,11,9};
        int postqueue [] = {6,10,5,7,3,12,11,8,9,2,4};

        BTreeNode btree = BTreeMethod.buildFullFromPrePost(prequeue, postqueue);
        if(btree != null) {
            BTreeMethod.printNodePreorder(btree);
            System.out.println();
            BTreeMethod.printNodeInorder(btree);
            System.out.println();
            BTreeMethod.printNodePostorder(btree);
        }
    }
}
