/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Statistic represents a statistic with a name and corresponding data points.
 */
public class Statistic {

    // Logging.
    private static final Logger logger = Logger.getLogger(Authentication.class.getName());

    // Name of Statistic, not subject to change.
    private final String name;

    // File path to the Statistic (Company file in which it is loaded).
    private String filePath = null;

    // ArrayList of Data.
    private final ArrayList<Data> data;

    /**
     * Constructs a Statistic object with the given name and file path.
     *
     * @param name The name of the statistic.
     * @param filePath The file path to read data from.
     */
    public Statistic(String name, String filePath) {

        this.name = name;
        this.filePath = filePath;
        this.data = new ArrayList<>(); // Initialize the data ArrayList.

    }

    /**
     * Statistic constructor with data existing. Used to model a statistic separately from a company.
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
    public String getName() { return name; }

    /**
     * Reads data from a file and populates the data list of the statistic object. This method searches for the
     * statistic in the file based on the name provided. It then reads the data associated with the statistic and saves
     * it as Data objects in the data list.
     */
    public void readData() {

        try {

            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Find where the statistic is in the file.
            boolean found = false;
            while (!found) { // Loop through the file and only stop when statistic is found.

                String[] currentLine = bufferedReader.readLine().split(","); // Save current line to an array.

                try {

                    if (currentLine[1].equalsIgnoreCase(name)) {

                        String[] nextLine = bufferedReader.readLine().split(",");

                        int i = 3; // First year should be defined at index position 3.
                        while (!currentLine[i].isEmpty()) { // Loop through to each year that is part of the statistic.

                            int year = Integer.parseInt(currentLine[i]); // Get the year that belongs to this year of data.
                            int j = 4; // The next index.
                            try {
                                
                                while (Integer.parseInt(currentLine[j]) <= 12) { // Loop through the years data from months creating data.

                                    System.out.println(Integer.parseInt(nextLine[j]));
                                    data.add(new Data(year, Integer.parseInt(currentLine[j]), Integer.parseInt(nextLine[j])));
                                    System.out.println(toString());
                                    j++;

                                }
                                
                            } catch (NumberFormatException numberFormatExceptions) {

                                logger.warning(

                                        "Number format issue. Skipping over value.\n"
                                        + numberFormatExceptions.getMessage()

                                );
                                
                            }

                            i = i + 14; // Skip to the next year.

                        }

                        found = true;

                    }

                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {

                    // Log warning message.
                    logger.warning(

                            "Array index is out of bounds, skipping over.\n" +
                            arrayIndexOutOfBoundsException.getMessage()

                    );

                }

            }

        } catch (IOException ioException) {

            // Output error message.
            logger.severe(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Error when trying to register.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        } catch (NullPointerException nullPointerException) {

            // Log warning message.
            logger.warning(

                    "Null pointer, does not matter line is probably blank.\n" +
                    nullPointerException.getMessage()

            );

        }

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

    /**
     * Outputs information about the statistic as a String.
     *
     * @return Statistic represented as a String.
     */
    @Override
    public String toString() {

        StringBuilder dataString = new StringBuilder("Data: ");

        // Loop through the data in the statistic and output it.
        for (Data dataPoint : getData()) {

            dataString.append(" ").append(dataPoint.getValue()).append(",");

        }

        return "Name: " + this.name + " - File path: " + this.filePath + " - " + dataString;

    }

    /**
     * Returns the data as an ArrayList of Data objects.
     *
     * @return The ArrayList of data objects.
     */
    public ArrayList<model.Data> getData() { return data; }

}
