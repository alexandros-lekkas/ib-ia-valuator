package view.company.statistics;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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

/**
 * Statistics dashboard to manage all of the statistics that belong to the company.
 * 
 * @author ALexandros Lekkas.
 */
public class Dashboard extends javax.swing.JFrame {
    
    ArrayList<model.Statistic> revenues; // ArrayList for company revenues.
    ArrayList<model.Statistic> costs; // ArrayList for company costs.

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

        } catch (UnsupportedLookAndFeelException error) { // Catch a look and feel error.

            JOptionPane.showMessageDialog(null, "Visual settings not loaded properly!", "Error", JOptionPane.ERROR_MESSAGE);

        }

        java.awt.EventQueue.invokeLater(() -> { initComponents(); }); // Initialise the co on a new thread.
    
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
                    series.add(month, data.getValue());
                }

                TimeSeriesCollection dataset = new TimeSeriesCollection();
                dataset.addSeries(series);

                // Create a chart with proper labels and dataset.
                JFreeChart chart = ChartFactory.createTimeSeriesChart(
                        
                    "Monthly Data", // Title.
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        costsScrollPane = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        revenuesScrollPane = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        titlePanel.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Company Statistics");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Costs");

        addStatistics(costs, costsScrollPane);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costsScrollPane)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 415, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(costsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 450, 220));

        jLabel2.setText("Revenues");

        addStatistics(revenues, revenuesScrollPane);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 400, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(revenuesScrollPane)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revenuesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 450, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Statistics");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton2.setText("Estimate Company Value");
        jButton2.setPreferredSize(new java.awt.Dimension(75, 35));
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 170, 35));

        jButton3.setText("Edit Data");
        jButton3.setPreferredSize(new java.awt.Dimension(75, 35));
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 35));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane costsScrollPane;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane revenuesScrollPane;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

}
