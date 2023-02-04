/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Project_Number: Project 3
 * @File: RedBlackTree.java
 */

/***
 * The RedBlackTree class creates a Red-Black Tree which acts as a dictionary to store the
 * distinct course names and the corresponding numbers assigned to them.
 */
public class RedBlackTree {

    // Stores the RedBlackNode reference to the null node of the RedBlackTree
    RedBlackNode null_node;

    // Stores the RedBlackNode reference of the root of the RedBlackTree
    RedBlackNode root;

    // Stores the boolean flag to know if course is present in the RedBlackTree
    boolean containsCourseFlag;

    // Stores the value of the course number referred to by the course name
    int courseNumberReturned;

    // Stores the value of the course name referred to by the course number
    String courseNameReturned;

    /***
     * Constructor to initialize the null node of a RedBlackTree.
     * Initialize the parent, left child, and right child of the null node to null,
     * the color of the null node will be BLACK, course name would be an empty string
     * and course number would store a dummy value of 99.
     */
    public RedBlackTree() {

        // Stores the null node of the RedBlackTree
        null_node = new RedBlackNode("", RedBlackNode.BLACK, null, null, null, 99);

        // Initially, point the root to the null node
        root = null_node;
        containsCourseFlag = false;
    }

    /***
     * Function to insert a RedBlackNode to the RedBlackTree
     * @param data Course name that would be stored in the RedBlackNode
     * @param course_number Course number that would be stored in the RedBlackNode
     * @Pre-condition: Memory is available for insertion
     */
    public void insert(java.lang.String data, int course_number) {

        // Initially make RedBlackNode y to point to the null node
        RedBlackNode y = null_node;

        // Initially make RedBlackNode x to point to root
        RedBlackNode x = root;

        // Create a new RedBlackNode z with the course name and course number
        // z's parent, left child and right child initially point to the null node
        RedBlackNode z = new RedBlackNode(data, RedBlackNode.RED, null_node, null_node, null_node, course_number);

        // While x does not equal to the null node
        while (x != null_node) {

            // Update y to be x
            y = x;

            // If z's course number is less than x's course number
            if (z.getCourse_number() < x.getCourse_number()) {
                // Update x to be x's left child
                x = x.getLc();
            }
            // If z's course number is greater than or equal to x's course number
            else {
                // Update x to be x's right child
                x = x.getRc();
            }
        }

        // Set parent of z to be y
        z.setP(y);

        // If y is null node
        if (y == null_node) {
            // Update root to be z
            root = z;
        }
        // If y is not null node
        else {
            // If z's course number is less than y's course number
            if (z.getCourse_number() < y.getCourse_number()) {
                // Update y's left child to be z
                y.setLc(z);
            }
            // If z's course number is greater than or equal to y's course number
            else {
                // Update y's right child to be z
                y.setRc(z);
            }
        }

        // Perform Red-Black fix up after insert
        RBInsertFixup(z);
    }

    /***
     * Function to left rotate a RedBlackNode x
     * @param x RedBlackNode to be left rotated
     * @Pre-condition: right[x] != null_node and root's parent is null_node
     */
    public void leftRotate(RedBlackNode x) {

        // Initialize a RedBlackNode y, to be right child of x
        RedBlackNode y = x.getRc();

        // Update x's right child to be y's left child
        x.setRc(y.getLc());

        // Update y's left child's parent to be x
        y.getLc().setP(x);

        // Update y's parent to be x's parent
        y.setP(x.getP());

        // If x's parent points to the null node
        if (x.getP() == null_node) {
            // Set root to be y
            root = y;
        }
        // If x's parent does not point to the null node
        else {
            // If x is equal to x's parent's left child
            if (x == x.getP().getLc()) {
                // Update x's parent's left child to be y
                x.getP().setLc(y);
            }
            // If x is not equal to x's parent's left child
            else {
                // Update x's parent's right child to be y
                x.getP().setRc(y);
            }
        }
        // Update y's left child to be x
        y.setLc(x);

        // Update x's parent to be y
        x.setP(y);
    }

    /***
     * Function to right rotate a RedBlackNode x
     * @param x RedBlackNode to be right rotated
     * @Pre-condition: pre: left[x] != null_node and root's parent is null_node
     */
    public void rightRotate(RedBlackNode x) {

        // Initialize a RedBlackNode y, to be left child of x
        RedBlackNode y = x.getLc();

        // Update x's left child to be y's right child
        x.setLc(y.getRc());

        // Update y's right child's parent to be x
        y.getRc().setP(x);

        // Update y's parent to be x's parent
        y.setP(x.getP());

        // If x's parent points to null node
        if (x.getP() == null_node) {
            // Update root to be y
            root = y;
        }
        // If x's parent does not point to null node
        else {
            // If x equals to x's parent's left child
            if (x == x.getP().getLc()) {
                // Update x's parent's left child to be y
                x.getP().setLc(y);
            }
            // If x does not equals to x's parent's left child
            else {
                // Update x's parent's right child to be y
                x.getP().setRc(y);
            }
        }
        // Update y's right child to be x
        y.setRc(x);

        // Update x's parent to be y
        x.setP(y);
    }

    /***
     * Function to perform Red-Black fixup on RedBlackNode z
     * @param z RedBlackNode on which the Red-Black fixup is to be performed
     */
    public void RBInsertFixup(RedBlackNode z) {

        // While z's parent's color is RED
        while (z.getP().getColor() == RedBlackNode.RED) {

            // If z's parent is equal to z's parent's parent's left child
            if (z.getP() == z.getP().getP().getLc()) {

                // Initialize RedBlackNode y to be z's parent's parent's right child
                RedBlackNode y = z.getP().getP().getRc();

                // If y's color is RED
                if (y.getColor() == RedBlackNode.RED) {

                    // Update z's parent's color to be BLACK
                    z.getP().setColor(RedBlackNode.BLACK);

                    // Update y's color to be BLACK
                    y.setColor(RedBlackNode.BLACK);

                    // Update z's parent's parent's color to be RED
                    z.getP().getP().setColor(RedBlackNode.RED);

                    // Update z to be z's parent's parent
                    z = z.getP().getP();
                }
                // If y's color is not RED
                else {

                    // If z is equal to z's parent's right child
                    if (z == z.getP().getRc()) {

                        // Update z to be z's parent
                        z = z.getP();

                        // Left rotate z
                        leftRotate(z);
                    }

                    // Update z's parent's color to be BLACK
                    z.getP().setColor(RedBlackNode.BLACK);

                    // Update z's parent's parent's color to be RED
                    z.getP().getP().setColor(RedBlackNode.RED);

                    // Right rotate z's parent's parent
                    rightRotate(z.getP().getP());
                }
            }
            // If z's parent is not equal to z's parent's parent's left child
            else {

                // Initialize RedBlackNode y to be z's parent's parent's left child
                RedBlackNode y = z.getP().getP().getLc();

                // If y's color is equal to RED
                if (y.getColor() == RedBlackNode.RED) {

                    // Update z's parent's color to be BLACK
                    z.getP().setColor(RedBlackNode.BLACK);

                    // Update y's color to be BLACK
                    y.setColor(RedBlackNode.BLACK);

                    // Update z's parent's parent's color to be RED
                    z.getP().getP().setColor(RedBlackNode.RED);

                    // Update z to be equal to z's parent's parent
                    z = z.getP().getP();
                }
                // If y's color is not equal to RED
                else {
                    // If z is equal to z's parent's left child
                    if (z == z.getP().getLc()) {

                        // Update z to be z's parent
                        z = z.getP();

                        // Right rotate z
                        rightRotate(z);
                    }

                    // Update z's parent's color to be BLACK
                    z.getP().setColor(RedBlackNode.BLACK);

                    // Update z's parent's parent's color to be RED
                    z.getP().getP().setColor(RedBlackNode.RED);

                    // Left rotate z's parent's parent
                    leftRotate(z.getP().getP());
                }
            }
        }
        // Set color of root to be BLACK
        root.setColor(RedBlackNode.BLACK);
    }

    /***
     * Function to check if course name is present in RedBlackTree
     * @param v Course name to check for in the RedBlackTree
     * @return True if course name is present in the RedBlack Tree
     */
    public boolean contains(java.lang.String v) {

        // If root is not null node
        if (root != null_node) {

            // Initialize r to be the root of the RedBlackTree
            RedBlackNode r = root;

            // Recursive check if course is present tree
            containsCourse(v, r);
        }
        // Return true if course is present in tree, else return false
        return containsCourseFlag;
    }

    /***
     * Recursive function to check if course name is present in RedBlackTree pointed to by root
     * @param v Course name to check for in the RedBlackTree
     * @param root Root of the RedBlackTree to be searched
     */
    public void containsCourse(java.lang.String v, RedBlackNode root) {

        // Get course name pointed to by root
        String data = root.getData();

        // If course name pointed to by root equals v
        if (v.equals(data)) {
            // Update flag
            containsCourseFlag = true;
        }
        // If course name pointed to by root does not equals v
        else {
            // Recursively search left child
            if (root.getLc() != null_node) {
                containsCourse(v, root.getLc());
            }
            // Recursively search right child
            if (root.getRc() != null_node)  {
                containsCourse(v, root.getRc());
            }
        }
    }

    /***
     * Function to get the course number corresponding to course name v in RedBlackTree
     * pointed to by the root
     * @param v Course name whose course number is to be searched in the RedBlackTree
     * @param root Root of the RedBlackTree
     */
    public void getCourseNumber(java.lang.String v, RedBlackNode root) {

        // Get the course name pointed to by root node
        String data = root.getData();

        // If course name equals v
        if (v.equals(data)) {
            // Update course number
            courseNumberReturned = root.getCourse_number();
        }

        // Recursively search left child of root
        if (root.getLc() != null_node) {
            getCourseNumber(v, root.getLc());
        }

        // Recursively search right child of root
        if (root.getRc() != null_node) {
            getCourseNumber(v, root.getRc());
        }
    }

    /***
     * Function to get course name pointed to by course number i in the RedBlackTree
     * @param i Course number whose corresponding name is to be found
     * @param root Root of the RedBlackTree which is to be searched
     */
    public void getCourseName(int i, RedBlackNode root) {

        // Get course number of course pointed to by root node
        int courseNumber = root.getCourse_number();

        // If course number matches i
        if (courseNumber == i) {
            // Update course name returned
            courseNameReturned = root.getData();
        }

        // Recursively search left child of root
        if (root.getLc() != null_node) {
            getCourseName(i, root.getLc());
        }

        // Recursively search right child of root
        if (root.getRc() != null_node) {
            getCourseName(i, root.getRc());
        }
    }
}
