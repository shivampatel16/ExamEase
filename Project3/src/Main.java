/**
 * @Author: Shivam Patel
 * @Andrew_ID: shpatel
 * @Course: 95-771 Data Structures and Algorithms for Information Processing
 * @Project_Number: Project 3
 * @File: Main.java
 */

// Imports required for performing IO operations
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // Stores the number of courses in the RedBlackTree
    static int maxCourseNumberSoFar = 0;

    public static void main(String[] args) {

        // All input files are in the "src/" folder
        String[] input_files = new String[] {"input1.txt", "input2.txt"};

        // For all input files
        for (int a = 0; a < input_files.length; a++){

            // Create a new RedBlackTree
            RedBlackTree tree = new RedBlackTree();

            // Source to read file: https://www.w3schools.com/java/java_files_read.asp
            try {
                // Create a file object of the input file
                File myObj = new File("src/" + input_files[a]);

                // Create a Scanner object of the file to be read
                Scanner myReader = new Scanner(myObj);

                // Boolean flag to store if course name is present in tree
                boolean containFlag;

                // While there is a line in file
                while (myReader.hasNextLine()) {

                    // Read next line from input file
                    String data = myReader.nextLine();

                    // Split line based on space
                    String[] data_split = data.split(" ");

                    // For every course in line
                    for (int i = 2; i < data_split.length; i++) {

                        // Check if course is present in RedBlackTree
                        containFlag = tree.contains(data_split[i]);

                        // If course is not present in RedBlackTree
                        if (!tree.containsCourseFlag) {

                            // Insert course name and course number in RedBlackTree
                            tree.insert(data_split[i], maxCourseNumberSoFar);

                            // Increment course number for next course
                            maxCourseNumberSoFar++;
                        }
                        // Update flag for next course in line
                        tree.containsCourseFlag = false;
                    }
                }

                // Total nodes for the graph
                int total_nodes = maxCourseNumberSoFar;

                // Create a new graph of total nodes
                Graph graph = new Graph(total_nodes);

                // Generate adjacency matrix from RedBlackTree
                graph.generateAdjacencyMatrix(input_files[a], tree);

                // Print adjacency matrix of the generated graph
                graph.printAdjacencyMatrix();

                // Color the graph
                graph.colorGraph(tree);

                // Close myReader
                myReader.close();
            }
            // Handle FileNotFound exception
            catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            // Handle IO Exception
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Update total course numbers for next input file
            maxCourseNumberSoFar = 0;
        }
        // Close PrinterWriter
        Graph.fileOutput.close();
    }
}
