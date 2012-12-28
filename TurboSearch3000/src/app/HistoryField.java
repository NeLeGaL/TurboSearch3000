/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HistoryField.java
 *
 * Created on 17.12.2012, 7:29:53
 */
package app;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import ts3000.model.Document;

/**
 *
 * @author Андрей
 */
public class HistoryField extends javax.swing.JPanel {

    /** Creates new form HistoryField */
    public HistoryField() {
        initComponents();
    }
    
    protected RecentFilesHelper mgr = null;
    
    private ArrayList<Document> docs = new ArrayList<Document>(10);
    
    private int pages = 0;
    private int currentPage;
    protected MainWindow parentWindow;
    
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
                    desc = doc.getPlainText();
                }
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult1.setDescription(desc);
                searchResult1.setPath(doc.getCategory());
            }
            if (howMuch >= 2) {
                Document doc = docs.get(newPage*4 + 1);
                searchResult2.setCaption(doc.getTitle());
                String desc = doc.getAnnotation();
                if (desc.equals("")) {
                    desc = doc.getPlainText();
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
                    desc = doc.getPlainText();
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
                    desc = doc.getPlainText();
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
    
    public void loadHistory() {
        
        if (mgr == null) {
            mgr = new RecentFilesHelper();
        }
        
        if (!mgr.loadRecentFilesList()) {
            docs = new ArrayList<Document>();
        } else {
            ArrayList<String> files = mgr.getRecentFilesList();
            docs = new ArrayList<Document>();
            for (String i : files) {
                String[] categoryAndTitle = i.split("/");
                docs.add(parentWindow.database.getDocument(categoryAndTitle[0] , categoryAndTitle[1]));
            }
            filter();
        }
        
        pages = (docs.size() + 3)/4;
        setPage(0);
    }                                                                         

    private class DocComparator implements Comparator<Document> {   
        @Override
        public int compare(Document o1, Document o2) {
            return o2.getDate().compareTo(o2.getDate());
        }
    }                                            


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lnkNextPage = new javax.swing.JLabel();
        lblCurPage = new javax.swing.JLabel();
        searchResult1 = new app.SearchResult();
        searchResult2 = new app.SearchResult();
        searchResult3 = new app.SearchResult();
        searchResult4 = new app.SearchResult();
        lblTitle = new javax.swing.JLabel();
        lnkPrevPage = new javax.swing.JLabel();

        lnkNextPage.setText(">>");
        lnkNextPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkNextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkNextPageMouseClicked(evt);
            }
        });

        lblCurPage.setText("curr");

        searchResult1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult1MouseClicked(evt);
            }
        });

        searchResult2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult2MouseClicked(evt);
            }
        });

        searchResult3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult3MouseClicked(evt);
            }
        });

        searchResult4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult4MouseClicked(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24));
        lblTitle.setText("History");

        lnkPrevPage.setText("<<");
        lnkPrevPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkPrevPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkPrevPageMouseClicked(evt);
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
                            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchResult2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                            .addComponent(searchResult3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                            .addComponent(searchResult4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                            .addComponent(searchResult1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)))
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
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchResult1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult1MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+0));
        parentWindow.viewerPanel.openedFrom = 1;
        parentWindow.viewerPanel.lnkBack.setText("<< back to history");
    }//GEN-LAST:event_searchResult1MouseClicked

    private void searchResult2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult2MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+1));
        parentWindow.viewerPanel.openedFrom = 1;
        parentWindow.viewerPanel.lnkBack.setText("<< back to history");
    }//GEN-LAST:event_searchResult2MouseClicked

    private void searchResult3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult3MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+2));
        parentWindow.viewerPanel.openedFrom = 1;
        parentWindow.viewerPanel.lnkBack.setText("<< back to history");
    }//GEN-LAST:event_searchResult3MouseClicked

    private void searchResult4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult4MouseClicked
        parentWindow.setViewerPanel();
        parentWindow.viewerPanel.loadDocument(docs.get(currentPage*4+3));
        parentWindow.viewerPanel.openedFrom = 1;
        parentWindow.viewerPanel.lnkBack.setText("<< back to history");
    }//GEN-LAST:event_searchResult4MouseClicked

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JLabel lblCurPage;
    protected javax.swing.JLabel lblTitle;
    protected javax.swing.JLabel lnkNextPage;
    protected javax.swing.JLabel lnkPrevPage;
    protected app.SearchResult searchResult1;
    protected app.SearchResult searchResult2;
    protected app.SearchResult searchResult3;
    protected app.SearchResult searchResult4;
    // End of variables declaration//GEN-END:variables
}
