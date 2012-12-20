/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HTMLViewer.java
 *
 * Created on 20.12.2012, 8:35:00
 */
package app;

import java.awt.Color;
import ts3000.model.Document;

/**
 *
 * @author Андрей
 */
public class HTMLViewer extends javax.swing.JPanel {

    /** Creates new form HTMLViewer */
    public HTMLViewer() {
        initComponents();
        this.viewer.setContentType("text/html");
        this.viewer.setText("");
        this.viewer.setEditable(false);
        lnkBack.setForeground(Color.blue);
    }
    
    RecentFilesHelper mgr = null;
    
    protected boolean openedFromHistory = false; // means opened from search results
    
    protected MainWindow parentWindow;
    
    public void setText(String text) {
        this.viewer.setText(text);
    }
    
    public void loadDocument(Document doc) {
        
        if (mgr == null) {
            mgr = new RecentFilesHelper();
        }
        
        mgr.loadRecentFilesList();
        
        mgr.addNewRecentFile(doc.getCategory() + "/" + doc.getTitle());
        
        mgr.saveRecentFilesList();
        
        this.viewer.setText(doc.getText());
        rememberTheFile(doc);
    }
    
    public void rememberTheFile(Document doc) {
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        viewer = new javax.swing.JEditorPane();
        lnkBack = new javax.swing.JLabel();

        scrollPanel.setViewportView(viewer);

        lnkBack.setText("<< back to the list of the documents");
        lnkBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lnkBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lnkBackMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lnkBack)
                .addContainerGap(457, Short.MAX_VALUE))
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lnkBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lnkBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkBackMouseEntered
        lnkBack.setForeground(new Color(0, 0, 150));
    }//GEN-LAST:event_lnkBackMouseEntered

    private void lnkBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkBackMouseExited
        lnkBack.setForeground(Color.blue);
    }//GEN-LAST:event_lnkBackMouseExited

    private void lnkBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkBackMouseClicked
        if (openedFromHistory)
            parentWindow.setHistoryPanel();
        else 
            parentWindow.setSearchResults();
    }//GEN-LAST:event_lnkBackMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lnkBack;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JEditorPane viewer;
    // End of variables declaration//GEN-END:variables
}
