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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Represents a company with its details and statistics.
 */
public class Company {

    // Next Company in the CompanyList linked list.
    private Company next = null;

    // File object of Company.
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
    private ArrayList<Statistic> revenues;

    // ArrayList of Statistic objects representing the costs for the Company.
    private ArrayList<Statistic> costs;

    /**
     * Create a new company.
     *
     * @param filePath Requires the file path of the company file to get details
     * from it.
     * @param fileName The file name of the company.
     */
    public Company(String filePath, String fileName) {

        this.fileName = fileName;
        this.filePath = filePath;
        this.revenues = new ArrayList<>(); // Initialize the revenues list.
        this.costs = new ArrayList<>(); // Initialize the costs list.

        // Read through the file and load the data into the object.
        try {

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read file until "END OF DETAILS" is found.
            boolean found = false;
            while (!found) { // While the end of the details are not reached...

                try {

                    String[] currentLine = bufferedReader.readLine().split(",");

                    try {

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

                                found = true;
                                break;

                            default:

                                break;

                        }

                    } catch (ArrayIndexOutOfBoundsException error) {

                        JOptionPane.showMessageDialog(null, "Error reading details. Please check file template!", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                } catch (NullPointerException error) {

                    found = true; // Break from the loop

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(null, "Issue with file!", "Error", JOptionPane.ERROR_MESSAGE);

                    Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

            this.file = new File(this.filePath);

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "Company file was not found!", "Error", JOptionPane.ERROR_MESSAGE);

        }

        readStatistics(); // Reads the file statistics.

    }

    /**
     * Get next company (linked list method).
     *
     * @return The next company in the linked list.
     */
    public Company getNext() {

        return next;

    }

    /**
     * Set the next company (linked list method).
     *
     * @param next The company to which to set the next company of the current
     * one.
     */
    public void setNext(Company next) {

        this.next = next;

    }
    
    /**
     * Gets the file name of the company.
     * 
     * @return String of company file name, not file path.
     */
    public String getFileName() {
        
        return this.fileName;
        
    }

    /**
     * Get the file name used to create the company.
     *
     * @return The name of the file that was used when creating the company
     * object.
     */
    public String getFileName() {

        return this.file.getName();

    }

    /**
     * Gets the name of the company.
     *
     * @return Name of the company.
     */
    public String getName() {

        return this.name;

    }

    /**
     * Gets the description of the company
     *
     * @return Description of the company
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the country of the company.
     *
     * @return The country of the company.
     */
    public String getCountry() {

        return this.country;

    }

    /**
     * Saves the statistics from the file to the two ArrayLists.
     */
    public void readStatistics() {

        revenues.clear();
        costs.clear();

        boolean found;
        try {

            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Find where the statistics data begins in the file.
            found = false;
            while (!found) { // Loop through the file until statistics data is found...

                String[] currentLine = bufferedReader.readLine().split(",");

                try {

                    // Check if currentLine[0] is equal to "STATISTICS".
                    if (currentLine[0].equals("STATISTICS")) { // If beginning of statistics...

                        found = true;

                    }

                } catch (ArrayIndexOutOfBoundsException error) {

                    // Do nothing, as this just means the line is blank and cannot be read so we skip.
                }

            }

            // Find statistics and create Statistic objects.
            found = false;
            while (!found) {

                String[] currentLine = bufferedReader.readLine().split(","); // Save current line to an array.
                System.out.println(Arrays.toString(currentLine));

                try {

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

                } catch (ArrayIndexOutOfBoundsException error) {

                    // Do nothing.
                }

            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NullPointerException ex) {

            // Do nothing because this just means that the line is blank. Not an issue.
        }

    }

    /**
     * Gets the revenues ArrayList.
     *
     * @return ArrayList of revenues.
     */
    public ArrayList<Statistic> getRevenues() {

        return this.revenues;

    }

    /**
     * Gets the costs ArrayList.
     *
     * @return ArrayList of revenues.
     */
    public ArrayList<Statistic> getCosts() {

        return this.costs;

    }

    /**
     * Calculates the value of a company with extrapolation if specified.
     *
     * @param monthsToExtrapolate How many months to extrapolate the data for.
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

        throw new IllegalStateException("No complete year data available.");

    }

    /**
     * Checks if the year is complete from data provided.
     *
     * @param monthCount
     * @return
     */
    private boolean isYearComplete(int[] monthCount) {

        for (int count : monthCount) {

            if (count == 0) {

                return false; // Year is incomplete if any month has a count of 0.

            }

        }

        return true; // Year is complete if all months have data

    }

    private ArrayList<Data> extrapolateAllData(ArrayList<Statistic> statistics, int monthsToExtrapolate) {

        ArrayList<Data> allData = new ArrayList<>();
        for (Statistic statistic : statistics) {

            allData.addAll(statistic.extrapolateData(monthsToExtrapolate));

        }

        return allData;

    }

    /**
     * Sums the data for the current year.
     *
     * @param dataList
     * @param year
     * @return
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

}
