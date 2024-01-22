package model;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.util.NoSuchElementException;

/**
 * Linked list that stores all of the companies.
 */
public final class CompanyList {

    private final String filePath; // User file, the file that holds the list of companies that belong to the user.
    private Company head; // The beginning of the linked list, the first company.

    /**
     * Constructs a CompanyList object. This constructor takes a file path as a parameter and initializes the linked
     * list of Company objects.
     *
     * @param filePath The file path to read the company file locations from.
     */
    public CompanyList(String filePath) {

        // Create the linked list of Company objects.
        this.head = null;
        this.filePath = filePath;

        // Loop through user file and create and add Company objects.
        try {

            // Use a Scanner object to read the first line in the file.
            Scanner scanner = new Scanner(new File(this.filePath));
            String line = scanner.nextLine();
            String[] companyFileNames = line.split(",");

            // Loop through the line, creating a new Company object for each file location written.
            for (String companyFileName : companyFileNames) {

                if (companyFileName.toLowerCase().endsWith(".csv")) { // If the file is a CSV file...

                    // Create a new Company object and add it to the linked list.
                    Company company = new Company(resources.Variables.dataFolderPath + "/Companies/" + companyFileName, companyFileName);
                    this.add(company);

                } else { // If the file is not a CSV file...

                    System.out.println(System.currentTimeMillis() + " [ERROR] " + "Ignored file " + companyFileName);

                }

            }

        } catch (IOException ioException) { // If there is an IOException exception...

            // Output an error message to the user.
            JOptionPane.showMessageDialog(

                    null,
                    "There was an error writing to the user file!\n" + ioException.toString(),
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        } catch (NoSuchElementException noSuchElementException) { // If there is a NoSuchElementException exception...

            // Do nothing. This does not need to be handled.

        }

    }

    /**
     * Adds a Company object to the linked list.
     *
     * @param company The Company object to be added to the linked list.
     */
    public void add(Company company) {
        
        Company current;

        // Add company to linked list.
        if (head == null) { // Check if the first value exists.

            head = company;

        } else { // Check if anything exists in list first

            current = head; // Set the first company

            while (current.getNext() != null) { // Loop through.

                current = current.getNext();

            }

            current.setNext(company); // Set next company after we reach the end of the linekd list.

        }

    }

    /**
     * Remove company from the linked list.
     *
     * @param company The company that will be removed from the linked list.
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
     * Finds the Company with the specified name and then remove it from the
     * linked list.
     * 
     * @param companyName The name of the Company object to search for.
     */
    public boolean remove(String companyName) {
        
        // Check if anything exists in the linked list.
        if (this.head == null) {
            
            return false;
            
        }
        
        Company current = head;
        Company previous = null;
        
        // If the current item in the list is the only item in hte list.
        if (current.getName().equals(companyName)) {
            
            remove(current);
            return false;

        }
        
        // Search for current Company to deleted.
        while (current != null && !current.getName().equals(companyName)) {
            
            previous = current;
            current = current.getNext();
            
        }
        
        // If Company with specified company name is not found.
        if (current == null) {
            
            return false;
            
        }
        
        remove(current);
        return true;
        
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
