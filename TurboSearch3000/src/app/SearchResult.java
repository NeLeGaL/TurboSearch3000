/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchResult.java
 *
 * Created on 04.12.2012, 3:13:20
 */
package app;

import java.awt.Color;

/**
 *
 * @author Андрей
 */
public class SearchResult extends javax.swing.JPanel {

    /** Creates new form SearchResult */
    public SearchResult() {
        initComponents();
    }
    
    public void setCaption(String newCaption) {
        caption.setText(newCaption);
    }
    
    public void setDescription(String newDesc) {
        description.setText(newDesc);
    }
    
    public void setPath(String newPath) {
        path.setText(newPath);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        caption = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        path = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new java.awt.Dimension(601, 102));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        caption.setFont(new java.awt.Font("Tahoma", 0, 18));
        caption.setForeground(new java.awt.Color(0, 51, 255));
        caption.setText("Caption");
        caption.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        description.setText("description");
        description.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        path.setFont(new java.awt.Font("Tahoma", 0, 14));
        path.setForeground(new java.awt.Color(51, 102, 0));
        path.setText("full/path/to/the/file");
        path.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(description)
                        .addContainerGap(539, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(path)
                        .addContainerGap(479, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(caption, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                        .addGap(197, 197, 197))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(caption, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(path)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(description)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        this.setBackground(new Color(240, 255, 240));
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        this.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_formMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JLabel caption;
    protected javax.swing.JLabel description;
    protected javax.swing.JLabel path;
    // End of variables declaration//GEN-END:variables
}