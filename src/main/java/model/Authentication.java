/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import resources.Variables;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Class for user authentication.
 */
public class Authentication {

    // File path of the userbase. The file that stores the User database.
    private final String userbaseFilePath = Variables.dataFolderPath + "/userbase.dat";

    // The currently authenticated User. Starts as null as no User is authenticated.
    private User user = null;

    /**
     * This class is responsible for signing up users, logging in users, and retrieving the authenticated user object.
     */
    public Authentication() { }

    /**
     * Signs up the user and creates a new user object.
     *
     * @param username Username that user wants their account to have.
     * @param password Password that user wants their account to have.
     *
     * @return The user object.
     */
    public User signUp(String username, String password) {

        // Check fields provided by user.
        if (username.isEmpty() || password.isEmpty()) {

            // Output an error message.
            JOptionPane.showMessageDialog(

                    null,
                    "Username and/or password cannot be blank!",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return null;

        }

        // Check if the User already exists.
        if(checkUserExists(username)) {

            // Output an error message.
            JOptionPane.showMessageDialog(

                    null,
                    "Username already exists!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return null;

        }

        // Access the userbase file as a RandomAccessFile.
        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {

            file.seek(file.length());
            file.writeUTF(username);
            file.writeUTF(password);

        } catch (IOException ioException) {

            // Output an error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Error with the userbase file.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return null;

        }

        // Create the new User.
        String userFilePath = Variables.dataFolderPath + "/Users/" + username + ".csv";
        try (PrintWriter printWriter = new PrintWriter(userFilePath)) {

            this.user = new User(username, userFilePath); // Create the new User object.
            return this.user;

        } catch (IOException ioException) {

            // Output an error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Error creating new user file.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return null;

        }

    }

    /**
     * Checks if a user with the given username exists in the userbase file.
     *
     * @param newUsername The username to check for existence.
     * @return True if a user with the given username exists, false otherwise.
     */
    private boolean checkUserExists(String newUsername) {

        boolean exists = false;

        // Try to search through RandomAccessFile to see if the username exists.
        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {

            while (file.getFilePointer() < file.length()) {

                // Read username and password and check.
                String username = file.readUTF();
                file.readUTF(); // consume password
                if(username.equals(newUsername)) {

                    exists = true;
                    break;

                }

            }

        } catch (IOException ioException) {

            // Output error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Error when searching for username in userbase.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }

        return exists;
    }

    /**
     * Logs in a user with the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     *
     * @return The user object if login is successful, null otherwise.
     */
    public User logIn(String username, String password) {

        // Check the inputted fields.
        if (username.isEmpty() || password.isEmpty()) {

            // Output error message.
            JOptionPane.showMessageDialog(

                    null,
                    "Username and/or password cannot be blank!",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );
            return null;

        }

        // Try to check for the user using the RandomAccessFile.
        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {

            // Loop through the RandomAccessFile.
            while (file.getFilePointer() < file.length()) {

                // Read the current username and password.
                String existingUsername = file.readUTF();
                String existingPassword = file.readUTF();

                // Check if the username provided matches up with the one in the file (same for password).
                if (existingUsername.equals(username) && existingPassword.equals(password)) {

                    String userFilePath = resources.Variables.dataFolderPath + "/Users/" + username + ".csv";
                    this.user = new User(username, userFilePath);

                    return user;

                }

            }

        } catch (IOException ioException) {

            // Output error message.
            System.out.println(ioException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Error when trying to register.",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }

        // Output login failed message.
        JOptionPane.showMessageDialog(

                null,
                "Incorrect details!",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE

        );
        return null;

    }

    /**
     * Returns the user object which is currently authenticated (held by the authentication object).
     *
     * @return The user object.
     */
    public User getUser() { return this.user; }

}