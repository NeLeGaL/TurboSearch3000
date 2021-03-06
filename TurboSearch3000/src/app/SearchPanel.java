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

import java.util.ArrayList;
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
        tbxSearchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbxSearchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxSearchFieldFocusLost(evt);
            }
        });
        tbxSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxSearchFieldKeyTyped(evt);
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

    protected void loadDocs(String query) {
    	ArrayList<Document> result = parentWindow.database.proccessQuery(query);
    	gotDocs =  new Document[result.size()];
    	int iteration = 0;
    	for (Document doc : result) {
    		gotDocs[iteration++] = doc;
    	}
    }
    
    protected void setSearch(String searchedText) {
        loadDocs(searchedText);
        if (searchedText.length() >= 30) {
                    searchedText = searchedText.substring(0, 30) + "...";
                }
        parentWindow.searchResults.lblTitle.setText("Search results - \"" + searchedText + "\"");
        parentWindow.searchResults.lnkToCategories.setVisible(false);
        parentWindow.searchResults.setSource(gotDocs);
        parentWindow.setSearchResults();
    }
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        setSearch(tbxSearchField.getText());
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbxSearchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxSearchFieldFocusGained
        if (tbxSearchField.getText().equals("search documents...")) {
            tbxSearchField.setText("");
        }
    }//GEN-LAST:event_tbxSearchFieldFocusGained

    private void tbxSearchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxSearchFieldFocusLost
        if (tbxSearchField.getText().equals("")) {
            tbxSearchField.setText("search documents...");
        }
    }//GEN-LAST:event_tbxSearchFieldFocusLost

    private void tbxSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxSearchFieldKeyTyped
        if (evt.getKeyChar() == 10 || evt.getKeyChar() == 13) {
            btnSearchActionPerformed(null);
        }
    }//GEN-LAST:event_tbxSearchFieldKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnSearch;
    protected javax.swing.JTextField tbxSearchField;
    // End of variables declaration//GEN-END:variables
}
