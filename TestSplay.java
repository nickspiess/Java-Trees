import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestSplay {

    /**
     * File name: TestSplay.java
     * @author SPIESSNA
     * description: The purpose of this program is to test our Splay Tree to verify that inputs work, deleting values
     * and clearing values work
     * Version: 5
     * @since 11/25/19
     */

    /**
     * Our main method that runs our SplayTest
     * @param args - Any command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Create printstream
        PrintStream o = new PrintStream(new File("TestSplay.txt"));
        PrintStream console = System.out;
        // Set output to out
        System.setOut(o);
        SplayTree<String> st = new SplayTree<>();
        testSplayTree(st);
        removeValuesTest(st, "Eau Claire");
        removeValuesTest(st, "Accident");
        removeValuesTest(st, "Rough and Ready");
        clearTree(st);
        System.out.println();
        System.out.println("***Inserting Values: Accident, Eau Claire, Rough and Ready***");
        reinsertValuesTest(st);
    }

    /**
     * Our method that inserts values by calling a helper method.
     */
    public static void testSplayTree(SplayTree st) {
        insertValuesTest(st, "Peculiar");
        insertValuesTest(st, "Crapo");
        insertValuesTest(st, "Accident");
        insertValuesTest(st, "Eau Claire");
        insertValuesTest(st, "Boring");
        insertValuesTest(st, "Hell");
        insertValuesTest(st, "Walla Walla");
        insertValuesTest(st, "Surprise");
        insertValuesTest(st, "Joseph");
        insertValuesTest(st, "Romance");
        insertValuesTest(st, "Mars");
        insertValuesTest(st, "Nuttsville");
        insertValuesTest(st, "Rough and Ready");
        insertValuesTest(st, "Dynamite");
        insertValuesTest(st, "Good Grief");
        insertValuesTest(st, "Zarephath");
    }


    /**
     * Our method to insert values
     * @param st - The SplayTree we are adding to
     * @param stringToInsert - The string we are inserting
     */
    public static void insertValuesTest(SplayTree st, String stringToInsert) {
        System.out.println("***Inserting " + stringToInsert + "***");
        st.insert(stringToInsert);
        System.out.println();
        st.printLevelOrder();
        System.out.println();
        System.out.println("Size: " + st.getSize());
        System.out.println("Height: " + st.getHeight());
        System.out.println();
    }

    /**
     * Our method to remove values
     * @param st - The SplayTree we are removing from
     * @param stringToRemove - The string we are removing
     */
    public static void removeValuesTest(SplayTree st, String stringToRemove) {
        System.out.println("***Removing " + stringToRemove + "***");
        System.out.println();
        st.remove(stringToRemove);
        st.printLevelOrder();
        System.out.println();
        System.out.println("Size: " + st.getSize());
        System.out.println("Height: " + st.getHeight());
        System.out.println();
    }

    /**
     * Our method to clear our values
     * @param st - The SplayTree we are clearing
     */
    public static void clearTree(SplayTree st) {
        System.out.println("***Clearing values***");
        System.out.println();
        st.clear();
        System.out.println("Size: " + st.getSize());
        System.out.println("Height: " + st.getHeight());
    }

    /**
     * Our method to reinsert values
     * @param st - The SplayTree we are adding to
     */
    public static void reinsertValuesTest(SplayTree st) {
        st.insert("Accident");
        st.insert("Eau Claire");
        st.insert("Rough and Ready");
        System.out.println();
        st.printLevelOrder();
        System.out.println();
        System.out.println("Size: " + st.getSize());
        System.out.println("Height: " + st.getHeight());
        System.out.println();
    }

}
