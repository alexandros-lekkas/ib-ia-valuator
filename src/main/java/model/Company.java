/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Represents a company with its details and statistics.
 */
public class Company {

    // Logger.
    private static final Logger logger = Logger.getLogger(Company.class.getName());

    // Next Company in the CompanyList linked list.
    private Company next = null;

    // File for the Company.
    private File file;

    // The file path of the Company.
    private final String filePath;

    // The name of the Company file.
    private final String fileName;

    // The name of the Company.
    private String name;

    // Description of the Company.
    private String description;

    // Country of the Company.
    private String country;

    // ArrayList of Statistic objects representing the revenue streams for the Company.
    public ArrayList<Statistic> revenues;

    // ArrayList of Statistic objects representing the costs for the Company.
    public ArrayList<Statistic> costs;

    /**
     * Creates a new Company object by reading data from a file and initializing its fields.
     *
     * @param filePath The path to the file containing company details.
     * @param fileName The name of the file containing company details.
     */
    public Company(String filePath, String fileName) {

        logger.info("Creating Company. | FilePath: " + filePath + " – FileName: " + fileName);

        this.filePath = filePath;
        this.fileName = fileName;
        this.revenues = new ArrayList<>(); // Initialize the revenues list.
        this.costs = new ArrayList<>(); // Initialize the costs list.

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            logger.info("Created new FileReader and BufferedReader.");

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] currentLine = line.split(",");
                logger.info(Arrays.toString(currentLine));

                // Use a switch statement to store the specific data of the line being currently read.
                switch (currentLine[0]) {

                    case "Name":

                        this.name = currentLine[1];
                        break;

                    case "Description":

                        this.description = currentLine[1];
                        break;

                    case "Country":

                        this.country = currentLine[1];
                        break;

                    // If the END OF DETAILS is found stop the loop.
                    case "END OF DETAILS":

                        return;

                    default:

                        break;

                }

            }
            
        } catch (IOException ioException) {

            // Send error message.
            logger.severe(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Exception with input.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    /**
     * Reads the statistics from a file and populates the revenues and costs ArrayLists with Statistic objects.
     */
    public void readStatistics() {

        // Reset the existing statistics.
        revenues.clear();
        costs.clear();

        // Create new FileReader and BufferedReader.
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {

            fileReader = new FileReader(this.filePath);
            bufferedReader = new BufferedReader(fileReader);

            boolean found;

            // Find where the statistics begin in the file.
            found = false;
            while (!found) {

                try {

                    String line = bufferedReader.readLine();

                    if (line != null) {

                        String[] currentLine = line.split(",");

                        // Check if we find the statistics part.
                        if (currentLine[0].equals("STATISTICS")) { found = true; }

                    } else {

                        logger.warning("Null line, might cause issues.");

                    }

                } catch (IOException ioException) {

                    System.out.println(ioException.getMessage());

                }


            }

            // Find where the statistics begin in the file.
            found = false;
            while (!found) {

                try {

                    String line = bufferedReader.readLine();

                    if (line != null) {

                        String[] currentLine = line.split(",");

                        // Check if the currentLine[0] is equal to "END OF STATISTICS".
                        if (currentLine[0].equals("END OF STATISTICS")) { // If end of statistics...

                            found = true;

                        } else { // If not end of statistics...

                            // Check if the statistic is a revenue or cost.
                            if (currentLine[0].equalsIgnoreCase("REVENUE")) {

                                revenues.add(new Statistic(currentLine[1], this.filePath));

                            } else if (currentLine[0].equalsIgnoreCase("COST")) {

                                costs.add(new Statistic(currentLine[1], this.filePath));

                            }

                        }

                    }

                } catch (IOException ioException) {

                    System.out.println(ioException.getMessage());

                }

            }

        } catch (FileNotFoundException fileNotFoundException) {

            // Output an error message.
            System.out.println(fileNotFoundException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Company file not found.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return;

        }

    }

    /**
     * Calculates the value of a company with extrapolation if specified.
     *
     * @param monthsToExtrapolate How many months to extrapolate the data for.
     *
     * @return The valuation of the company.
     */
    public int calculateFinalValue(int monthsToExtrapolate) {

        // Read and extrapolate data once.
        ArrayList<Data> allRevenueData = extrapolateAllData(revenues, monthsToExtrapolate);
        ArrayList<Data> allCostData = extrapolateAllData(costs, monthsToExtrapolate);

        // Find the most recent complete year
        int mostRecentCompleteYear = findMostRecentCompleteYear(allRevenueData);

        // Calculate the valuation only for the most recent complete year
        double revenueSum = sumDataForYear(allRevenueData, mostRecentCompleteYear);
        double costSum = sumDataForYear(allCostData, mostRecentCompleteYear);

        double netProfit = revenueSum - costSum;
        int profitMultiplier = 5; // Adjust this multiplier as necessary.

        // Return the final prediction for the company's value.
        return (int) (netProfit * profitMultiplier);

    }

    /**
     * Finds the most recent complete year from the given list of data points.
     *
     * @param data The list of data points.
     *
     * @return The most recent complete year.
     */
    private int findMostRecentCompleteYear(ArrayList<Data> data) {

        int currentYear = Integer.MIN_VALUE;
        int[] monthCount = new int[12];

        for (Data dataPoint : data) {

            if (dataPoint.getYear() > currentYear) {

                // Check if the previous year was complete
                if (isYearComplete(monthCount)) {

                    return currentYear; // Return the most recent complete year

                }

                // Reset for the new year
                currentYear = dataPoint.getYear();
                monthCount = new int[12];

            }
            monthCount[dataPoint.getMonth() - 1]++;

        }

        // Check for the last year in the dataset
        if (isYearComplete(monthCount)) {
            return currentYear;
        }

        return currentYear;

    }

    /**
     * Checks if a year is complete based on the count of data points for each month.
     * A year is considered complete if there is at least one data point for each month.
     *
     * @param monthCount An array of counts for each month. The index represents the month, and the value represents the count of data points for that month.
     *                   The array should have a length of 12.
     *
     * @return True if the year is complete, false otherwise.
     */
    private boolean isYearComplete(int[] monthCount) {

        for (int count : monthCount) {

            if (count == 0) {

                return false; // Year is incomplete if any month has a count of 0.

            }

        }

        return true; // Year is complete if all months have data.

    }

    /**
     * Extrapolates the data from the given list of statistics for the specified number of months.
     *
     * @param statistics The list of statistics to extrapolate the data from.
     * @param monthsToExtrapolate The number of months to extrapolate the data for.
     *
     * @return An ArrayList of Data objects containing the extrapolated data.
     */
    private ArrayList<Data> extrapolateAllData(ArrayList<Statistic> statistics, int monthsToExtrapolate) {

        ArrayList<Data> allData = new ArrayList<>();
        for (Statistic statistic : statistics) {

            allData.addAll(statistic.extrapolateData(monthsToExtrapolate));

        }

        return allData;

    }

    /**
     * Calculates the sum of data values for a specific year.
     *
     * @param dataList An ArrayList of Data objects containing the data points.
     * @param year The year for which to calculate the sum.
     * @return The sum of data values for the specified year.
     */
    private double sumDataForYear(ArrayList<Data> dataList, int year) {

        double sum = 0;
        for (Data data : dataList) {

            if (data.getYear() == year) {

                sum += data.getValue();

            }

        }

        return sum;

    }

    /**
     * Get next company (linked list method).
     *
     * @return The next company in the linked list.
     */
    public Company getNext() { return next; }

    /**
     * Set the next company (linked list method).
     *
     * @param next The company to which to set the next company of the current
     * one.
     */
    public void setNext(Company next) { this.next = next; }

    /**
     * Gets the name of the file from which the Company object was initialized.
     *
     * @return The name of the file as a String.
     */
    public String getFileName() { return fileName; }

    /**
     * Retrieves the file associated with the Company object.
     *
     * @return The file associated with the Company object.
     */
    public File getFile() { return file; }

    /**
     * Sets the file associated with the Company object.
     *
     * @param file The file to be set.
     */
    public void setFile(File file) { this.file = file; }

    /**
     * Gets the name of the company.
     *
     * @return Name of the company.
     */
    public String getName() { return this.name; }

    /**
     * Gets the description of the company
     *
     * @return Description of the company
     */
    public String getDescription() { return this.description; }

    /**
     * Gets the country of the company.
     *
     * @return The country of the company.
     */
    public String getCountry() { return this.country; }

    /**
     * Gets the revenues ArrayList.
     *
     * @return ArrayList of revenues.
     */
    public ArrayList<Statistic> getRevenues() { return this.revenues; }

    /**
     * Gets the costs ArrayList.
     *
     * @return ArrayList of revenues.
     */
    public ArrayList<Statistic> getCosts() { return this.costs; }

}
