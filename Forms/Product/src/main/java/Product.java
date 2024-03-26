
import resources.Variables;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;

/**
 * The Valuator class is the entry point of the application. It is responsible for creating the necessary folders and
 * files for storing application data and launching the LoginSignup interface.
 */
public class Product {

    // Logging.
    private static final Logger logger = Logger.getLogger(Product.class.getName());

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

        logger.info("Application started successfully.");

    }

    /**
     * Creates the necessary directories and files for the application to run.
     *
     * @throws IOException If an I/O error occurs while creating folders or files.
     */
    private static void createRequiredFilesAndFolders() throws IOException {

        // Create required folders and files.
        String[] paths = {

            Variables.dataFolderPath,
            Variables.dataFolderPath + File.separator + "Companies",
            Variables.dataFolderPath + File.separator + "Users",
            Variables.dataFolderPath + File.separator + "authentication.dat"

        };

        // Loop through all paths, creating them if they don't already exist.
        for (String path : paths) {

            File fileOrFolder = new File(path);
            if (!fileOrFolder.exists()) {

                try {

                    if (path.endsWith(".dat")) {

                        fileOrFolder.createNewFile();

                    } else {

                        fileOrFolder.mkdirs();

                    }

                } catch (IOException ioException) {

                    // Output an error message.
                    logger.severe(ioException.getMessage());
                    JOptionPane.showMessageDialog(

                            null,
                            "Issue when trying to create APPDATA files, program exiting.\n" +
                            ioException.getMessage(),
                            "IO Error",
                            JOptionPane.ERROR_MESSAGE

                    );
                    System.exit(0);

                }

            }

        }

    }

}