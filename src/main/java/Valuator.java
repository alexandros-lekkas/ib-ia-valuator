/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

import resources.Variables;

import java.io.File;
import java.io.IOException;

/**
 * The Valuator class is the entry point of the application. It is responsible for creating the necessary folders and
 * files for storing application data and launching the LoginSignup interface.
 */
public class Valuator {

    /**
     * The main method is the entry point of the Valuator application. It is responsible for creating the necessary
     * folders and files for storing application data and launching the LoginSignup interface.
     *
     * @param args The command-line arguments. Not used in this method.
     *
     * @throws IOException If an I/O error occurs while creating folders or files.
     */
    public static void main(String[] args) throws IOException {

        Variables.setDataFolderPath(System.getenv("APPDATA") + "/Valuator"); // Set folder for application data.
        createRequiredFilesAndFolders(); // Creates required files and folders.

        new view.LoginSignup().setVisible(true); // Output a new LoginSignup interface.
    }

    /**
     * Creates the required files and folders for the application data.
     *
     * @throws IOException If an I/O error occurs while creating folders or files.
     */
    private static void createRequiredFilesAndFolders() throws IOException {

        // Create required folders and files.
        String[] paths = {

            Variables.dataFolderPath,
            Variables.dataFolderPath + "/Companies",
            Variables.dataFolderPath + "/Users",
            Variables.dataFolderPath + "/authentication.dat"

        };

        // Loop through all the paths creating them if they don't already exist.
        for (String path : paths) {

            File fileOrFolder = new File(path);
            if (!fileOrFolder.exists()) {

                if (path.endsWith(".dat")) {

                    fileOrFolder.createNewFile();

                } else {

                    fileOrFolder.mkdirs();

                }

            }

        }

    }

}