/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Project_Number: Project 3
 * @File: Graph.java
 */

// Imports required for performing IO operations
import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;

/***
 * The Graph class creates a Graph (in the form of an adjacency matrix) from the
 * input file and RedBlackTree. It contains functions to create the adjacency matrix,
 * print the adjacency matrix and color the graph with the least possible colors.
 */
public class Graph {

    // Stores the graph
    int[][] graph;

    // To write to the output file
    static PrintWriter fileOutput;

    // Create output file called "result.txt" in src/ folder
    static {
        try {
            fileOutput = new PrintWriter("src/result.txt");

            // Add first file to the output file
            fileOutput.print("shpatel");
        }
        // Handle FileNotFoundException
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /***
     * Constructor to initialize the graph of size total_nodes
     * @param total_nodes Size of the graph
     */
    Graph(int total_nodes) {
        graph = new int[total_nodes][total_nodes];
    }

    /***
     * Function to generate adjacency matrix from RedBlackTree and input file
     * @param file_name Input file with course details
     * @param tree Generated RedBlackTree from input file
     * @throws FileNotFoundException IO Exception
     */
    public void generateAdjacencyMatrix(String file_name, RedBlackTree tree) throws FileNotFoundException {

        // Create a file object of the input file
        File myObj = new File("src/" + file_name);

        // Create a Scanner object of the file to be read
        Scanner myReader = new Scanner(myObj);

        // While there is a line in file
        while (myReader.hasNextLine()) {

            // Read next line from input file
            String data = myReader.nextLine();

            // Split line based on space
            String[] data_split = data.split(" ");

            // For every course in line
            for (int i = 2; i < data_split.length - 1; i++) {

                // Get course number from course name
                tree.getCourseNumber(data_split[i], tree.root);

                // Course number of first course
                int course1 = tree.courseNumberReturned;

                // For every course in line
                for (int j = i + 1; j < data_split.length; j++) {

                    // Get course number from course name
                    tree.getCourseNumber(data_split[j], tree.root);

                    // Course number of second course
                    int course2 = tree.courseNumberReturned;

                    // Update graph
                    graph[course1][course2] = 1;
                    graph[course2][course1] = 1;
                }
            }
        }
    }

    /***
     * Function to print adjacency matrix
     * @throws IOException IO Exception
     */
    public void printAdjacencyMatrix() throws IOException {

        // Add a new line in output file
        fileOutput.println();

        // Loop through the graph generated
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {

                // Print graph to output file
                fileOutput.print(graph[i][j]);
            }
            // Add a new line in output file
            fileOutput.println();
        }
        // Flush to output file
        fileOutput.flush();
    }

    /***
     * Function to color graph with the least possible number of colors
     * @param tree Generated RedBlack tree
     */
    // Reference for graph coloring: https://www.tutorialspoint.com/Graph-Coloring#
    public void colorGraph(RedBlackTree tree) {

        // Stores colors of nodes
        int[] newclr = new int[graph.length];

        // Color first node with default first color
        newclr[0] = 0;

        // Stores colors used
        boolean[] colorUsed = new boolean[graph.length];

        // Initialize newclr for all nodes
        for (int i = 1; i < graph.length; i++) {
            newclr[i] = -1;
        }

        // Initialize colorUsed with false
        for (int i = 0; i < graph.length; i++) {
            colorUsed[i] = false;
        }

        // Loop through uncolored vertex in graph
        for (int v = 1; v < graph.length; v++) {

            // Loop through newclr
            for (int w = 0; w < graph.length; w++) {
                // If there is an edge between v and w
                if (graph[v][w] == 1) {
                    // If w is not colored
                    if (newclr[w] != -1) {
                        // Color it
                        colorUsed[newclr[w]] = true;
                    }
                }
            }

            int col;

            // Find color that is not assigned yet
            for (col = 0; col < graph.length; col++) {
                if (!colorUsed[col]) {
                    break;
                }
            }

            // Assign the newly found color
            newclr[v] = col;

            // Make color availability to false for next iteration
            for (int w = 0; w < graph.length; w++) {
                if (graph[v][w] == 1) {
                    if (newclr[w] != -1) {
                        colorUsed[newclr[w]] = false;
                    }
                }
            }
        }

        // Stores maximum number of periods required to conduct the exam
        int max_periods = -1;

        // Find maximum number of periods required
        for (int i = 0; i < newclr.length; i++) {
            if (newclr[i] > max_periods) {
                max_periods = newclr[i];
            }
        }

        // Print the exam schedule to output file
        for (int i = 0; i <= max_periods; i++) {
            fileOutput.print("\nFinal Exam Period " + (i + 1) + " =>");
            for (int j = 0; j < graph.length; j++) {
                if (newclr[j] == i) {
                    tree.getCourseName(j, tree.root);
                    fileOutput.print(" " + tree.courseNameReturned);
                }
            }
        }

        // Add an empty line to output file
        fileOutput.println();

        // Flush output to the results file
        fileOutput.flush();
    }
}