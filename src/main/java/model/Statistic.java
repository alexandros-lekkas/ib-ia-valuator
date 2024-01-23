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

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Data> data;

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
     * Reads data from a file and populates the Data list of the Statistic object.
     */
    public void readData() {

        logger.info("Reading Data for Statistic " + this.name + ".");

        try {

            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            findStatisticInFile(bufferedReader);

        } catch (IOException ioException) {

            // Output error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Error with IO. File potentially not found.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    /**
     * Finds the statistic in the given file using the provided BufferedReader.
     *
     * @param bufferedReader The BufferedReader object to read the file.
     * @throws IOException If an I/O error occurs.
     */
    private void findStatisticInFile(BufferedReader bufferedReader) throws IOException {

        boolean found = false;

        while (!found) {

            String[] currentLine = bufferedReader.readLine().split(",");
            logger.info("Current line: " + Arrays.toString(currentLine) + ".");

            try {

                if (currentLine.length > 1 && currentLine[1].equalsIgnoreCase(name)) {

                    String[] nextLine = bufferedReader.readLine().split(",");
                    processDataLines(currentLine, nextLine);
                    found = true;

                }

            } catch (ArrayIndexOutOfBoundsException exception) {

                logger.warning("Array index is out of bounds, skipping over.\n" + exception.getMessage());
            }

        }

    }

    /**
     *
     * Processes the data lines for a given statistic.
     *
     * @param currentLine The current line of data.
     * @param nextLine The next line of data.
     */
    private void processDataLines(String[] currentLine, String[] nextLine) {

        int i = 3;

        while (i < currentLine.length && !currentLine[i].isEmpty()) {

            int year = Integer.parseInt(currentLine[i]);
            int j = 4;

            processYearData(currentLine, nextLine, i, j, year);
            i += 14;

        }

    }

    /**
     * Processes the year data for a given statistic, adding the data to the internal data list.
     *
     * @param currentLine The current line of data.
     * @param nextLine The next line of data.
     * @param i The current index in the currentLine array.
     * @param j The current index in the nextLine array.
     * @param year The year of the data.
     */
    private void processYearData(String[] currentLine, String[] nextLine, int i, int j, int year) {

        try {

            while (j < currentLine.length && Integer.parseInt(currentLine[j]) <= 12) {

                int value = (int) Double.parseDouble(nextLine[j]);
                data.add(new Data(year, Integer.parseInt(currentLine[j]), value));
                logger.info(data.toString());
                j++;

            }

        } catch (NumberFormatException exception) {

            logger.warning("Number format issue. Skipping over value.\n" + exception.getMessage());

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

        if (getData().isEmpty()) { return null; }

        ArrayList<Data> extrapolatedData = new ArrayList<>(data);

        // If no extrapolation is needed, return the current data.
        if (monthsToExtrapolate == 0) { return extrapolatedData; }

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

        // Output message if Data was actually extrapolated.
        logger.info("Data has " + this.data.toArray().length + " records.");
        logger.info("Data has " + extrapolatedData.toArray().length + " records.");
        if (!extrapolatedData.equals(this.data)) {

            logger.info("Data extrapolated properly.");

        } else {

            logger.warning("Data not extrapolated properly.");

        }

        return extrapolatedData;

    }

    /**
     * Returns the latest complete year for the data. A complete year is defined as having at least 12 data points. If
     * the data does not have a complete year, -1 is returned.
     *
     * @return The latest complete year for the data, or -1 if it is incomplete.
     */
    public int getLatestCompleteYear() {

        readData();

        logger.info("Getting latest complete year for " + name);

        // Check if the ArrayList of Data is long enough for minimum one year.
        if (data.size() >= 12) {

            logger.info("Size of data is over 12.");

            int lastYear = data.get(data.size() - 1).getYear();

            ArrayList<Data> dataLastYear = new ArrayList<>(data.subList(data.size() - 12, data.size()));

            for (Data dataPoint : dataLastYear) {

                if (dataPoint.getYear() != lastYear) {

                    logger.warning(name + " is not a full year.");
                    return -1;

                }

            }

            return lastYear;

        }

        return -1;  // Return -1 if there are less than 12 datapoints

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
     * Gets the name of the statistic.
     *
     * @return The name of the statistic.
     */
    public String getName() { return name; }

    /**
     * Returns the data as an ArrayList of Data objects.
     *
     * @return The ArrayList of data objects.
     */
    public ArrayList<model.Data> getData() { return data; }

}
