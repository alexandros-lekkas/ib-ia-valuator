
import java.io.File;
import java.io.IOException;

/**
 * The main class for the program. Running this runs the entirety of the program.
 *
 * @author Alexandros Lekkas
 */
public class Valuator {

    /**
     * When the class is run, if the app data does not already have a folder to hold its data it creates one. After
     * that, the program opens the LoginSignup interface.
     *
     * @param args Arguments for the main.
     *
     * @throws IOException In the scenario that there is an issue with accessing files the program throws an
     *                     IOException.
     */
    public static void main(String[] args) throws IOException {

        

        resources.Variables.setDataFolderPath(System.getenv("APPDATA") + "/Valuator");

        // Main folder.
        File appDataFolder = new File(resources.Variables.dataFolderPath);
        if (!appDataFolder.exists()) {

            boolean mkdirs = appDataFolder.mkdirs();

        }

        // Companies folder.
        File userbaseFile = getFile();
        if (!userbaseFile.exists()) {

            boolean newFile;
            newFile = userbaseFile.createNewFile();

        }

        new view.LoginSignup().setVisible(true); // Opens the LoginSignup interface.

    }

    private static File getFile() {

        String companiesFolderPath = resources.Variables.dataFolderPath + "/Companies";
        File companiesFolder = new File(companiesFolderPath);
        if (!companiesFolder.exists()) {

            boolean mkdirs;
            mkdirs = companiesFolder.mkdirs();

        }

        // Users folder.
        String usersFolderPath = resources.Variables.dataFolderPath + "/Users";
        File usersFolder = new File(usersFolderPath);
        if (!usersFolder.exists()) {

            boolean mkdirs;
            mkdirs = usersFolder.mkdirs();

        }

        // Userbase RandomAccessFile (.dat) file.
        String userbaseFilePath = resources.Variables.dataFolderPath + "/authentication.dat";
        return new File(userbaseFilePath);

    }

}
