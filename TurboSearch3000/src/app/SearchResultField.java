/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchResultField.java
 *
 * Created on 05.12.2012, 7:12:33
 */
package app;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import ts3000.model.Document;

/**
 *
 * @author Андрей
 */
public class SearchResultField extends javax.swing.JPanel {

    /** Creates new form SearchResultField */
    public SearchResultField() {
        initComponents();
    }
    
    private ArrayList<Document> docs = new ArrayList<Document>(10);
    
    private int pages = 0;
    private int currentPage;
    protected MainWindow parentWindow;
    
    private boolean fromCategory = false;
    
    private void makeVisibleByPage(int pageNum) {
        if (pageNum < pages - 1) {
            makeVisibleByNum(4);
        } else {
            int howMuch = docs.size() % 4;
            if (howMuch == 0 && pages > 0) {
                howMuch = 4;
            }
            makeVisibleByNum(howMuch);
        }   
    }
    
    private void makeVisibleByNum(int num) {
        searchResult1.setVisible(num >= 1);
        searchResult2.setVisible(num >= 2);
        searchResult3.setVisible(num >= 3);
        searchResult4.setVisible(num >= 4);        
    }
    
    public void filter() {
        ArrayList<Document> newDocs = new ArrayList<Document>(docs.size());
        
        
        
        for (Document i : docs) {
            if (i != null)
                newDocs.add(i);
        }
        
        docs = newDocs;
        
        try {
            DocComparator comp = null;

            if (cmbSortBy.getModel().getSelectedItem().toString().equals("name ascending")) {
                comp = new DocComparator(0);
            }

            if (cmbSortBy.getModel().getSelectedItem().toString().equals("name descending")) {
                comp = new DocComparator(1);
            }

            if (cmbSortBy.getModel().getSelectedItem().toString().equals("date")) {
                comp = new DocComparator(2);
            }
            
            if (cmbSortBy.getModel().getSelectedItem().toString().equals("relevance")) {
                comp = new DocComparator(3);
            }

            Collections.sort(docs, comp);
        } catch (Exception ex) { }
    }
    
    public boolean setPage(int newPage) {
        if (pages < newPage || newPage < 0) {
            return false;
        } else {
            currentPage = newPage;
            if (pages == 0) {
                lblCurPage.setText("Found nothing");
            }else {
                lblCurPage.setText("Page " + Integer.toString(currentPage + 1) + " of " + Integer.toString(pages));
            }
            makeVisibleByPage(newPage);
            int howMuch = docs.size() % 4;
            if (howMuch == 0 || newPage < pages - 1) {
                howMuch = 4;
            }
            
            lnkPrevPage.setEnabled(currentPage != 0);
            lnkNextPage.setEnabled(currentPage != pages - 1);
            
            if (pages == 0) {
                lnkPrevPage.setVisible(false);
                lnkNextPage.setVisible(false);
                Font font = lblCurPage.getFont();
                lblCurPage.setFont(font.deriveFont((float)40.));
                lblCurPage.setForeground(new Color(200, 200, 200));
                return true;
            } else {
                lnkPrevPage.setVisible(true);
                lnkNextPage.setVisible(true);
                Font font = lblCurPage.getFont();
                lblCurPage.setFont(font.deriveFont((float)11.));
                lblCurPage.setForeground(Color.black);
            }
            
            int maxTextLen = 120;
            
            if (howMuch >= 1) {
                Document doc = docs.get(newPage*4 + 0);
                searchResult1.setCaption(doc.getTitle());
                String desc = doc.getAnnotation();
                if (desc.equals("")) {
                    desc = doc.getPlainText().substring(2);
                }
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult1.setDescription(desc);
                searchResult1.setPath(/*"category - " + */doc.getCategory());
            }
            if (howMuch >= 2) {
                Document doc = docs.get(newPage*4 + 1);
                searchResult2.setCaption(doc.getTitle());
                String desc = doc.getAnnotation();
                if (desc.equals("")) {
                    desc = doc.getPlainText().substring(2);
                }
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult2.setDescription(desc);
                searchResult2.setPath(doc.getCategory());
            }
            if (howMuch >= 3) {
                Document doc = docs.get(newPage*4 + 2);
                searchResult3.setCaption(doc.getTitle());
                String desc = doc.getAnnotation();
                if (desc.equals("")) {
                    desc = doc.getPlainText().substring(2);
                }
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult3.setDescription(desc);
                searchResult3.setPath(doc.getCategory());
            }
            if (howMuch >= 4) {
                Document doc = docs.get(newPage*4 + 3);
                searchResult4.setCaption(doc.getTitle());
                String desc = doc.getAnnotation();
                if (desc.equals("")) {
                    desc = doc.getPlainText().substring(2);
                }
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult4.setDescription(desc);
                searchResult4.setPath(doc.getCategory());
            }
            
            return true;
        }
    }
    
    public void setSource(Document[] docs) {
        this.docs = new ArrayList<Document>(docs.length);
        this.docs.addAll(Arrays.asList(docs));
        filter();
        pages = (docs.length + 3)/4;
        setPage(0);
    }
    
    public void setSource(ArrayList<Document> docs) {
        this.docs = docs;
        filter();
        pages = (docs.size() + 3)/4;
        setPage(0);
    }
    
    public void setSize(int i) {
        this.setSize(300, i*120 + 100);
        this.setVisible(false);
    }
    
    protected void setThreeWaySorting() {
        cmbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "name ascending", "name descending", "date" }));
    }
    
    protected void setCompleteSorting() {
        cmbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "relevance", "name ascending", "name descending", "date" }));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchResult1 = new app.SearchResult();
        searchResult2 = new app.SearchResult();
        searchResult3 = new app.SearchResult();
        searchResult4 = new app.SearchResult();
        cmbSortBy = new javax.swing.JComboBox();
        lblSortBy = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lnkPrevPage = new javax.swing.JLabel();
        lnkNextPage = new javax.swing.JLabel();
        lblCurPage = new javax.swing.JLabel();
        lnkToCategories = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));

        searchResult1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchResult1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchResult1MouseExited(evt);
            }
        });

        searchResult2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchResult2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchResult2MouseExited(evt);
            }
        });

        searchResult3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchResult3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchResult3MouseExited(evt);
            }
        });

        searchResult4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchResult4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchResult4MouseExited(evt);
            }
        });

        cmbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "name ascending", "name descending", "date" }));
        cmbSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortByActionPerformed(evt);
            }
        });

        lblSortBy.setText("sort by:");

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24));
        lblTitle.setText("Search results");

        lnkPrevPage.setText("<<");
        lnkPrevPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkPrevPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkPrevPageMouseClicked(evt);
            }
        });

        lnkNextPage.setText(">>");
        lnkNextPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkNextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkNextPageMouseClicked(evt);
            }
        });

        lblCurPage.setText("curr");

        lnkToCategories.setFont(new java.awt.Font("Tahoma", 0, 18));
        lnkToCategories.setForeground(new java.awt.Color(0, 0, 255));
        lnkToCategories.setText("<< back to categories");
        lnkToCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkToCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkToCategoriesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lnkToCategoriesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lnkToCategoriesMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitle)
                                .addGap(18, 18, 18)
                                .addComponent(lnkToCategories)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(lblSortBy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchResult2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                            .addComponent(searchResult3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                            .addComponent(searchResult4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                            .addComponent(searchResult1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(lnkPrevPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCurPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lnkNextPage)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle)
                    .addComponent(lblSortBy)
                    .addComponent(lnkToCategories))
                .addGap(18, 18, 18)
                .addComponent(searchResult1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchResult2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchResult3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchResult4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurPage)
                    .addComponent(lnkNextPage)
                    .addComponent(lnkPrevPage))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lnkPrevPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkPrevPageMouseClicked
        if (currentPage == 0)
            return;
        
        setPage(currentPage - 1);
    }//GEN-LAST:event_lnkPrevPageMouseClicked

    private void lnkNextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkNextPageMouseClicked
        if (currentPage == pages - 1)
            return;
        
        setPage(currentPage + 1);
    }//GEN-LAST:event_lnkNextPageMouseClicked

    private void searchResult1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult1MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+0));
        parentWindow.viewerPanel.openedFrom = 0;
        parentWindow.viewerPanel.lnkBack.setText("<< back to the list of the documents");
    }//GEN-LAST:event_searchResult1MouseClicked

    private class DocComparator implements Comparator<Document> {
        
        public int state = 0; // 0 for title-asc sort, 1 for title-desc sort, 2 for date sort, 3 for range
        
        public DocComparator() { }
        
        public DocComparator(int state) {
            this.state = state;
        }
        
        @Override
        public int compare(Document o1, Document o2) {
            if (state == 1)
                return o2.getTitle().compareTo(o1.getTitle());
            if (state == 2)
                return o2.getDate().compareTo(o1.getDate());
            if (state == 3)
                return o2.getRangeParameter().compareTo(o1.getRangeParameter());
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }
    
    private void cmbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortByActionPerformed
        
        try {
            DocComparator comp = null;

            if (cmbSortBy.getModel().getSelectedItem().toString().equals("name ascending")) {
                comp = new DocComparator(0);
            }

            if (cmbSortBy.getModel().getSelectedItem().toString().equals("name descending")) {
                comp = new DocComparator(1);
            }

            if (cmbSortBy.getModel().getSelectedItem().toString().equals("date")) {
                comp = new DocComparator(2);
            }
            
            if (cmbSortBy.getModel().getSelectedItem().toString().equals("relevance")) {
                comp = new DocComparator(3);
            }

            Collections.sort(docs, comp);
            setPage(0);
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_cmbSortByActionPerformed

    private void searchResult2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult2MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+1));
        parentWindow.viewerPanel.openedFrom = 0;
        parentWindow.viewerPanel.lnkBack.setText("<< back to the list of the documents");
    }//GEN-LAST:event_searchResult2MouseClicked

    private void searchResult3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult3MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+2));
        parentWindow.viewerPanel.openedFrom = 0;
        parentWindow.viewerPanel.lnkBack.setText("<< back to the list of the documents");
    }//GEN-LAST:event_searchResult3MouseClicked

    private void searchResult4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult4MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+3));
        parentWindow.viewerPanel.openedFrom = 0;
        parentWindow.viewerPanel.lnkBack.setText("<< back to the list of the documents");
    }//GEN-LAST:event_searchResult4MouseClicked

    private void lnkToCategoriesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkToCategoriesMouseEntered
        lnkToCategories.setForeground(new Color(0, 0, 150));
    }//GEN-LAST:event_lnkToCategoriesMouseEntered

    private void lnkToCategoriesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkToCategoriesMouseExited
        lnkToCategories.setForeground(Color.blue);
    }//GEN-LAST:event_lnkToCategoriesMouseExited

    private void lnkToCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnkToCategoriesMouseClicked
        parentWindow.setCategoriesPanel();
    }//GEN-LAST:event_lnkToCategoriesMouseClicked

    private void searchResult1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult1MouseEntered
        parentWindow.prevViewer.loadDocumentWithoutSaving(docs.get(currentPage*4+0));
        parentWindow.previewPanel.setVisible(true);
    }//GEN-LAST:event_searchResult1MouseEntered

    private void searchResult1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult1MouseExited
        parentWindow.previewPanel.setVisible(false);
    }//GEN-LAST:event_searchResult1MouseExited

    private void searchResult2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult2MouseEntered
        parentWindow.prevViewer.loadDocumentWithoutSaving(docs.get(currentPage*4+1));
        parentWindow.previewPanel.setVisible(true);
    }//GEN-LAST:event_searchResult2MouseEntered

    private void searchResult2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult2MouseExited
        parentWindow.previewPanel.setVisible(false);
    }//GEN-LAST:event_searchResult2MouseExited

    private void searchResult3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult3MouseEntered
        parentWindow.prevViewer.loadDocumentWithoutSaving(docs.get(currentPage*4+2));
        parentWindow.previewPanel.setVisible(true);
    }//GEN-LAST:event_searchResult3MouseEntered

    private void searchResult3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult3MouseExited
        parentWindow.previewPanel.setVisible(false);
    }//GEN-LAST:event_searchResult3MouseExited

    private void searchResult4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult4MouseEntered
        parentWindow.prevViewer.loadDocumentWithoutSaving(docs.get(currentPage*4+3));
        parentWindow.previewPanel.setVisible(true);
    }//GEN-LAST:event_searchResult4MouseEntered

    private void searchResult4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult4MouseExited
        parentWindow.previewPanel.setVisible(false);
    }//GEN-LAST:event_searchResult4MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JComboBox cmbSortBy;
    protected javax.swing.JLabel lblCurPage;
    protected javax.swing.JLabel lblSortBy;
    protected javax.swing.JLabel lblTitle;
    protected javax.swing.JLabel lnkNextPage;
    protected javax.swing.JLabel lnkPrevPage;
    protected javax.swing.JLabel lnkToCategories;
    protected app.SearchResult searchResult1;
    protected app.SearchResult searchResult2;
    protected app.SearchResult searchResult3;
    protected app.SearchResult searchResult4;
    // End of variables declaration//GEN-END:variables
}
