/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import javax.swing.*;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * Represents a company with name, description, country, and financial statistics.
 */
public class Company {

    // Logger.
    private static final Logger logger = Logger.getLogger(Company.class.getName());

    // Next Company in CompanyList.
    private Company next = null;

    // Company File.
    private File file;

    // File path to the Company.
    private final String filePath;

    // Name of the Company file.
    private final String fileName;

    // Name of the Company.
    private String name;

    // Description of the Company.
    private String description;

    // Country of the Company.
    private String country;

    // ArrayList of Statistic, revenues.
    public ArrayList<Statistic> revenues;

    // ArrayList of Statistic, costs.
    public ArrayList<Statistic> costs;

    /**
     * Creates a new Company object with the given file path and file name.
     *
     * @param filePath The file path of the company file.
     * @param fileName The file name of the company file.
     */
    public Company(String filePath, String fileName) {

        logger.info("Creating Company. | FilePath: " + filePath + " – FileName: " + fileName);

        this.filePath = filePath;
        this.fileName = fileName;
        this.revenues = new ArrayList<>();
        this.costs = new ArrayList<>();

        readCompanyDetails();

    }

    /**
     * Reads the company details from a file and updates the corresponding fields of the Company object.
     */
    private void readCompanyDetails() {

        try (BufferedReader bufferedReader =  new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) { processLine(line); }

        } catch (IOException ioException) {

            // Output error message.
            logger.severe(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Exception with input.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {

            // Output warning message.
            logger.warning(arrayIndexOutOfBoundsException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Please refrain from using blank space between lines of information in Company file.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE

            );

        }

    }

    /**
     * Processes a line of input and updates the corresponding fields of the Company object.
     *
     * @param line The line of input to be processed.
     */
    private void processLine(String line) throws ArrayIndexOutOfBoundsException {

        String[] currentLine = line.split(",");
        logger.info(Arrays.toString(currentLine));

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

            case "END OF DETAILS":
                return;

            default:
                break;

        }

    }

    /**
     * Clears the revenues and costs lists and reads the statistic data from a file.
     */
    public void readStatistics() {

        revenues.clear();
        costs.clear();
        readStatisticData();

    }

    /**
     * Reads the statistic data from a file.
     */
    private void readStatisticData() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath))) {

            processStatistics(bufferedReader);

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
     * Processes the statistics from the input file.
     *
     * @param bufferedReader The BufferedReader used to read the input file.
     */
    private void processStatistics(BufferedReader bufferedReader) {

        try {

            boolean found = locateStatistics(bufferedReader);

            if (!found) {

                logger.warning("Null line, might cause issues.");
                return;

            }

            found = processStatisticLines(bufferedReader);

            if (!found) {

                logger.warning("Could not find end of statistics.");

            }

        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {

            // Output error message.
            logger.severe(arrayIndexOutOfBoundsException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Issue with file, remove unnecessary spaces..",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }


    }

    /**
     * Locates the statistics line in the input file.
     *
     * @param bufferedReader The BufferedReader used to read the input file.
     * @return true if the statistics line is found, false otherwise.
     */
    private boolean locateStatistics(BufferedReader bufferedReader) throws ArrayIndexOutOfBoundsException {

        boolean found = false;
        try {

            String line;
            while (!found && (line = bufferedReader.readLine()) != null) {

                String[] currentLine = line.split(",");
                if (currentLine[0].equals("STATISTICS")) {

                    found = true;

                }

            }

        } catch (IOException ioException) {

            // Output error message.
            logger.severe(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Exception with input.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }
        return found;
    }

    /**
     * Processes the statistic lines from the input file.
     *
     * @param bufferedReader The BufferedReader used to read the input file.
     * @return True if the line "END OF STATISTICS" is found, false otherwise.
     */
    private boolean processStatisticLines(BufferedReader bufferedReader) {

        boolean found = false;
        try {

            String line;

            while (!found && (line = bufferedReader.readLine()) != null) {

                found = processStatisticLine(line);

            }

        } catch (IOException ioException) {

            // Output error message.
            logger.severe(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Exception with input.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }
        return found;

    }

    /**
     * Processes a statistic line from the input and updates the corresponding fields of the Company object.
     *
     * @param line The line of input to be processed.
     * @return True if the line is "END OF STATISTICS", false otherwise.
     */
    private boolean processStatisticLine(String line) {

        String[] currentLine = line.split(",");

        if (currentLine[0].equals("END OF STATISTICS")) {

            return true;

        }

        addStatistic(currentLine);

        return false;

    }

    /**
     * Adds a statistic to the company's revenues or costs, based on the first element of the given currentLine array.
     *
     * @param currentLine The current line of input to be processed.
     */
    private void addStatistic(String[] currentLine) {

        if (currentLine[0].equalsIgnoreCase("REVENUE")) {

            revenues.add(new Statistic(currentLine[1], this.filePath));

        } else if (currentLine[0].equalsIgnoreCase("COST")) {

            costs.add(new Statistic(currentLine[1], this.filePath));

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

        readStatistics(); // Re-read Statistics in-case they have not been read before.

        int mostRecentYear = getMostRecentYear();

        ArrayList<Statistic> newRevenues = new ArrayList<>();
        ArrayList<Statistic> newCosts = new ArrayList<>();
        for (Statistic statistic : revenues) {

            if (statistic.getLatestCompleteYear() == mostRecentYear) {

                newRevenues.add(statistic);

            }

        }
        for (Statistic statistic : costs) {

            if (statistic.getLatestCompleteYear() == mostRecentYear) {

                newCosts.add(statistic);

            }

        }

        // Read and extrapolate data once.
        ArrayList<Data> allRevenueData = extrapolateAllData(newRevenues, monthsToExtrapolate);
        ArrayList<Data> allCostData = extrapolateAllData(newCosts, monthsToExtrapolate);

        // Calculate the valuation only for the most recent complete year
        double revenueSum = sumDataValues(allRevenueData);
        double costSum = sumDataValues(allCostData);

        double netProfit = revenueSum - costSum;
        int profitMultiplier = 5; // Adjust this multiplier as necessary.

        // Return the final prediction for the company's value.
        return (int) (netProfit * profitMultiplier);

    }

    /**
     * Retrieves the most recent year from the revenues and costs statistics.
     *
     * @return The most recent year as an integer.
     */
    private int getMostRecentYear() {

        logger.info("Getting most recent year.");

        ArrayList<Integer> revenuesLatestCompleteYears = new ArrayList<>();
        ArrayList<Integer> costsLatestCompleteYears = new ArrayList<>();

        // Loop through the Statistic ArrayLists for revenues and costs and get latest complete years.
        int latestCompleteYear;
        for (Statistic statistic : revenues) {

            latestCompleteYear = statistic.getLatestCompleteYear();
            logger.info(statistic.getName() + " " + latestCompleteYear);

            revenuesLatestCompleteYears.add(latestCompleteYear);

        }
        for (Statistic statistic : costs) {

            latestCompleteYear = statistic.getLatestCompleteYear();
            logger.info(statistic.getName() + " " + latestCompleteYear);

            costsLatestCompleteYears.add(latestCompleteYear);

        }

        // Add together the year ArrayLists.
        ArrayList<Integer> combinedRevenuesCostsLatestCompleteYears = new ArrayList<>();
        combinedRevenuesCostsLatestCompleteYears.addAll(revenuesLatestCompleteYears);
        combinedRevenuesCostsLatestCompleteYears.addAll(costsLatestCompleteYears);

        int mostRecentYear = Collections.max(combinedRevenuesCostsLatestCompleteYears);
        logger.info("Most recent year: " + mostRecentYear);
        return mostRecentYear;

    }

    /**
     * Extrapolates all data from the given list of statistics for the specified number of years.
     *
     * @param statistics The list of statistics to extrapolate.
     * @param yearsToExtrapolate The number of years to extrapolate the data for.
     *
     * @return The combined list of extrapolated data.
     */
    private ArrayList<Data> extrapolateAllData(ArrayList<Statistic> statistics, int yearsToExtrapolate) {

        logger.info("Extrapolating all data.");

        ArrayList<Data> combinedData = new ArrayList<>();

        for (Statistic statistic : statistics) {

            logger.info("Statistic: " + statistic.getData());
            logger.info("Data before: " + statistic.getData());

            ArrayList<Data> extrapolatedData = statistic.extrapolateData(yearsToExtrapolate);
            combinedData.addAll(extrapolatedData);

            logger.info("Data after: " + extrapolatedData);
            logger.info("Combined data size: " + combinedData.size());

        }

        return combinedData;

    }

    /**
     * Calculates the sum of the values of the Data objects in the given ArrayList for a specific year.
     *
     * @param dataList The ArrayList of Data objects.
     *
     * @return The sum of the values.
     */
    private double sumDataValues(ArrayList<Data> dataList) {

        logger.info("Summing up Data values.");

        double sum = 0;

        for (Data data : dataList) {

            sum += data.getValue();

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