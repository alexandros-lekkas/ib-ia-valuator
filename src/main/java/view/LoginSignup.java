package view;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;

/**
 * Interface for logging in and signing up.
 */
public final class LoginSignup extends javax.swing.JFrame {

    model.Authentication authentication; // Authentication object to authenticate the user
    boolean login = true; // Boolean that defines in what state the form should be
    model.User user;

    /**
     * Creates new form Login
     */
    public LoginSignup() {

        authentication = new model.Authentication(); // Create a new authentication object to use

        // Attempt to set the preferred look and feel.
        try {

            UIManager.setLookAndFeel(new FlatIntelliJLaf());

        } catch (UnsupportedLookAndFeelException error) { // Catch a look and feel error.
            
            JOptionPane.showMessageDialog(null, "Visual settings not loaded properly!", "Error", JOptionPane.ERROR_MESSAGE);

        }

        // Initialise the components.
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

        titlePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        informationButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        fieldPanel = new javax.swing.JPanel();
        usernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        insteadLabel = new javax.swing.JLabel();
        insteadButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titlePanel.setBackground(new java.awt.Color(0, 51, 102));
        titlePanel.setPreferredSize(new java.awt.Dimension(425, 75));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Valuator");

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
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(informationButton)
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(informationButton)))
                .addContainerGap())
        );

        usernameTextField.setForeground(new java.awt.Color(204, 204, 204));
        usernameTextField.setText("Enter your username");
        usernameTextField.setToolTipText("");
        usernameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameTextFieldFocusLost(evt);
            }
        });

        usernameLabel.setText("Username");

        passwordTextField.setForeground(new java.awt.Color(204, 204, 204));
        passwordTextField.setText("Password");
        passwordTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordTextFieldFocusLost(evt);
            }
        });

        passwordLabel.setText("Password");

        submitButton.setBackground(new java.awt.Color(0, 51, 102));
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Log in");
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitButton.setPreferredSize(new java.awt.Dimension(73, 23));
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        insteadLabel.setText("Don't already have an account?");

        insteadButton.setBackground(new java.awt.Color(0, 51, 102));
        insteadButton.setForeground(new java.awt.Color(255, 255, 255));
        insteadButton.setText("Sign up");
        insteadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insteadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insteadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fieldPanelLayout = new javax.swing.GroupLayout(fieldPanel);
        fieldPanel.setLayout(fieldPanelLayout);
        fieldPanelLayout.setHorizontalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordTextField)
                        .addComponent(passwordLabel)
                        .addComponent(usernameLabel)
                        .addGroup(fieldPanelLayout.createSequentialGroup()
                            .addComponent(insteadLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(insteadButton))
                        .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))))
        );
        fieldPanelLayout.setVerticalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insteadLabel)
                    .addComponent(insteadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(fieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(fieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
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
     * When the instead button is pressed switch the things displayed on the
     * screen and boolean
     *
     * @param evt
     */
    private void insteadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insteadButtonActionPerformed

        // Check which screen should be showing
        if (login) {

            // Buttons and values switched
            insteadButton.setText("Log in");
            submitButton.setText("Sign up");

            login = false; // Set login to false

        } else {

            // Buttons and values switched
            insteadButton.setText("Sign up");
            submitButton.setText("Log in");

            login = true; // Set login to true

        }

    }//GEN-LAST:event_insteadButtonActionPerformed

    /**
     * When submit button is pressed attempt login or signup depending on login
     * boolean
     *
     * @param evt
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

        // Check if the interface is checking for login or signup.
        if (login) { // If the program is in login mode attempt to log in the user.
            
            user = authentication.logIn(usernameTextField.getText(), passwordTextField.getText());

        } else { // If the login boolean is false, meaning that we are trying to signup, then try to signup the user.

            user = authentication.signUp(usernameTextField.getText(), passwordTextField.getText());

        }
        
        // Check if a user object was recieved.
        if (user != null) { // If it is not null, it means that either the login or the signup was sucessful.
            
            dispose(); // Close the current frame.
            new view.Dashboard(authentication).setVisible(true); // Opens a new dashboard interface.
            
        }

    }//GEN-LAST:event_submitButtonActionPerformed

    /**
     * When not focused on password field if text is empty set to placeholder
     * value
     *
     * @param evt
     */
    private void passwordTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFieldFocusLost

        if (passwordTextField.getText().equals("")) {

            passwordTextField.setText("Password");
            passwordTextField.setForeground(new Color(204, 204, 204));

        }
    }//GEN-LAST:event_passwordTextFieldFocusLost

    /**
     * When user focuses on the password field set the value to empty
     * (placeholder value)
     *
     * @param evt
     */
    private void passwordTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFieldFocusGained

        if (passwordTextField.getText().equals("Password")) {

            passwordTextField.setText("");
            passwordTextField.setForeground(new Color(0, 0, 0));

        }
    }//GEN-LAST:event_passwordTextFieldFocusGained

    /**
     * When the user is not focused on the username textfield re-add the
     * placeholder text
     *
     * @param evt
     */
    private void usernameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFieldFocusLost

        if (usernameTextField.getText().equals("")) {

            usernameTextField.setText("Enter your username");
            usernameTextField.setForeground(new Color(204, 204, 204));

        }
    }//GEN-LAST:event_usernameTextFieldFocusLost

    /**
     * When the user focuses on the username text field the placeholder is
     * removed
     *
     * @param evt
     */
    private void usernameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFieldFocusGained

        if (usernameTextField.getText().equals("Enter your username")) {

            usernameTextField.setText("");
            usernameTextField.setForeground(new Color(0, 0, 0));

        }

    }//GEN-LAST:event_usernameTextFieldFocusGained

    private void informationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informationButtonActionPerformed
        
        
        
    }//GEN-LAST:event_informationButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fieldPanel;
    private javax.swing.JButton informationButton;
    private javax.swing.JButton insteadButton;
    private javax.swing.JLabel insteadLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JButton submitButton;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

}
