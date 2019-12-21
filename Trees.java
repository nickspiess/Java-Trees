import java.util.*;
import java.io.*;

public class Trees {

    /**
     * File name: Trees.java
     * @author SPIESSNA
     * description: The purpose of this driver is to read through an input file of 40,000 plus zip codes, add them
     * to a SplayTree, AVL tree and a Binary Search Tree, then to create a user input program that will ask a user to
     * search for a certain location, search for it and inform the user if it exists or not, and how many comparisons
     * for each tree it took to figure that out.
     * It also calls on methods in our processor for the statistical analysis portion of the project
     * Version: 5
     * @since 11/25/19
     */

    /**
     * Our main method to create our processor, pass our file to create our trees, initiate userSearch, and to initiate
     * the data collection portion
     * @param args - Any command line arguments
     * @throws - FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException{
        // Create Processor
        Processor processor = new Processor();
        // Hard coded file
        linePass("zips.txt", processor);
        userSearch(processor);
        dataCollection(processor);
    }

    /**
     * A method that will pass each line to our fileReader to process and add the data.  Will call method to find
     * the heights of each tree at the end.
     * @param file - The file we are reading from
     * @param processor - Our processor that we are using to read, collect and add data
     * @throws - FileNotFoundException
     */
    public static void linePass(String file, Processor processor) throws FileNotFoundException {
        // Create scanner to read through file
        Scanner inputFile = new Scanner(new File(file));
        String line = inputFile.nextLine();// Grab line : Fencepost issue, grab first line
        while (inputFile.hasNextLine()) { // While file contains other lines
            System.out.println("Compiling Tree Data...");
            while (inputFile.hasNext()) { // While line contains tokens
                line = inputFile.nextLine(); // Grab line
                fileReader(line, processor); // Pass line to be read
            }
        }
        // Once we are done reading through the file and inputting our data, we will return the heights of each tree
        processor.heightReturns();
    }

    /**
     * A method that takes a line and reads through it, sending the data to our processor
     * @param line - The line in the file we are reading
     * @param processor - The instance of the processor that we are using to collect, add and read data.
     * @throws - FileNotFoundException
     */
    public static void fileReader(String line, Processor processor) {
        // Initiate our variables
        String zipCode;
        String town;
        String state = "";
        String townAndState;
        double rand1;
        double rand2;
        // Read through the line
        Scanner lineReader = new Scanner(line);
        int frequency = new StringTokenizer(line, " ").countTokens();
        while(lineReader.hasNext()) { // While there are tokens left in the line
            // If blocks to check for length of city name
            // Note: Could've been more efficient, I know
            zipCode = lineReader.next();
            rand1 = lineReader.nextDouble(); // Don't need this
            rand2 = lineReader.nextDouble(); // Don't need this
            town = lineReader.next(); // Grab city name
            // City/town is one word
            if (frequency == 2) {
                state = lineReader.next();
            }
            // City is two words
            else if (frequency == 3){
                String town2 = lineReader.next();
                state = lineReader.next();
                town = town + " " + town2;
            }
            // City is three words
            else if (frequency == 4) {
                String town2 = lineReader.next();
                String town3 = lineReader.next();
                state = lineReader.next();
                town = town + " " + town2 + " " + town3 + " ";
            }
            // City is four words
            else if (frequency == 5){
                String town2 = lineReader.next();
                String town3 = lineReader.next();
                String town4 = lineReader.next();
                state = lineReader.next();
                town = town + " " + town2 + " " + town3 + " " + town4;
            }
            // City is five words
            // Yes, very redundant, I know.  Should've made a method to check for comma at end of string in town.
            else if (frequency == 6) {
                String town2 = lineReader.next();
                String town3 = lineReader.next();
                String town4 = lineReader.next();
                String town5 = lineReader.next();
                town = town + town2 + town3 + town4 + town4 + town5;
                state = lineReader.next();
            }
            // We rely on our processor when creating the place to check if the place exists or not
            // If the place exists, we are just adding the zip code
            // If it does not, we are creating the instance
            processor.createPlace(zipCode, town, state);
        }
    }

    /**
     * A method to initiate the user search so that the user can search for cities.  The user will be informed if the
     * city exists and regardless, will be informed of how many comparisons it took to figure that out.
     * @param processor - The processor we are using to collect, add and read data
     */
    public static void userSearch(Processor processor) {
        int game = 0;
        String response = "Yes";
        String location = "";
        while (response.equalsIgnoreCase("yes")) {
            Scanner scan = new Scanner(System.in);
            System.out.print("You want to search for a city: ");
            // Grab city
            location = scan.nextLine();
            // Create empty town string
            String town = "";
            // Create an array of tokens that splits where there is a space to get the town and state
            String[] tokens = location.split(" ");
            // Grabbing the town
            for (int i = 0; i <= tokens.length - 2; i++) {
                town = town + tokens[i] + " ";
            }
            // Last token is the state
            String state = tokens[tokens.length - 1];
            // Create place
            Place place = new Place(town, state);
            // Search for the place
            processor.searchForNode(place);
            System.out.print("Do you want me to search again? ");
            response = scan.next();
            System.out.println();
        }
        System.out.println("Good bye!");
        System.out.println();
    }

    /**
     * A helper method to initiate our data collection
     * @param processor - The instance of the processor that we are using to collect, read and add data
     * @throws - FileNotFoundException
     */
    public static void dataCollection(Processor processor) throws FileNotFoundException {
        processor.dataCollection();
        processor.dataCollection();
        processor.dataCollection();
    }

}
