package model;

/**
 * Data class to hold data (which includes time frame).
 *
 * @author Alexandros Lekkas
 */
public class Data {

    private final int year; // Year of the recorded data.
    private final int month; // Month of the recorded data.
    private final int value; // Value of the recorded data.

    /**
     * Constructor for creating a data object used to represent a single piece
     * of data.
     *
     * @param year The year of the recorded data.
     * @param month The month of the recorded data.
     * @param value The value of the recorded data.
     */
    public Data(int year, int month, int value) {

        this.year = year;
        this.month = month;
        this.value = value;

    }

    /**
     * Returns the year the data was recorded.
     *
     * @return The recorded year of the data.
     */
    public int getYear() {

        return this.year;

    }

    /**
     * Gets the current month of the data.
     *
     * @return The current month of the data.
     */
    public int getMonth() {

        return this.month;

    }

    /**
     * Return the value of the current data point.
     *
     * @return The current value of the data.
     */
    public int getValue() {

        return this.value;

    }
    
    /**
     * Create a string to represent the data in the data object.
     * 
     * @return The data object as a string.
     */
    @Override
    public String toString() {
        
        return "Value: " + getValue() + " - Month: " + getMonth() + " - Year: " + getYear(); 
        
    }

}
