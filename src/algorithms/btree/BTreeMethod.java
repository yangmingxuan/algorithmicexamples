package algorithms.btree;

/***
 *
 * @author Mingxuan Yang
 *
 */
public class BTreeMethod {

    /***
     * print the btree in preorder
     * @param btreenode
     */
    public static void printNodePreorder(BTreeNode btreenode) {
        if (btreenode == null) {
            return;
        }
        System.out.print(btreenode.data + " ");
        printNodePreorder(btreenode.left);
        printNodePreorder(btreenode.right);
    }

    /***
     * print the btree in inorder
     * @param btreenode
     */
    public static void printNodeInorder(BTreeNode btreenode) {
        if(btreenode == null) {
            return;
        }
        printNodeInorder(btreenode.left);
        System.out.print(btreenode.data + " ");
        printNodeInorder(btreenode.right);
    }

    /***
     * print the btree in postorder
     * @param btreenode
     */
    public static void printNodePostorder(BTreeNode btreenode) {
        if(btreenode == null) {
            return;
        }
        printNodePostorder(btreenode.left);
        printNodePostorder(btreenode.right);
        System.out.print(btreenode.data + " ");
    }

    /***
     *
     * @param prequeue
     * @param inqueue
     * @return
     */
    public static BTreeNode buildFromPreIn(int prequeue[], int inqueue[]) {
        if(prequeue == null || inqueue == null) {
            System.err.println("Input param ares error!");
            return null;
        }
        int lenPre = prequeue.length;
        int lenIn = inqueue.length;
        if(lenPre != lenIn) {
            System.err.println("Input params are error!");
            return null;
        }
        //initial position
        int pPre = 0, pIn = 0;
        return buildFromPreIn(prequeue, inqueue, pPre, pIn, lenPre);
    }

    /***
     *
     * @param prequeue
     * @param inqueue
     * @param PrePosition
     * @param Inposition
     * @param len
     * @return
     */
    private static BTreeNode buildFromPreIn(int prequeue[], int inqueue[], int PrePosition, int Inposition, int len) {
        /***
         * There is no element in it
         */
        if(len <= 0 || PrePosition + len > prequeue.length || Inposition + len > inqueue.length) {
            return null;
        }
        if(PrePosition >= prequeue.length || Inposition >= inqueue.length) {
            return null;
        }
        /***
         * The root node is the prequeue's first element
         */
        BTreeNode btree = new BTreeNode(prequeue[PrePosition]);

        /***
         * find the root node in the inqueue's position
         */
        int i = Inposition;
        for(i = Inposition; i < Inposition + len; i ++) {
            if(inqueue[i] == prequeue[PrePosition]) {
                break;
            }
        }
        if(i > Inposition + len) {
            // if no found
            System.err.println("Input params are error-!");
            return null;
        }

        /***
         * inqueue's left is left child
         */
        int leftlen = i - Inposition;
        btree.left = buildFromPreIn(prequeue, inqueue, PrePosition+1, Inposition, leftlen);

        /***
         * inqueue's right is right child
         */
        int rightlen = len - leftlen - 1;
        btree.right = buildFromPreIn(prequeue, inqueue, PrePosition+1+leftlen, i+1, rightlen);

        return btree;
    }

    /***
     *
     * @param postqueue
     * @param inqueue
     * @return
     */
    public static BTreeNode buildFromPostIn(int postqueue[], int inqueue[]) {
        if(postqueue == null || inqueue == null) {
            System.err.println("Input param ares error!");
            return null;
        }
        int lenPost = postqueue.length;
        int lenIn = inqueue.length;
        if(lenPost != lenIn) {
            System.err.println("Input params are error!");
            return null;
        }
        //initial position
        int pPost = 0, pIn = 0;
        return buildFromPostIn(postqueue, inqueue, pPost, pIn, lenPost);
    }

    /***
     *
     * @param postqueue
     * @param inqueue
     * @param PostPosition
     * @param Inposition
     * @param len
     * @return
     */
    private static BTreeNode buildFromPostIn(int postqueue[], int inqueue[], int PostPosition, int Inposition, int len) {
        /***
         * There is no element in it
         */
        if(len <= 0 || PostPosition + len > postqueue.length || Inposition + len > inqueue.length) {
            return null;
        }
        if(PostPosition >= postqueue.length || Inposition >= inqueue.length) {
            return null;
        }

        /***
         * The root node is the postqueue's last element
         */
        BTreeNode btree = new BTreeNode(postqueue[PostPosition + len - 1]);

        /***
         * find the root node in the inqueue's position
         */
        int i = Inposition;
        for(i = Inposition; i < Inposition + len; i ++) {
            if(inqueue[i] == postqueue[PostPosition + len - 1]) {
                break;
            }
        }
        if(i > Inposition + len) {
            // if no found
            System.err.println("Input params are error--!");
            return null;
        }

        /***
         * inqueue's left is left child
         */
        int leftlen = i - Inposition;
        btree.left = buildFromPostIn(postqueue, inqueue, PostPosition, Inposition, leftlen);

        /***
         * inqueue's right is right child
         */
        int rightlen = len - leftlen - 1;
        btree.right = buildFromPostIn(postqueue, inqueue, PostPosition+leftlen, i+1, rightlen);

        return btree;
    }

    /***
     * Full BTree -- Each node must has 0 or 2 child(ren).
     * @param prequeue
     * @param postqueue
     * @return
     */
    public static BTreeNode buildFullFromPrePost(int prequeue[], int postqueue[]) {
        if(prequeue == null || postqueue == null) {
            System.err.println("Input param ares error!");
            return null;
        }
        int lenPre = prequeue.length;
        int lenPost = postqueue.length;
        if(lenPre != lenPost) {
            System.err.println("Input params are error!");
            return null;
        }
        //initial position
        int pPre = 0, pPost = 0;
        return buildFullFromPrePost(prequeue, postqueue, pPre, pPost, lenPre);
    }

    /***
     *
     * @param prequeue
     * @param postqueue
     * @param PrePosition
     * @param PostPosition
     * @param len
     * @return
     */
    private static BTreeNode buildFullFromPrePost(int prequeue[], int postqueue[], int PrePosition, int PostPosition, int len) {
        /***
         * There is no element in it
         */
        if(len <= 0 || PrePosition + len > prequeue.length || PostPosition + len > postqueue.length) {
            return null;
        }
        if(PrePosition >= prequeue.length || PostPosition >= postqueue.length) {
            return null;
        }

        /***
         * The root node is the prequeue's first element
         */
        BTreeNode btree = new BTreeNode(prequeue[PrePosition]);

        /***
         * len == 1 indicate the node has 0 child
         */
        if(len == 1) {
            return btree;
        }
        // prequeue's next element is lefechild
        int leftchild = prequeue[PrePosition+1];
        // postqueue's previous element is rightchild
        //int rightchild = postqueue[PostPosition + len - 2];

        /***
         * find the left child in the postqueue's position
         */
        int i = PostPosition;
        for(i = PostPosition; i < PostPosition + len; i ++) {
            if(postqueue[i] == leftchild) {
                break;
            }
        }
        if(i > PostPosition + len) {
            // if no found
            System.err.println("Input params are error-!");
            return null;
        }

        /***
         * postqueue's left is left child
         */
        int leftlen = i - PostPosition + 1;
        btree.left = buildFullFromPrePost(prequeue, postqueue, PrePosition+1, PostPosition, leftlen);

        /***
         * postqueue's right is right child (except the last one)
         */
        int rightlen = len - leftlen - 1;
        btree.right = buildFullFromPrePost(prequeue, postqueue, PrePosition+1+leftlen, i+1, rightlen);


        return btree;
    }

    /***
     *
     * @param btree
     * @return
     */
    public static BTreeNode reverseBtree(BTreeNode btree) {
        if (btree == null) {
            return null;
        }
        BTreeNode temp = btree.left;
        btree.left = btree.right;
        btree.right = temp;
        temp = null;
        reverseBtree(btree.left);
        reverseBtree(btree.right);

        return btree;
    }

}
