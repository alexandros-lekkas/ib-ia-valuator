package view.company.statistics;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import view.LoginSignup;

/**
 * The Dashboard class represents the graphical user interface for displaying
 * company statistics. It extends the javax.swing.JFrame class.
 */
public class Dashboard extends javax.swing.JFrame {

    // Logging.
    private static final Logger logger = Logger.getLogger(LoginSignup.class.getName());

    // ArrayList for company revenues.
    ArrayList<model.Statistic> revenues;

    // ArrayList for company costs.
    ArrayList<model.Statistic> costs;

    /**
     * Creates new form Statistics.
     * 
     * @param company The company that the statistics belong to.
     */
    public Dashboard(model.Company company) {
        
        company.readStatistics();
        revenues = company.getRevenues();
        costs = company.getCosts();

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
     * Add statistics to a scroll pane.
     * 
     * @param statistics ArrayList of statistics to add to this.
     * @param scrollPane JScrollPane to add the statistics to.
     */
    public void addStatistics(ArrayList<model.Statistic> statistics, JScrollPane scrollPane) {

        // Create a JPanel with BoxLayout.
        JPanel statisticPanel = new JPanel();
        statisticPanel.setLayout(new BoxLayout(statisticPanel, BoxLayout.Y_AXIS));

        for (model.Statistic statistic : statistics) {

            JButton statisticButton = new JButton(statistic.getName()); // Create a new button for the statistic.

            // Make the button fill the horizontal space.
            statisticButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            statisticButton.setMinimumSize(new Dimension(Integer.MAX_VALUE, 50));
            statisticButton.setPreferredSize(new Dimension(80, 50));
            statisticButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button.
            
            // Add the event to the button.
            statisticButton.addActionListener((ActionEvent event) -> {
                
                System.out.println(statistic.toString());

                statistic.readData();
                
                TimeSeries series = new TimeSeries("Data Value");

                // Loop through the data of the statistic.
                ArrayList<model.Data> statisticData = statistic.getData();
                for(model.Data data : statisticData) {

                    Month month = new Month(data.getMonth(), data.getYear());
                    System.out.println(month.toString());
                    series.addOrUpdate(month, data.getValue());

                }

                TimeSeriesCollection dataset = new TimeSeriesCollection();
                dataset.addSeries(series);

                // Create a chart with proper labels and dataset.
                JFreeChart chart = ChartFactory.createTimeSeriesChart(
                        
                    statistic.getName(), // Title.
                    "Month", // X-axis label.
                    "Revenue ($)", // Y-axis label.
                    dataset, // Dataset.
                    true, // Legend.
                    true, // Tooltips.
                    false // URLs.
                        
                );
                
                System.out.println("Created chart.");
                
                System.out.println(statistic.extrapolateData(12).toString());
                System.out.println((statisticData.size()-1));

                ChartFrame frame = new ChartFrame("Monthly Data", chart);
                
                frame.setVisible(true);
                frame.setSize(480,380);

            });

            statisticPanel.add(statisticButton); // Add the button to the inner panel, not the scroll panel;
            statisticPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add some space between the buttons.
            
        }

        // Add the statistic panel to the scroll pane/panel.
        scrollPane.setViewportView(statisticPanel);
        scrollPane.getViewport().revalidate();
        scrollPane.getViewport().repaint();
        
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
        subMainPanel = new javax.swing.JPanel();
        revenuesPanel = new javax.swing.JPanel();
        revenuesLabel = new javax.swing.JLabel();
        revenuesScrollPane = new javax.swing.JScrollPane();
        costsPanel = new javax.swing.JPanel();
        costsLabel = new javax.swing.JLabel();
        costsScrollPane = new javax.swing.JScrollPane();
        statisticsHeadingPanel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        titlePanel.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Company Statistics");

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
                .addGap(65, 65, 65)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(15, Short.MAX_VALUE))
        );

        subMainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        revenuesLabel.setText("Revenues");

        addStatistics(revenues, revenuesScrollPane);

        javax.swing.GroupLayout revenuesPanelLayout = new javax.swing.GroupLayout(revenuesPanel);
        revenuesPanel.setLayout(revenuesPanelLayout);
        revenuesPanelLayout.setHorizontalGroup(
            revenuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuesPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(revenuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(revenuesPanelLayout.createSequentialGroup()
                        .addComponent(revenuesLabel)
                        .addGap(0, 400, Short.MAX_VALUE))
                    .addGroup(revenuesPanelLayout.createSequentialGroup()
                        .addComponent(revenuesScrollPane)
                        .addContainerGap())))
        );
        revenuesPanelLayout.setVerticalGroup(
            revenuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(revenuesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revenuesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        subMainPanel.add(revenuesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 450, -1));

        costsLabel.setText("Costs");

        addStatistics(costs, costsScrollPane);

        javax.swing.GroupLayout costsPanelLayout = new javax.swing.GroupLayout(costsPanel);
        costsPanel.setLayout(costsPanelLayout);
        costsPanelLayout.setHorizontalGroup(
            costsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(costsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costsScrollPane)
                    .addGroup(costsPanelLayout.createSequentialGroup()
                        .addComponent(costsLabel)
                        .addGap(0, 415, Short.MAX_VALUE)))
                .addContainerGap())
        );
        costsPanelLayout.setVerticalGroup(
            costsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(costsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(costsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        subMainPanel.add(costsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 450, 220));

        statisticsHeadingPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        statisticsHeadingPanel.setText("Statistics");
        subMainPanel.add(statisticsHeadingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        backButton.setText("Back");
        backButton.setPreferredSize(new java.awt.Dimension(75, 35));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        subMainPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 35));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(subMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(subMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Displays an information message when the information button is clicked.
     *
     * @param evt The action event that triggered the click event.
     */
    private void informationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informationButtonActionPerformed

        logger.info("InformationButton clicked.");

        // Output information message.
        JOptionPane.showMessageDialog(

                this,
                "Use this interface to view statistics.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE

        );

    }//GEN-LAST:event_informationButtonActionPerformed

    /**
     * Logs a message when the window is closed.
     *
     * @param evt The WindowEvent representing the closing of the window.
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        logger.info("Window closing.");

    }//GEN-LAST:event_formWindowClosed

    /**
     * Performs the action when the back button is clicked.
     *
     * @param evt The action event that triggered the click event.
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        dispose();

    }//GEN-LAST:event_backButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel costsLabel;
    private javax.swing.JPanel costsPanel;
    private javax.swing.JScrollPane costsScrollPane;
    private javax.swing.JButton informationButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel revenuesLabel;
    private javax.swing.JPanel revenuesPanel;
    private javax.swing.JScrollPane revenuesScrollPane;
    private javax.swing.JLabel statisticsHeadingPanel;
    private javax.swing.JPanel subMainPanel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

}
