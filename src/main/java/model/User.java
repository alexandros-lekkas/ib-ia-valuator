/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * The User class represents a user in the system.
 */
public class User {

    // The username of the current User.
    private final String username;

    // The list of companies belonging to the User.
    private final CompanyList companyList;

    // The file path for the User.
    private final String filePath;

    /**
     * Constructs a new User object with the given username and file path.
     *
     * @param username The username of the User.
     * @param filePath The file path for the CompanyList object.
     */
    public User(String username, String filePath) {

        this.username = username;
        this.companyList = new CompanyList(filePath);
        this.filePath = filePath;

    }

    public void updateUserFile() {

        try {

            StringBuilder stringBuilder = new StringBuilder();

            // Loop through the linked list building the String.
            Company current = this.companyList.getHead();
            while (current != null) {

                stringBuilder.append(current.getFileName());
                stringBuilder.append(",");

            }

            if (!stringBuilder.isEmpty()) { stringBuilder.deleteCharAt(stringBuilder.length() - 1); }

            List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            lines.set(0, stringBuilder.toString());

            Files.write(Paths.get(this.filePath), lines, StandardOpenOption.WRITE);

        } catch (IOException ioException) {

            // Output error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Issue when trying to update user's list of companies.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    /**
     * Retrieves the username of the User.
     *
     * @return The username of the User.
     */
    public String getUsername() { return this.username; }

    /**
     * Retrieves the company list belonging to the user.
     *
     * @return The CompanyList object containing the companies.
     */
    public CompanyList getCompanyList() { return this.companyList; }

}