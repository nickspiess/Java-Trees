import java.util.*;

public class Place implements Comparable<Place> {

    /**
     * File name: Place.java
     * @author SPIESSNA
     * description: The program is a class for our Place, which contains our values for the place, such as zipcodes,
     * town name and state.  It also contains an ArrayList of all our zipcodes for the place.
     * Version: 2
     * @since 11/25/19
     */

    private String town;
    private String state;
    private String townAndState;
    private ArrayList<String> zipCodes;

    /**
     * The constructor for the Place
     * @param town - The town we are adding
     * @param state - The state the town exists in
     */
    public Place(String town, String state) {
        this.town = town;
        this.state = state;
        this.townAndState = town + " " + state;
        zipCodes = new ArrayList<>();
    }

    /**
     * A method to add a zip code to our array of zipcodes
     * @param zipCode - The zipCode we are adding
     */
    void addZip(String zipCode) {
        zipCodes.add(zipCode);
    }

    /**
     * A method to get our town String
     * @return town : The name of our town/city
     */
    public String getTown() {
        return town;
    }

    /**
     * A method to get our state String
     * @return state : The name of our state
     */
    public String getState() {
        return state;
    }

    /**
     * A method to get our zipcode String for the town
     * @return A string containing the zipcodes for a certain town and state
     */
    public String getZips() {
        return "The zip code(s) that belong to " + townAndState + " are: " + zipReturner();
    }

    /**
     * A method to get all our zipcodes in a place
     * @return A string of all of our zipcodes for the certain place
     */
    public String zipReturner() {
        String singleZip;
        String zips = "";
        for (int i = 0; i <= zipCodes.size() - 1; i++) {
            singleZip = zipCodes.get(i).toString();
            // If we are at the last array item, don't add the comma
            if (i == zipCodes.size() - 1) {
                zips = zips +  " " + singleZip;
            }
            // If this isn't the last item, add a comma
            else {
                zips = zips + " " + singleZip + ",";
            }
        }
        // Return our zips in a String format
        return zips;
    }


    /**
     * A method to get our town and state String
     * @return town : The name of our town/city and state
     */
    public String toString() {
        return townAndState;
    }


    /**
     * A method that overrides our compare to class so we can compare data inputs based off alphabetical order
     * @return int : The comparison value
     */
    @Override
    public int compareTo(Place place) {
        int comparison = place.toString().compareTo(townAndState);
        if (comparison > 0) {
            return -1;
        }
        else if (comparison < 0) {
            return 1;
        }
        return 0;
    }
}
