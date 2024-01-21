package view.company;

import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * The merging interface where company merging occurs.
 *
 * @author Alexandros Lekkas
 */
public class Merger extends javax.swing.JFrame {
    
    model.Company company1 = null;
    model.Company company2 = null;

    /**
     * Creates new form Merging.
     */
    public Merger() {

        // Attempt to set the preferred look and feel.
        try {

            UIManager.setLookAndFeel(new FlatIntelliJLaf());

        } catch (UnsupportedLookAndFeelException error) { // Catch a look and feel error.

            JOptionPane.showMessageDialog(null, "Visual settings not loaded properly!", "Error", JOptionPane.ERROR_MESSAGE);

        }

        // Initialise the frame on a new thread.
        java.awt.EventQueue.invokeLater(() -> {
            
            initComponents();
            
        });

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
        mergingDialog = new javax.swing.JDialog();
        mainDialogPanel = new javax.swing.JPanel();
        mergingExplanationLabel = new javax.swing.JLabel();
        companyDetailsPanel = new javax.swing.JPanel();
        companyNameLabel = new javax.swing.JLabel();
        companyNameField = new javax.swing.JTextField();
        companyDescriptionLabel = new javax.swing.JLabel();
        companyDescriptionField = new javax.swing.JTextField();
        companyCountryLabel = new javax.swing.JLabel();
        companyCountryField = new javax.swing.JTextField();
        mergingButtonPanel = new javax.swing.JPanel();
        cancelMergeButton = new javax.swing.JButton();
        submitMergeButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        mainButtonPanel = new javax.swing.JPanel();
        mergeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        companySelectionPanel = new javax.swing.JPanel();
        chooseCompany2Button = new javax.swing.JButton();
        company2Field = new javax.swing.JTextField();
        chooseCompany1Button = new javax.swing.JButton();
        company1Field = new javax.swing.JTextField();

        companyFileChooser.setCurrentDirectory(new File(resources.Variables.dataFolderPath + "/Companies"));

        mergingDialog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                mergingDialogComponentShown(evt);
            }
        });

        mergingExplanationLabel.setText("Use the fields below to enter new details.");

        companyNameLabel.setText("Name");

        companyNameField.setText("Name");

        companyDescriptionLabel.setText("Description");

        companyDescriptionField.setText("Description");

        companyCountryLabel.setText("Country");

        companyCountryField.setText("Country");

        javax.swing.GroupLayout companyDetailsPanelLayout = new javax.swing.GroupLayout(companyDetailsPanel);
        companyDetailsPanel.setLayout(companyDetailsPanelLayout);
        companyDetailsPanelLayout.setHorizontalGroup(
            companyDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyDetailsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(companyDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(companyCountryField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(companyDescriptionField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(companyNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(companyDetailsPanelLayout.createSequentialGroup()
                        .addGroup(companyDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyCountryLabel)
                            .addComponent(companyDescriptionLabel)
                            .addComponent(companyNameLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        companyDetailsPanelLayout.setVerticalGroup(
            companyDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyDetailsPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(companyNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(companyDescriptionLabel)
                .addGap(4, 4, 4)
                .addComponent(companyDescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(companyCountryLabel)
                .addGap(1, 1, 1)
                .addComponent(companyCountryField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        cancelMergeButton.setText("Cancel");
        cancelMergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelMergeButtonActionPerformed(evt);
            }
        });

        submitMergeButton.setBackground(new java.awt.Color(0, 51, 102));
        submitMergeButton.setForeground(new java.awt.Color(255, 255, 255));
        submitMergeButton.setText("Merge");
        submitMergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitMergeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mergingButtonPanelLayout = new javax.swing.GroupLayout(mergingButtonPanel);
        mergingButtonPanel.setLayout(mergingButtonPanelLayout);
        mergingButtonPanelLayout.setHorizontalGroup(
            mergingButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mergingButtonPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cancelMergeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitMergeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );
        mergingButtonPanelLayout.setVerticalGroup(
            mergingButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mergingButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mergingButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mergingButtonPanelLayout.createSequentialGroup()
                        .addComponent(cancelMergeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(submitMergeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainDialogPanelLayout = new javax.swing.GroupLayout(mainDialogPanel);
        mainDialogPanel.setLayout(mainDialogPanelLayout);
        mainDialogPanelLayout.setHorizontalGroup(
            mainDialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDialogPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(mainDialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mergingButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainDialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mergingExplanationLabel)
                        .addComponent(companyDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        mainDialogPanelLayout.setVerticalGroup(
            mainDialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDialogPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(mergingExplanationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mergingButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout mergingDialogLayout = new javax.swing.GroupLayout(mergingDialog.getContentPane());
        mergingDialog.getContentPane().setLayout(mergingDialogLayout);
        mergingDialogLayout.setHorizontalGroup(
            mergingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainDialogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mergingDialogLayout.setVerticalGroup(
            mergingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mergingDialogLayout.createSequentialGroup()
                .addComponent(mainDialogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        titlePanel.setBackground(new java.awt.Color(0, 51, 102));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Merger");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(173, 173, 173))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(titleLabel)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        mergeButton.setText("Perform Merge");
        mergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainButtonPanelLayout = new javax.swing.GroupLayout(mainButtonPanel);
        mainButtonPanel.setLayout(mainButtonPanelLayout);
        mainButtonPanelLayout.setHorizontalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mergeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainButtonPanelLayout.setVerticalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(mergeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        chooseCompany2Button.setText("Choose Company");
        chooseCompany2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCompany2ButtonActionPerformed(evt);
            }
        });

        company2Field.setText("Unselected");
        company2Field.setFocusable(false);

        chooseCompany1Button.setText("Choose Company");
        chooseCompany1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCompany1ButtonActionPerformed(evt);
            }
        });

        company1Field.setText("Unselected");
        company1Field.setFocusable(false);

        javax.swing.GroupLayout companySelectionPanelLayout = new javax.swing.GroupLayout(companySelectionPanel);
        companySelectionPanel.setLayout(companySelectionPanelLayout);
        companySelectionPanelLayout.setHorizontalGroup(
            companySelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companySelectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companySelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chooseCompany1Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(company1Field, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(companySelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(company2Field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseCompany2Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        companySelectionPanelLayout.setVerticalGroup(
            companySelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companySelectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companySelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(company2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(company1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(companySelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseCompany1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseCompany2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(companySelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(mainButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(companySelectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Second company choosing button is clicked.
     * 
     * @param evt The event.
     */
    private void chooseCompany2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseCompany2ButtonActionPerformed
        
        model.Company company = selectCompany(); // Get user to select the company.
        
        if (company != null) { // If they did select a company.
            
            company2 = company; // Set the company.
            company2Field.setText(company2.getName()); // Get the name and display that.
            
        }
        
    }//GEN-LAST:event_chooseCompany2ButtonActionPerformed

    /**
     * First company choosing button is clicked.
     * 
     * @param evt The event.
     */
    private void chooseCompany1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseCompany1ButtonActionPerformed
       
                
        model.Company company = selectCompany(); // Get user to select the company.
        
        if (company != null) { // If they did select a company.
            
            company1 = company; // Set the company.
            company1Field.setText(company1.getName()); // Get the name and display that.
            
        }
        
    }//GEN-LAST:event_chooseCompany1ButtonActionPerformed

    /**
     * When the merging cancel is done just close the window.
     * 
     * @param evt The event.
     */
    private void cancelMergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelMergeButtonActionPerformed
        
        mergingDialog.setVisible(false); // Make the dialog not visisble.
        
    }//GEN-LAST:event_cancelMergeButtonActionPerformed

    /**
     * When the dialog is opened.
     * 
     * @param evt The event.
     */
    private void mergingDialogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mergingDialogComponentShown
        
        companyNameField.setText(company1.getName() + "-" + company2.getName()); // Create a combination of company names.
        companyDescriptionField.setText("The merged company of " + company1.getName() + " and " + company2.getName() + "."); // Create a placeholder description
        companyCountryField.setText(company1.getCountry()); // Take the country of the first company by default.
                
    }//GEN-LAST:event_mergingDialogComponentShown

    /**
     * Merging button pressed.
     * 
     * @param evt The event.
     */
    private void mergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeButtonActionPerformed
        
        mergingDialog.pack(); // Adjust the dialog to its components.
        mergingDialog.setLocationRelativeTo(this); // Center the new dialog to the main panel.
        mergingDialog.setVisible(true);
        
    }//GEN-LAST:event_mergeButtonActionPerformed

    /**
     * Back button is pressed closing the current window.
     * 
     * @param evt The event.
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        dispose(); // Close the current window.
        
        // Note: If this was run individually this will essentially exit the program, but that is okay as in the full functionality this will work.
        
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * The process when the companies are sent to be merged.
     * 
     * @param evt The event.
     */
    private void submitMergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitMergeButtonActionPerformed
        
        mergeCompanies(company1, company2, companyNameField.getText(), companyDescriptionField.getText(), companyCountryField.getText()); // Merge companies.
        
        mergingDialog.setVisible(false); // Close the dialog.

        JOptionPane.showMessageDialog(this, "Companies successfuly merged!");
        
    }//GEN-LAST:event_submitMergeButtonActionPerformed

    /**
     * Main function used to test the interface without running the entirety of
     * the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String args[]) {
        
        resources.Variables.setDataFolderPath(System.getenv("APPDATA") + "/Valuator"); // Sets the main data folder path. Used for file location.

        new Merger().setVisible(true); // Create a new merging panel.

    }
    
    /**
     * Use the file chooser to get the company.
     * 
     * @return The company selected.
     */
    public model.Company selectCompany() {
        
        model.Company company = null; // Create a null company.
        
        // Show the file chooser dialog.
        companyFileChooser.setCurrentDirectory(new File(resources.Variables.dataFolderPath + "/Companies"));
        int result = companyFileChooser.showOpenDialog(this);

        // Check the result.
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = companyFileChooser.getSelectedFile();

            // Check if the file has a .csv extension.
            if (selectedFile.getName().toLowerCase().endsWith(".csv")) {

                // Add to the company.
                company = new model.Company(selectedFile.getAbsolutePath());

            } else {

                // The selected file is not a CSV file.
                JOptionPane.showMessageDialog(this, "Please select a CSV file.", "Invalid File Type", JOptionPane.ERROR_MESSAGE);

            }

        } else if (result == JFileChooser.CANCEL_OPTION) {

            // The user canceled the operation.
        }
        
        return company;
        
    }

    /**
     * Merge two companies and create a new company file.
     * 
     * @param company1 First company.
     * @param company2 Second company.
     * @param name New company name.
     * @param description New company description.
     * @param country New company country.
     */
    public void mergeCompanies(model.Company company1, model.Company company2, String name, String description, String country) {

        String filePath = resources.Variables.dataFolderPath + "/Companies/" + name + ".csv";

        File file = new File(filePath); // Create a new file from the file path that is common to companies.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            // Write down the company details to file.
            writer.write("DETAILS\n");
            writer.write("Name," + name + "\n");
            writer.write("Description," + description + "\n");
            writer.write("Country," + country + "\n");
            writer.write("END OF DETAILS\n\n");

            // Get revenues and costs from each of the companies.
            ArrayList<model.Statistic> revenues1 = company1.getRevenues();
            ArrayList<model.Statistic> revenues2 = company2.getRevenues();
            ArrayList<model.Statistic> costs1 = company1.getCosts();
            ArrayList<model.Statistic> costs2 = company2.getCosts();
            readData(revenues1);
            readData(revenues2);
            readData(costs1);
            readData(costs2);
            
            // Combine revenues and costs.
            ArrayList<model.Statistic> combinedRevenues = combineStatistics(revenues1, revenues2);
            ArrayList<model.Statistic> combinedCosts = combineStatistics(costs1, costs2);
            
            // Output the new data for the company.
            System.out.println(name + " | " + description + " | " + country);
            
            // Write statistics.
            writer.write("STATISTICS\n");
            String[] lineArray;
            for (model.Statistic statistic : combinedRevenues) {
                
                lineArray = statisticToLines("Revenue", statistic);
                
                System.out.println(Arrays.toString(lineArray));
                
                writer.write(lineArray[0]);
                writer.write(lineArray[1]);
                
            }
            for (model.Statistic statistic : combinedCosts) {
                
                lineArray = statisticToLines("Cost", statistic);
                
                writer.write(lineArray[0]);
                writer.write(lineArray[1]);
                
            }
            writer.write("END OF STATISTICS\n");
            
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Issue making changes to company file. Consider checking if file is accessible.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
        // Open the file for the user.
        if (Desktop.isDesktopSupported()) {
            
            try {
                
                Desktop.getDesktop().open(file);
                
            } catch (IOException exception) {
                
                JOptionPane.showMessageDialog(null, "Could not open the company file.", "Error", JOptionPane.ERROR_MESSAGE);
            
            }
            
        } else {
            
            JOptionPane.showMessageDialog(null, "Desktop is not supported on this system.", "Error", JOptionPane.ERROR_MESSAGE);
        
        }

    }
    
    /**
     * Read data loop.
     * 
     * @param statistics Statistic to read the data for.
     */
    public void readData(ArrayList<model.Statistic> statistics) {
        
        for (model.Statistic statistic : statistics) {
            
            statistic.readData();
            
        }
        
    }

    /**
     * Takes two statistic ArrayLists and combines their statistics.
     *
     * @param statistics1 First ArrayList.
     * @param statistics2 Second ArrayList.
     * @return The new ArrayList created from combining them.
     */
    public ArrayList<model.Statistic> combineStatistics(ArrayList<model.Statistic> statistics1, ArrayList<model.Statistic> statistics2) {
        
        ArrayList<model.Statistic> combinedStatistics = new ArrayList<>(); // Create the new ArrayList to save the merged details.
        
        // Duplicate lists to use for re-adding.
        ArrayList<model.Statistic> statistics1copy = new ArrayList<>(statistics1);
        ArrayList<model.Statistic> statistics2copy = new ArrayList<>(statistics2);

        // Loop through the two statistics finding similar ones and perfomring corresponding logic.
        for (model.Statistic statistic1 : statistics1) {
            
            // Loop through spessscifically the second one, doesn't matter in which order things are.
            for (model.Statistic statistic2 : statistics2) {
                    
                // Check if statistics have the same name, made to uppercase to avoid character issues.
                if (statistic1.getName().toUpperCase().equals(statistic2.getName().toUpperCase())) {
                    
                    // Remove statistics from their ArrayLists.
                    statistics1copy.remove(statistic1);
                    statistics2copy.remove(statistic2);
                    
                    // Combine data points from stat1 and stat2.
                    ArrayList<model.Data> combinedData = combineData(statistic1.getData(), statistic2.getData());
                    combinedStatistics.add(new model.Statistic(statistic1.getName(), combinedData));
                    
                }
                
            }
            
        }
        
        // Add the remaining uncombined statistics from the copies
        combinedStatistics.addAll(statistics1copy);
        combinedStatistics.addAll(statistics2copy);
        
        System.out.println(combinedStatistics);

        return combinedStatistics; // Return the combined statistics.
        
        // Note: We do not need to specify if these are costs or revenues as they are pre-organised into lists.
        
    }
    
    /**
     * Combine data points.
     * 
     * @param data1 The data from the first statistic.
     * @param data2 The data from the second statistic.
     * @return 
     */
    private ArrayList<model.Data> combineData(ArrayList<model.Data> data1, ArrayList<model.Data> data2) {
        
        ArrayList<model.Data> combinedData = new ArrayList<>(); // Create a new ArrayList to store data.
        
        // Create two new copy ArrayLists of the data.
        ArrayList<model.Data> data1copy = new ArrayList<>(data1);
        ArrayList<model.Data> data2copy = new ArrayList<>(data2);
        
        // Loop through the data and check if data points are existing.
        for (model.Data dataPoint1 : data1) {
            
            System.out.println(dataPoint1.toString());
            
            // Loop through specifcally second data here, order does not matter.
            for (model.Data dataPoint2 : data2) {
                
                System.out.println(dataPoint2.toString());
                
                // If they have the same data of data then...
                if (dataPoint1.getYear() == dataPoint2.getYear() && dataPoint1.getMonth() == dataPoint2.getMonth()) {
                    
                    // Remove the data values.
                    data1copy.remove(dataPoint1);
                    data2copy.remove(dataPoint2);
                    
                    combinedData.add(new model.Data(dataPoint1.getYear(), dataPoint1.getMonth(), (dataPoint1.getValue() + dataPoint2.getValue()))); // Add the combined data to the ArrayList of data.
                    
                }
                
            }
            
        }
        
        // Add the remaining data to the combined list.
        combinedData.addAll(data1copy);
        combinedData.addAll(data2copy);
        
        // Sort the combined data.
        combinedData.sort((dataPoint1, dataPoint2) -> {
            
            if (dataPoint1.getYear() != dataPoint2.getYear()) {
                
               return Integer.compare(dataPoint1.getYear(), dataPoint2.getYear());
                
            } else {
                
                return Integer.compare(dataPoint1.getMonth(), dataPoint2.getMonth());
            
            }
            
        });
        
        System.out.println(combinedData);
        
        return combinedData; // Return the data that was combined.
        
    }
    
    /**
     * Writes statistic to file.
     * 
     * @param statisticType The type of statistic.
     * @param statistic Statistic to convert.
     * @return An array holding each line.
     */
    public String[] statisticToLines(String statisticType, model.Statistic statistic) {
        
        ArrayList<model.Data> data = statistic.getData(); // Get the data for that specific statistic.
        StringBuilder statisticLine1 = new StringBuilder(statisticType + "," + statistic.getName());
        StringBuilder statisticLine2 = new StringBuilder(",");
        int lastYear = -1;
        
        // Loop through data creating the data lines.
        int month = 1;
        for (model.Data dataPoint : data) {
            
            if (lastYear != dataPoint.getYear()) {
                
                statisticLine1.append(",,").append(dataPoint.getYear());
                statisticLine2.append(",,"); // Align values with the year.
                lastYear = dataPoint.getYear();
                month = 1;
                              
            }
         
            statisticLine1.append(",").append(month);
            statisticLine2.append(",").append(dataPoint.getValue());
            
            month++;
             
        }
        
        return new String[] { statisticLine1.append("\n").toString(), statisticLine2.append("\n").toString() };
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelMergeButton;
    private javax.swing.JButton chooseCompany1Button;
    private javax.swing.JButton chooseCompany2Button;
    private javax.swing.JTextField company1Field;
    private javax.swing.JTextField company2Field;
    private javax.swing.JTextField companyCountryField;
    private javax.swing.JLabel companyCountryLabel;
    private javax.swing.JTextField companyDescriptionField;
    private javax.swing.JLabel companyDescriptionLabel;
    private javax.swing.JPanel companyDetailsPanel;
    private javax.swing.JFileChooser companyFileChooser;
    private javax.swing.JTextField companyNameField;
    private javax.swing.JLabel companyNameLabel;
    private javax.swing.JPanel companySelectionPanel;
    private javax.swing.JPanel mainButtonPanel;
    private javax.swing.JPanel mainDialogPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton mergeButton;
    private javax.swing.JPanel mergingButtonPanel;
    private javax.swing.JDialog mergingDialog;
    private javax.swing.JLabel mergingExplanationLabel;
    private javax.swing.JButton submitMergeButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
