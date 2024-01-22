/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package view;

import model.Authentication;
import model.Company;
import model.CompanyList;
import model.User;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;

import java.util.logging.Logger;

/**
 * The Dashboard class represents the main dashboard for the application.
 */
public class Dashboard extends javax.swing.JFrame {

    // Logging.
    private static final Logger logger = Logger.getLogger(LoginSignup.class.getName());

    // Currently authenticated user.
    User user;

    // Company list of the authenticated User.
    CompanyList companies;

    // Current company selected, used for lambda expressions.
    Company company;

    /**
     * Initializes the Dashboard object.
     *
     * @param authentication The Authentication object used for user authentication.
     *                       The authentication object should contain the user object and the company list.
     */
    public Dashboard(Authentication authentication) {

        user = authentication.getUser();
        companies = user.getCompanyList();

        // Attempt to set the preferred look and feel.
        try {

            UIManager.setLookAndFeel(new FlatIntelliJLaf());

        } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) { // Catch a look and feel error.

            // Output error message.
            logger.severe(unsupportedLookAndFeelException.getMessage());
            JOptionPane.showMessageDialog(

                    null,
                    "Unsupported look and feel.",
                    "Look & Feel Error",
                    JOptionPane.ERROR_MESSAGE

            );

        }

        java.awt.EventQueue.invokeLater(this::initComponents);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        companyFileChooser = new javax.swing.JFileChooser();
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        informationButton = new javax.swing.JButton();
        dashboardPanel = new javax.swing.JPanel();
        companyScrollPane = new javax.swing.JScrollPane();
        addCompanyButton = new javax.swing.JButton();
        removeCompanyButton = new javax.swing.JButton();

        companyFileChooser.setCurrentDirectory(new File(resources.Variables.dataFolderPath + "/Companies"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Valuator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setFocusableWindowState(false);
        setName("frame"); // NOI18N
        setResizable(false);

        titlePanel.setBackground(new java.awt.Color(0, 51, 102));
        titlePanel.setPreferredSize(new java.awt.Dimension(281, 75));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Dashboard");

        informationButton.setBackground(new java.awt.Color(255, 255, 255));
        informationButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        informationButton.setForeground(new java.awt.Color(0, 51, 153));
        informationButton.setText("i");
        informationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(informationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(98, 98, 98))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(titleLabel))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(informationButton)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        dashboardPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dashboardPanelComponentShown(evt);
            }
        });

        createCompanyButtons(); // Call the company button creation.

        addCompanyButton.setText("Add Company");
        addCompanyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCompanyButtonActionPerformed(evt);
            }
        });

        removeCompanyButton.setText("Remove Company");
        removeCompanyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCompanyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(companyScrollPane)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(addCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(dashboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(dashboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Displays a confirmation message to the user when the remove company button is clicked.
     *
     * @param evt The action event.
     */
    private void removeCompanyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCompanyButtonActionPerformed

        logger.info("RemoveCompanyButton click by User.");

        // Output an information message.
        JOptionPane.showMessageDialog(

                null,
                "This interface is used for adding and opening companies.\n" +
                        "To remove a company click on it (open its dashboard) and click on the Delete button.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE

        );
        
    }//GEN-LAST:event_removeCompanyButtonActionPerformed

    /**
     * Performs an action when the add company button is clicked.
     *
     * @param evt The action event.
     */
    private void addCompanyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCompanyButtonActionPerformed

        int result = companyFileChooser.showOpenDialog(this);

        // Check the result.
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = companyFileChooser.getSelectedFile();

            // Check if the file has a .csv extension.
            if (selectedFile.getName().toLowerCase().endsWith(".csv")) {

                // Add to the company.
                model.CompanyList companyList = user.getCompanyList(); // Retrieve the list of companies.
                company = new model.Company(selectedFile.getAbsolutePath(), selectedFile.getName());
                companyList.add(company); // Add the company to the company list.
                companyList.save();
                createCompanyButtons();

            } else {

                // The selected file is not a CSV file.
                JOptionPane.showMessageDialog(this, "Please select a CSV file.", "Invalid File Type", JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_addCompanyButtonActionPerformed

    /**
     * This method is called when the dashboardPanel is shown.
     *
     * @param evt The ComponentEvent that triggered this method.
     */
    private void dashboardPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dashboardPanelComponentShown

    }//GEN-LAST:event_dashboardPanelComponentShown

    /**
     * Displays an information message to the user when the information button is clicked.
     *
     * @param evt The action event that triggered this method.
     */
    private void informationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informationButtonActionPerformed

        logger.info("Documentation opened by User.");

        // Output an information message.
        JOptionPane.showMessageDialog(

            null,
            """
            - Add Company Button: Opens a file chooser to select which file you want to get a company from.
            - Remove Company Button: Opens information popup about how to remove a company.
            - Company Button: Each company generates as a button, click each one to open its respective dashboard.
            """,
            "Information",
            JOptionPane.INFORMATION_MESSAGE

        );

    }//GEN-LAST:event_informationButtonActionPerformed

    /**
     * Creates buttons for each company in the linked list and adds them to a panel.
     * Sets the panel as the view for the JScrollPane.
     */
    public void createCompanyButtons() {
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        model.Company current = companies.getHead(); // Get the first company in the linked list.

        // Loop through the linked list creating company buttons for each company.
        while (current != null) {
            
            company = current;

            JButton companyButton = createCompanyButton();

            // Add the button to the panel, not the JScrollPane.
            buttonPanel.add(companyButton);

            current = current.getNext(); // Move to the next company.
        }

        // Set the button panel as the view for the JScrollPane.
        companyScrollPane.setViewportView(buttonPanel);
        
    }

    /**
     * Creates a JButton for the company.
     *
     * @return The JButton for the company.
     */
    private JButton createCompanyButton() {

        JButton companyButton = new JButton(company.getName()); // Create a new button for the company.

        // Make the button fill the horizontal space.
        companyButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        companyButton.setMinimumSize(new Dimension(Integer.MAX_VALUE, 50));
        companyButton.setPreferredSize(new Dimension(80, 50));
        companyButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button.

        // Add event to the button.
        companyButton.addActionListener((ActionEvent event) -> {

            new view.company.Dashboard(company).setVisible(true);

        });

        return companyButton;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCompanyButton;
    private javax.swing.JFileChooser companyFileChooser;
    private javax.swing.JScrollPane companyScrollPane;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JButton informationButton;
    private javax.swing.JButton removeCompanyButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

}
