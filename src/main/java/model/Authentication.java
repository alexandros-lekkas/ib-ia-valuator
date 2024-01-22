/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

import resources.Variables;

import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Class for user authentication.
 */
public class Authentication {

    private final String userbaseFilePath = Variables.dataFolderPath + "/userbase.dat";
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
                    "Error",
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
            JOptionPane.showMessageDialog(

                    null,
                    ioException.getMessage(), // Output the IOException message, this error is technical.
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return null;

        }

        // Handle user creation.
        String userFilePath = resources.Variables.dataFolderPath + "/Users/" + username + ".csv"; // Define user file.
        try (PrintWriter printWriter = new PrintWriter(userFilePath)) {
            this.user = new User(username, userFilePath); // Create user object.
            return this.user; // Return new user.
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "There was an error writing to the user file!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    private boolean checkUserExists(String newUsername) {
        boolean exists = false;
        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {
            while (file.getFilePointer() < file.length()) {
                String username = file.readUTF();
                file.readUTF(); // consume password
                if(username.equals(newUsername)) {
                    exists = true;
                    break;
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, e);
        }

        return exists;
    }

    /**
     * Logs in a user by checking if provided username and password match the stored user credentials.
     *
     * @param username The username of the user to log in.
     * @param password The password of the user to log in.
     * @return The User object corresponding to the logged in user, or null if the login details are incorrect.
     */
    public User logIn(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and/or password cannot be blank!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {
            while (file.getFilePointer() < file.length()) {
                String existingUsername = file.readUTF();
                String existingPassword = file.readUTF();

                if (existingUsername.equals(username) && existingPassword.equals(password)) {
                    String userFilePath = resources.Variables.dataFolderPath + "/Users/" + username + ".csv";
                    this.user = new User(username, userFilePath); // Create new user object.
                    return user;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Login details incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    /**
     * Returns the user object which is currently authenticated (held by the authentication object).
     *
     * @return The user object.
     */
    public User getUser() { return this.user; }

}