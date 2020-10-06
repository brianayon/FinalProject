/*
 * @author	Brian Ayon
 */
package cse360assignment02;

/**
 * Create class AddingMachine
 */
public class AddingMachine {
    private int total;
    private String totalString;

    /**
     * Class constructor for AddingMachine
     */
    public AddingMachine () {
        total = 0;  // not needed - included for clarity
        totalString = "";
    }


    /**
     * Method to return total
     * @return total
     */
    public int getTotal () {
        return total;
    }

    /**
     * Method to add
     * @param value
     */
    public void add (int value) {
    	total = total + value;
    	totalString += " + " + value;
    }

    /**
     * Method to subtract
     * @param value
     */
    public void subtract (int value) {
    	total = total - value;
    	totalString += " - " + value;
    }

    /**
     * Method to return string
     * @return totalString
     */
    public String toString () {
        return totalString;
    }

    /**
     * Method to clear
     */
    public void clear() {
    	total = 0;
    	totalString = "";
    }
}