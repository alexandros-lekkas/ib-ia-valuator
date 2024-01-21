package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows for the creation of a statistic that is loaded when reading the
 * company file.
 *
 * @author Alexandros Lekkas
 */
public class Statistic {

    private String name = "Name"; // Name of the statistic.
    private String filePath = null; // File path of the statistic (in which company file it is located).
    private ArrayList<Data> data = null; // ArrayList of data points.

    /**
     * Creates a unique statistic object used to track that statistic.
     *
     * @param name Name of the statistic (used as an identifier when searching
     * through the files to find.
     * @param filePath The file path of the company from which the statistic
     * belongs to.
     */
    public Statistic(String name, String filePath) {

        this.name = name;
        this.filePath = filePath;
        this.data = new ArrayList<>(); // Initialize the data ArrayList.

    }

    /**
     * Statistic constructor with data existing. Used to model a statistic
     * separately from a company - such as in the case of company valuation
     *
     * @param name Name of the statistic.
     * @param data Data provided by the user that belongs to the statistic.
     */
    public Statistic(String name, ArrayList<Data> data) {

        this.name = name;
        this.data = data;

    }

    /**
     * Gets the name of the statistic.
     *
     * @return The name of the statistic.
     */
    public String getName() {

        return name;

    }

    /**
     * Read the data related to the specific statistic and store it in the
     * ArrayList of Data objects.
     */
    public void readData() {

        FileReader fileReader;
        try {

            fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Find where the statistic is in the file.
            boolean found = false;
            while (!found) { // Loop through the file and only stop when statistic is found.

                String[] currentLine = bufferedReader.readLine().split(","); // Save current line to an array.

                try {

                    if (currentLine[1].toUpperCase().equals(name.toUpperCase())) {

                        String[] nextLine = bufferedReader.readLine().split(",");

                        int i = 3; // First year should be defined at index position 3.
                        while (!currentLine[i].equals("")) { // Loop through to each year that is part of the statistic.

                            int year = Integer.parseInt(currentLine[i]); // Get the year that belongs to this year of data.
                            int j = 4; // The next index.
                            try {
                                
                                while (Integer.parseInt(currentLine[j]) <= 12) { // Loop through the years data from months creating data.

                                    System.out.println(Integer.parseInt(nextLine[j]));
                                    data.add(new Data(year, Integer.parseInt(currentLine[j]), Integer.parseInt(nextLine[j])));
                                    System.out.println(toString());
                                    j++;

                                }
                                
                            } catch (NumberFormatException ex) {

                                System.out.println("[!] Issue with number formatting. Skipping.");
                            }

                            i = i + 14; // Skip to the next year.

                        }

                        found = true;

                    }

                } catch (ArrayIndexOutOfBoundsException ex) {

                    // Do nothing, as this just means that the line is blank so we just ignore it and ignore the error.
                }

            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NullPointerException ex) {

            // Does not matter because line may be blank.
        }

    }

    /**
     * Returns the data as an ArrayList of Data objects.
     *
     * @return The ArrayList of data objects,
     */
    public ArrayList<model.Data> getData() {

        return data;

    }

    /**
     * Outputs information about the statistic as a string.
     *
     * @return Statistic represented as a string.
     */
    @Override
    public String toString() {

        String dataString = "Data: ";

        // Loop through the data in the statistic and output it.
        for (Data dataPoint : getData()) {

            dataString = dataString + " " + dataPoint.getValue() + ",";

        }

        return "Name: " + this.name + " - File path: " + this.filePath + " - " + dataString;

    }

    /**
     * Extrapolates the data for a given number of months.
     *
     * @param monthsToExtrapolate The number of months to extrapolate.
     * @return An updated ArrayList with the extrapolated data.
     */
    public ArrayList<Data> extrapolateData(int monthsToExtrapolate) {

        readData();

        if (getData().isEmpty()) {

            throw new IllegalArgumentException("Data list is empty.");

        }

        ArrayList<Data> extrapolatedData = new ArrayList<>(data);

        // If no extrapolation is needed, return the current data.
        if (monthsToExtrapolate == 0) {

            return extrapolatedData;

        }

        Data lastDataPoint = extrapolatedData.get(extrapolatedData.size() - 1);
        int mostRecentYear = lastDataPoint.getYear();

        // Filter data for the most recent year.
        ArrayList<Data> recentYearData = new ArrayList<>();

        for (Data extrapolatedDataPoint : extrapolatedData) {

            if (extrapolatedDataPoint.getYear() == mostRecentYear) {

                recentYearData.add(extrapolatedDataPoint);

            }

        }

        // Calculate moving average.
        float movingAverage = 0;
        for (Data d : recentYearData) {

            movingAverage += d.getValue();

        }
        movingAverage /= recentYearData.size();

        // Extrapolate data using moving average.
        int lastMonth = lastDataPoint.getMonth();
        float lastValue = (float) lastDataPoint.getValue();
        for (int i = 1; i <= monthsToExtrapolate; i++) {

            int extrapolatedMonth = lastMonth + i;
            int extrapolatedYear = mostRecentYear;

            while (extrapolatedMonth > 12) {

                extrapolatedMonth -= 12;
                extrapolatedYear++;

            }

            float extrapolatedValue = lastValue + (movingAverage * i);
            extrapolatedData.add(new Data(extrapolatedYear, extrapolatedMonth, (int) extrapolatedValue));

        }

        return extrapolatedData;

    }

}
