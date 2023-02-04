/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Project_Number: Project 3
 * @File: RedBlackNode.java
 */

/***
 * The RedBlackNode class represents a node in the RedBlackTree to be constructed.
 * Each node contains information related to color of the node, course number, course name,
 * parent node, left child, and right child.
 */
public class RedBlackNode {

    // Stores the integer value of RED node
    public static final int RED = 1;

    // Stores the integer value of BLACK node
    public static final int BLACK = 0;

    // Stores the name of course
    java.lang.String data;

    // Stores the color of node
    int color;

    // Stores reference to parent node
    RedBlackNode p;

    // Stores reference to left child
    RedBlackNode lc;

    // Stores reference to rigth child
    RedBlackNode rc;

    // Stores course number
    int course_number;

    /***
     * Constructor to initialize a RedBlackNode in the RedBlackTree
     * @param data Course name stored in the node
     * @param color Color of the node
     * @param p Reference to the parent RedBlackNode
     * @param lc Reference to the left child RedBlackNode
     * @param rc Reference to the right child RedBlackNode
     * @param course_number Course number of the course referred by the RedBalckNode
     */
    public RedBlackNode(java.lang.String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc, int course_number) {

        // Initialize the RedBlackNode
        this.data = data;
        this.color = color;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
        this.course_number = course_number;
    }

    // Returns the color of the RedBlackNode
    public int getColor() {
        return color;
    }

    // Returns the course name stored in the RedBlackNode
    public java.lang.String getData() {
        return data;
    }

    // Returns the RedBlackNode reference of the parent
    public RedBlackNode getP() {
        return p;
    }

    // Returns the RedBlackNode reference of the left child
    public RedBlackNode getLc() {
        return lc;
    }

    // Returns the RedBlackNode reference of the right child
    public RedBlackNode getRc() {
        return rc;
    }

    // Returns the RedBlackNode reference of the parent
    public int getCourse_number() {
        return course_number;
    }

    // Set the color of the RedBlackNode
    public void setColor(int color) {
        this.color = color;
    }

    // Set the RedBlackNode reference of the parent
    public void setP(RedBlackNode p) {
        this.p = p;
    }

    // Set the RedBlackNode reference of the left child
    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    // Set the RedBlackNode reference of the right child
    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }
}
