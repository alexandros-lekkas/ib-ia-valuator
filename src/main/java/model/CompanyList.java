/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import resources.Variables;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Represents a linked list of companies.
 */
public final class CompanyList {

    // File path of the User's file.
    private final String filePath;

    // The first Company in the linked list.
    private Company head;

    /**
     * Constructs a CompanyList object. This constructor takes a file path as a parameter and initializes the linked
     * list of Company objects.
     *
     * @param filePath The file path to read the company file locations from.
     */
    public CompanyList(String filePath) {

        // Initializes the linked list of Company objects.
        this.head = null;
        this.filePath = filePath;

        // Loop through the User's file.
        try {

            // Use the scanner object to read through the first line in the User file.
            Scanner scanner = new Scanner(new File(this.filePath));
            String line = scanner.nextLine();
            String[] companyFileNames = line.split(",");

            // Loop through the line, creating a new Company object for each file location written.
            for (String companyFileName : companyFileNames) {

                // Check if the file is a CSV file.
                if (companyFileName.toLowerCase().endsWith(".csv")) {

                    // Create a new Company object and add it to the linked list.
                    Company company = new Company(Variables.dataFolderPath + "/Companies/" + companyFileName, companyFileName);
                    this.add(company);

                } else {

                    // Output error message.
                    JOptionPane.showMessageDialog(

                            null,
                            "Company file " + companyFileName + " + is not a CSV file",
                            "IO Error",
                            JOptionPane.ERROR_MESSAGE

                    );

                }

            }

        } catch (IOException ioException) {

            // Output error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Issue when trying to add list of companies.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        } catch (NoSuchElementException noSuchElementException) {

            System.out.println(noSuchElementException.getMessage());

        }

    }

    /**
     * Adds a Company object to the linked list.
     *
     * @param company The Company object to be added.
     */
    public void add(Company company) {

        Company current;

        // Check if the Company is the first in the list.
        if (head == null) {

            head = company;

        } else { // If the Company is not the first Company...

            current = head; // Start from the head Company.

            // Loop through the linked list until there is no next Company.
            while (current.getNext() != null) {

                current = current.getNext();

            }

            current.setNext(company); // Set the next Company to the new one to add.

        }

    }

    /**
     * Removes a Company object from the linked list.
     *
     * @param company The Company object to be removed.
     */
    public void remove(Company company) {

        Company current = head;
        Company previous = null;

        // If the head node holds the company to be deleted.
        if ((current != null) && (current == company)) {

            head = current.getNext();
            return;

        }

        // Search for the company to be deleted.
        while ((current != null) && (current != company)) {

            previous = current;
            current = current.getNext();

        }

        // If the company was not in the linked list.
        if (current == null) {

            return;

        }

        previous.setNext(current.getNext());

    }

    /**
     * Re-writes the file with the updated linked list.
     */
    public void save() {

        StringBuilder stringBuilder = new StringBuilder();
        Company current = head;

        while (current != null) {

            stringBuilder.append(current.getFileName());
            current = current.getNext();

            if (current != null) {
                stringBuilder.append(",");
            }

        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filePath))) {

            bufferedWriter.write(stringBuilder.toString());

        } catch (IOException error) {

            JOptionPane.showMessageDialog(null, "There was an error writing to the file!", "IO Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * Get the company list as a string.
     *
     * @return String of the companies.
     */
    @Override
    public String toString() {

        Company current = head;
        String string = "Companies: ";

        // Loop through linked list and create a String.
        while (current != null) {

            string = string + current.getName() + ", ";
            current = current.getNext();

        }

        return string;

    }

    /**
     * Retrieve a list of the companies as an array of companies.
     *
     * @return An array of the company names.
     */
    public Company[] toArray() {

        Company[] companyArray = new Company[this.length()]; // Create a new array, same length as linked list

        // Loop through linked list and add companies to array
        Company current = head;
        int i = 0;
        while (current != null) {

            companyArray[i] = current;
            i++;
            current = current.getNext();

        }

        return companyArray;

    }

    /**
     * Get the length of the linked list
     *
     * @return Length of the linked list.
     */
    public int length() {

        Company current = head;
        int length = 0;

        while (current != null) {
            length++;
            current = current.getNext();
        }

        return length;

    }

    /**
     * Check if the company already exists (file wise).
     *
     * @param fileName The file name of the company to search for in the linked
     * list.
     * @return Whether or not the company being searched for exists in the list.
     */
    public boolean exists(String fileName) {

        // Loop through list and check if file exists.
        Company current = head;
        while (current != null) {

            // Check if the file name we are searching for is equal to that of the current company.
            if (current.getFileName().equals(fileName)) {
                return true; // If the file is found return that the file is found.
            }

            current = current.getNext(); // Get the next company.

        }

        return false; // Return false if the company is not found.

    }

    /**
     * Check if the list is empty
     *
     * @return Whether or not the list is empty.
     */
    public boolean isEmpty() {

        return this.head == null; // If there is no first value.

    }

    /**
     * Retrieve the first company in the linked list.
     *
     * @return The first company in the linked list.
     */
    public Company getHead() {

        return this.head;

    }

}
