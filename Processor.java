
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Processor{

    /**
     * File name: Processor.java
     * @author SPIESSNA
     * description: This program is a processor that will contain all our trees and necessary ArrayLists
     * for data calculation.  This program will verify that certain places haven't been added yet, will
     * create places, add them, search for nodes and will output desired information.
     * This program also will collect our data for the statistical analysis portion of the project.
     * Version: 5
     * @since 11/25/19
     */

    // Create our trees
    private BinarySearchTree bst;
    private AVLTree avl;
    private SplayTree st;
    // Create our ArrayLists
    private ArrayList<Place> towns;
    private ArrayList<String> townList;
    private ArrayList<Integer> numberSet;

    /** Constructor for the processor **/
    public Processor() {
        // Initialize our trees
        bst = new BinarySearchTree();
        avl = new AVLTree<>();
        st = new SplayTree();
        // Our array to hold all the towns to check if values already exist or not and add zips if needed
        towns = new ArrayList<Place>();
        townList = new ArrayList<String>();
    }

    /**
     * A helper method to return the index number of a place so we can search for it
     * @param place - The place we are searching for
     * @return indexNumber - The index number location in town ArrayList of the place we are searching for or -1
     * if it does not exist in the array
     */
    private int findPlace(Place place) {
        int indexNumber = -1;
        String town = place.getTown();
        String state = place.getState();
        // Iterate through list of towns to find index
        for (int i = 0; i <= towns.size() - 1; i++) {
            // If the town object string is the same as the town we are adding
            if (towns.get(i).toString().equals(town + state)) {
                indexNumber = i;
            }
        }
        return indexNumber;
    }

    /**
     * A method to create an instance of Place and check to see if it exists, if it doesn't, we will create the place
     * and add the zip.  If it does exist, we will add the zipcode to the place that already exists.
     * @param zip - The zip of the place we are looking to add
     * @param town - The name of the town/city of the Place
     * @param state - The name of the state of the Place
     */
    void createPlace(String zip, String town, String state) {
        // Issues of comparisons in attempting to find if a string of towns exists
        int index;
        String townAndState = town + " " + state;
        // If our array of places contains the existing value
        if (townList.contains(townAndState)) {
            index = townList.indexOf(townAndState);
            int indexNumber = 0;
            // Iterate through list of towns to find index
            for (int i = 0; i <= towns.size() - 1; i++) {
                // If the town object string is the same as the town we are adding
                if (towns.get(i).toString().equals(townAndState)) {
                    // Add that zip to the town object
                    towns.get(i).addZip(zip);
                    indexNumber = i;
                }
            }
        }
        // The instance of the town has not been created, so we will create it
        else {
            // Create the instance of the place
            Place place = new Place(town, state);
            // Add the zip
            place.addZip(zip);
            // Add the town to the list of places
            towns.add(place);
            // Add the town string to the list of the town names
            townList.add(townAndState);
            // Add the instance to the trees
            insert(place);
        }
    }

    /**
     * A helper method to insert our place into each tree
     * @param place - The place we are adding
     */
    private void insert(Place place) {
        bst.insert(place);
        avl.insert(place);
        st.insert(place);
    }

    /**
     * A method to search for a node in our tree.  If it doesn't exist, we will notify the user.
     * Regardless if it exists, we will report the amount of comparisons it took to figure this out.
     * @param place - The place we are searching for
     */
    public void searchForNode(Place place) {
        // If we don't have the place added
        if (findPlace(place) == -1) {
            // Inform user
            System.out.println("There is not a city by this name.");
            // Return comparisons
            System.out.println("The number of comparisons needed to find the entry in the Splay Tree: " + st.getComparisons(place));
            System.out.println("The number of comparisons needed to find the entry in the Binary Search Tree: " + bst.getComparisons(place));
            System.out.println("The number of comparisons needed to find the entry in the AVL Tree: " + avl.getComparisons(place));
        }
        // There is a place
        else {
            // Update place
            place = towns.get(findPlace(place));
            // Return comparisons
            System.out.println();
            System.out.println("The number of comparisons needed to find the entry in the Splay Tree: " + st.getComparisons(place));
            System.out.println("The number of comparisons needed to find the entry in the Binary Search Tree: " + bst.getComparisons(place));
            System.out.println("The number of comparisons needed to find the entry in the AVL Tree: " + avl.getComparisons(place));
            // Return zips for place
            System.out.println(place.getZips());
        }
    }

    /**
     * A helper method to create a list of 500 random numbers from our ArrayList of towns
     */
    private void collectRandoms() {
        int min = 0;
        int max = towns.size() - 1;
        // Min is zero, max is the amount of towns we have
        int randomNumber = (int) ((Math.random() * ((max - min) + 1)) + min);
        // Create a random number set so we are always generating random values in the tree
        numberSet = new ArrayList<>();
        // Create random numbers to access data with
        for (int i = 0; i <= 499; i++) {
            // If our numberSet contains the random number, we are generating a new one
            while (numberSet.contains(randomNumber)) {
                if (numberSet.contains(randomNumber)) {
                    randomNumber = (int) ((Math.random() * ((max - min) + 1)) + min);
                }
                // If our numberSet doesn't contain the random number, we are adding it to the numberSet
                else {
                    numberSet.add(randomNumber);
                }
            }
            numberSet.add(randomNumber);
        }
    }

    /**
     * A method that collects our data for the statistical analysis portion of the project.
     * Will output data to a file called data.txt
     */
    void dataCollection() throws FileNotFoundException {
        // Create our Array of random numbers to access certain data
        collectRandoms();
        // Create printstream
        PrintStream o = new PrintStream(new File("data.txt"));
        PrintStream console = System.out;
        // Set output to out
        System.setOut(o);

        int stComparisons = 0;
        int avlComparisons = 0;
        int bstComparisons = 0;

        double averageAVLDistance = 0;
        double averageBSTDistance = 0;
        double averageSTDistance = 0;

        int amountOfSearches = 0;

        int stAverage = 19;
        int avlAverage = 14;
        int bstAverage = 22;
        int overallComparisonsST = 0;
        int overallComparisonsBST = 0;
        int overallComparisonsAVL = 0;
        for (int i = 0; i<= numberSet.size() - 1; i++) {
            // Iterate the amount of comparisons we have done
            amountOfSearches++;
            // Grab the first random number from the set and create a place with it
            Place place = towns.get(numberSet.get(i));
            // Adding our overall number of comparisons for each tree

            averageAVLDistance = averageAVLDistance + ((Math.abs(avlComparisons-avlAverage))*(Math.abs(avlComparisons-avlAverage)));
            averageSTDistance = averageSTDistance + ((Math.abs(stComparisons-stAverage))*(Math.abs(stComparisons-stAverage)));
            averageBSTDistance = averageBSTDistance + ((Math.abs(bstComparisons-bstAverage))*(Math.abs(bstComparisons-bstAverage)));

            stComparisons = st.getComparisons(place);
            avlComparisons = avl.getComparisons(place);
            bstComparisons = bst.getComparisons(place);
            overallComparisonsST = overallComparisonsST + stComparisons;
            overallComparisonsBST = overallComparisonsBST + bstComparisons;
            overallComparisonsAVL = overallComparisonsAVL + avlComparisons;
        }
        double avlAvgDist = Math.sqrt(averageAVLDistance / (amountOfSearches-1));
        double stAvgDist = Math.sqrt(averageSTDistance / (amountOfSearches-1));
        double bstAvgDist = Math.sqrt(averageBSTDistance / (amountOfSearches-1));

        // Find the average number of comparisons
        double averageST = overallComparisonsST / amountOfSearches;
        double averageAVL = overallComparisonsAVL / amountOfSearches;
        double averageBST = overallComparisonsBST / amountOfSearches;
        // Output needed data
        System.out.println("The overall amount of comparisons for the Splay tree was " + overallComparisonsST);
        System.out.println("The overall amount of comparisons for the Binary Search tree was " + overallComparisonsBST);
        System.out.println("The overall amount of comparisons for the AVL tree was " + overallComparisonsAVL);
        System.out.println();
        System.out.println("The amount of searches done in general were: " + amountOfSearches);
        System.out.println();
        System.out.println("The average amount of the searches for the Splay Tree was " + averageST + " searches.");
        System.out.println("The average amount of the searches for the AVL Tree was " + averageAVL + " searches.");
        System.out.println("The average amount of the searches for the Binary Search Tree was " + averageBST + " searches.");
        System.out.println();
        System.out.println("The standard deviation for the AVL tree is : " + avlAvgDist);
        System.out.println("The standard deviation for the BST is : " + bstAvgDist);
        System.out.println("The standard deviation for the Splay Tree is : " + stAvgDist);
    }

    /**
     * A method to return the heights of each tree
     */
    public void heightReturns() {
        System.out.println("The height of the Binary Search Tree is: " + bst.getHeight());
        System.out.println("The height of the AVL Tree is: " + avl.getHeight());
        System.out.println("The height of the Splay Tree is: " + st.getHeight());
    }



}
