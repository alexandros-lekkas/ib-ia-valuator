package model;

/**
 * Class to model the user and pass them as an object.
 */
public class User {

    private final String username; // User's username.
    private final CompanyList companyList; // The linked list of companies that belongs to the user.

    /**
     * Main constructor for the user class.
     *
     * @param username The username assigned to the user.
     * @param filePath The Path to the user's CSV file.
     */
    public User(String username, String filePath) {

        // Assign passed through values to attributes.
        this.username = username;
        // File path to the user's file.

        // Get user companies.
        this.companyList = new CompanyList(filePath); // Creates a new CompanyList object that belongs to the user, storing their companies and allowing them to manage them.

    }

    /**
     * Get the user's username.
     *
     * @return The user's username.
     */
    public String getUsername() {

        return this.username;

    }

    /**
     * Get the user's company list object.
     *
     * @return The user's CompanyList object containing the list of companies that belong to them.
     */
    public CompanyList getCompanyList() {

        return this.companyList;

    }

}
