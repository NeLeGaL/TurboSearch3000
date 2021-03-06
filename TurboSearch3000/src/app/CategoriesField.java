/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CategoriesField.java
 *
 * Created on 17.12.2012, 7:31:48
 */
package app;

import java.util.ArrayList;
import java.util.Arrays;
import ts3000.model.Category;

/**
 *
 * @author Андрей
 */
public class CategoriesField extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1246532928935334729L;

	/** Creates new form CategoriesField */
    public CategoriesField() {
        initComponents();
    }
    
        private ArrayList<Category> categories = new ArrayList<Category>(10);
    
    private int pages = 0;
    private int currentPage;
    protected MainWindow parentWindow;
    
    private void makeVisibleByPage(int pageNum) {
        if (pageNum < pages - 1) {
            makeVisibleByNum(4);
        } else {
            int howMuch = categories.size() % 4;
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
        ArrayList<Category> newCats = new ArrayList<Category>(categories.size());
        for (Category i : categories) {
            if (i != null)
                newCats.add(i);
        }
        
        categories = newCats;
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
            int howMuch = categories.size() % 4;
            if (howMuch == 0 || newPage < pages - 1) {
                howMuch = 4;
            }
            
            if (pages == 0) {
                return true;
            }
            
            int maxTextLen = 80;
            
            if (howMuch >= 1) {
                Category cat = categories.get(newPage*4 + 0);
                searchResult1.setCaption(cat.getName());
                String desc = cat.getAnnotation();
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult1.setPath(desc);
                searchResult1.setDescription(cat.getSize() + " item(s) in the category");
            }
            if (howMuch >= 2) {
                Category cat = categories.get(newPage*4 + 1);
                searchResult2.setCaption(cat.getName());
                String desc = cat.getAnnotation();
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult2.setPath(desc);
                searchResult2.setDescription(cat.getSize() + " item(s) in the category");
            }
            if (howMuch >= 3) {
                Category cat = categories.get(newPage*4 + 2);
                searchResult3.setCaption(cat.getName());
                String desc = cat.getAnnotation();
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult3.setPath(desc);
                searchResult3.setDescription(cat.getSize() + " item(s) in the category");
            }
            if (howMuch >= 4) {
                Category cat = categories.get(newPage*4 + 3);
                searchResult4.setCaption(cat.getName());
                String desc = cat.getAnnotation();
                if (desc.length() >= maxTextLen) {
                    desc = desc.substring(0, maxTextLen) + "...";
                }
                searchResult4.setPath(desc);
                searchResult4.setDescription(cat.getSize() + " item(s) in the category");
            }
            
            return true;
        }
    }
    
    public void setSource(Category[] categories) {
        if (categories == null) {
            pages = 0;
            setPage(0);
        }
        
        this.categories = new ArrayList<Category>(categories.length);
        this.categories.addAll(Arrays.asList(categories));
        filter();
        pages = (categories.length + 3)/4;
        setPage(0);
    }  
    
    public void setSource(ArrayList<Category> categories) {
        if (categories == null) {
            pages = 0;
            setPage(0);
        }
        this.categories = new ArrayList<Category>(categories.size());
        this.categories.addAll(categories);
        filter();
        pages = (categories.size() + 3)/4;
        setPage(0);
    }   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchResult2 = new app.SearchResult();
        searchResult1 = new app.SearchResult();
        lnkNextPage = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lnkPrevPage = new javax.swing.JLabel();
        searchResult4 = new app.SearchResult();
        lblCurPage = new javax.swing.JLabel();
        searchResult3 = new app.SearchResult();

        searchResult2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult2MouseClicked(evt);
            }
        });

        searchResult1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult1MouseClicked(evt);
            }
        });

        lnkNextPage.setText(">>");
        lnkNextPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkNextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkNextPageMouseClicked(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24));
        lblTitle.setText("Categories");

        lnkPrevPage.setText("<<");
        lnkPrevPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lnkPrevPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnkPrevPageMouseClicked(evt);
            }
        });

        searchResult4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult4MouseClicked(evt);
            }
        });

        lblCurPage.setText("curr");

        searchResult3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResult3MouseClicked(evt);
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
                            .addComponent(searchResult2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(searchResult3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(searchResult4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(searchResult1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)))
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
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void searchResult1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult1MouseClicked
        String categoryName = categories.get(currentPage*4+0).getName();
        
        parentWindow.searchResults.lblTitle.setText("Documents in category \"" + categoryName + "\"");
        parentWindow.searchResults.lnkToCategories.setVisible(true);
        
        parentWindow.searchResults.setSource(parentWindow.database.getDocsByCategory(categoryName));
        parentWindow.setSearchResults();
    }//GEN-LAST:event_searchResult1MouseClicked

    private void searchResult2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult2MouseClicked
        String categoryName = categories.get(currentPage*4+1).getName();
        
        parentWindow.searchResults.lblTitle.setText("Documents in category \"" + categoryName + "\"");
        parentWindow.searchResults.lnkToCategories.setVisible(true);
        
        parentWindow.searchResults.setSource(parentWindow.database.getDocsByCategory(categoryName));
        parentWindow.setSearchResults();
    }//GEN-LAST:event_searchResult2MouseClicked

    private void searchResult3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult3MouseClicked
        String categoryName = categories.get(currentPage*4+2).getName();
        
        parentWindow.searchResults.lblTitle.setText("Documents in category \"" + categoryName + "\"");
        parentWindow.searchResults.lnkToCategories.setVisible(true);
        
        parentWindow.searchResults.setSource(parentWindow.database.getDocsByCategory(categoryName));
        parentWindow.setSearchResults();
    }//GEN-LAST:event_searchResult3MouseClicked

    private void searchResult4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResult4MouseClicked
        String categoryName = categories.get(currentPage*4+3).getName();
        
        parentWindow.searchResults.lblTitle.setText("Documents in category \"" + categoryName + "\"");
        parentWindow.searchResults.lnkToCategories.setVisible(true);
        
        parentWindow.searchResults.setSource(parentWindow.database.getDocsByCategory(categoryName));
        parentWindow.setSearchResults();
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
