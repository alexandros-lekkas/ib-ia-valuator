package model;

/**
 * The User class represents a user in the system.
 */
public class User {

    // The username of the current User.
    private final String username;

    // The list of companies belonging to the User.
    private final CompanyList companyList;

    /**
     * Constructs a new User object with the given username and file path.
     *
     * @param username The username of the User.
     * @param filePath The file path for the CompanyList object.
     */
    public User(String username, String filePath) {

        this.username = username;
        this.companyList = new CompanyList(filePath);

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