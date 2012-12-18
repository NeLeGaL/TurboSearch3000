/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchPanel.java
 *
 * Created on 17.12.2012, 5:23:53
 */
package app;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JOptionPane;
import ts3000.model.Document;

/**
 *
 * @author Андрей
 */
public class SearchPanel extends javax.swing.JPanel {

    /** Creates new form SearchPanel */
    public SearchPanel() {
        initComponents();
    }
    
    protected Document[] gotDocs;
    protected MainWindow parentWindow;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbxSearchField = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        tbxSearchField.setFont(new java.awt.Font("Tahoma", 0, 18));
        tbxSearchField.setText("search documents...");
        tbxSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbxSearchFieldActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnSearch.setText("Search!");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(tbxSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbxSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbxSearchFieldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tbxSearchFieldActionPerformed

    private void loadDocs(String query) {

    }
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadDocs(tbxSearchField.getText());
        parentWindow.searchResults.setSource(gotDocs);
        parentWindow.setSearchResults();
    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnSearch;
    protected javax.swing.JTextField tbxSearchField;
    // End of variables declaration//GEN-END:variables
}
