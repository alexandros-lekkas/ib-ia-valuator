package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Class used to authenticate users. This class is passed into various parts of
 * the program to provide authentication.
 *
 * @author Alexandros Lekkas
 */
public class Authentication {

    private final String userbaseFilePath = resources.Variables.dataFolderPath + "/userbase.dat"; // File path to where the userbase is stored.
    private User user = null; // Current user authenticated (in this object), set to null at first as no user is authenticated.

    /**
     * Main constructor, used to create an authentication object when user is
     * logged in or signed up.
     */
    public Authentication() {

        // Nothing to perform here, we just create a new object.

    }

    /**
     * Signs up the user and creates a new user object.
     *
     * @param username Username that the user wants their account to have.
     * @param password Password that the user wants their account to have.
     * @return The user object.
     */
    public User signUp(String username, String password) {

        // Check if received blank information.
        if (username.isEmpty() || password.isEmpty()) { // If it is output an error and return null.

            JOptionPane.showMessageDialog(null, "Username and/or password cannot be blank!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;

        }

        // Access the userbase file as a random access file.
        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {

            // Check if the username already exists in the file.
            while (file.getFilePointer() < file.length()) {

                if (file.readUTF().equals(username)) {

                    file.close();

                    JOptionPane.showMessageDialog(null,
                                            "Username already exists!",
                                                "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                    return null;

                }

                file.readUTF(); // Consume password.

            }

            // Write user to database
            file.seek(file.length());
            file.writeUTF(username);
            file.writeUTF(password);

        } catch (IOException ex) {

            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);

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

    /**
     * Logs in a user by checking if the provided username and password match the stored user credentials.
     *
     * @param username The username of the user to log in.
     * @param password The password of the user to log in.
     * @return The User object corresponding to the logged in user, or null if the login details are incorrect.
     */
    public User logIn(String username, String password) {

        // Check if the username and password details are empty.
        if (username.isEmpty() || password.isEmpty()) { // If they are empty...

            // Output an error message to the user.
            JOptionPane.showMessageDialog(

                    null,
                    "Username and/or password cannot be blank!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE

            );

            return null; // Return null, meaning that the login failed.

        }

        // Access random access file.
        try (RandomAccessFile file = new RandomAccessFile(userbaseFilePath, "rw")) {

            // Loop through file.
            while (file.getFilePointer() < file.length()) {

                // Read current chunks of values.
                String existingUsername = file.readUTF();
                String existingPassword = file.readUTF();

                // Check if username and password blocks are equal to details provided by user.
                if (existingUsername.equals(username) && existingPassword.equals(password)) {

                    String userFilePath = resources.Variables.dataFolderPath + "/Users/" + username + ".csv"; // Define user file.

                    this.user = new User(username, userFilePath); // Create new user object.
                    return user; // Return user.

                }

            }

        } catch (IOException ex) {

            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);

        }

        JOptionPane.showMessageDialog(null, "Login details incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        return null; // Return null if no user is found with correct login details.

    }

    /**
     * Returns the user object which is currently authenticated (held by the authentication object).
     *
     * @return The user object.
     */
    public User getUser() {

        return this.user; // Return the current authenticated user.

    }

}
