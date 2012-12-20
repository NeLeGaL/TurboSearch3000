/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on 01.12.2012, 1:49:32
 */
package app;

import java.awt.Color;
import java.awt.TrayIcon.MessageType;
import javax.swing.JOptionPane;

import ts3000.model.Database;

/**
 *
 * @author Андрей
 */
public class MainWindow extends javax.swing.JApplet {
    int resCount = 0;
    protected Database database;
    
    public void addResult()
    {
        
    }
    
    /** Initializes the applet MainWindow */
    public void init() {
        try {
            
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                    //searchButton.setF
                    searchDocumentLabel.setFocusable(true);
                    allDocumentsLabel.setFocusable(true);
                    historyLabel.setFocusable(true);
                    
                    setSearchPanel();
                    searchPanel.tbxSearchField.requestFocus();
                }
            });
            searchPanel.parentWindow = this;
            searchResults.parentWindow = this;
            viewerPanel.parentWindow = this;
            categoriesField.parentWindow = this;
            historyField.parentWindow = this;
            database = new Database("C:\\Users\\Андрей\\Documents\\GitHub\\TurboSearch3000\\TurboSearch3000\\documents.txt");
            //database = new Database("/Users/kolesov93/Documents/workspace/TurboSearch3000/TurboSearch3000/documents.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        searchDocumentLabel = new javax.swing.JLabel();
        allDocumentsLabel = new javax.swing.JLabel();
        historyLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        contentPanel = new javax.swing.JPanel();
        searchPanel = new app.SearchPanel();
        searchResults = new app.SearchResultField();
        historyField = new app.HistoryField();
        categoriesField = new app.CategoriesField();
        viewerPanel = new app.HTMLViewer();

        setBackground(new java.awt.Color(0, 255, 255));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        menuPanel.setBackground(new java.awt.Color(230, 230, 230));

        searchDocumentLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        searchDocumentLabel.setForeground(new java.awt.Color(0, 0, 255));
        searchDocumentLabel.setText("Search document");
        searchDocumentLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchDocumentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchDocumentLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchDocumentLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchDocumentLabelMouseExited(evt);
            }
        });
        searchDocumentLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchDocumentLabelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchDocumentLabelFocusLost(evt);
            }
        });
        searchDocumentLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDocumentLabelKeyPressed(evt);
            }
        });

        allDocumentsLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        allDocumentsLabel.setForeground(new java.awt.Color(0, 0, 255));
        allDocumentsLabel.setText("All documents");
        allDocumentsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allDocumentsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allDocumentsLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                allDocumentsLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                allDocumentsLabelMouseExited(evt);
            }
        });
        allDocumentsLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                allDocumentsLabelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                allDocumentsLabelFocusLost(evt);
            }
        });
        allDocumentsLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allDocumentsLabelKeyPressed(evt);
            }
        });

        historyLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        historyLabel.setForeground(new java.awt.Color(0, 0, 255));
        historyLabel.setText("History");
        historyLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        historyLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                historyLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                historyLabelMouseExited(evt);
            }
        });
        historyLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                historyLabelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                historyLabelFocusLost(evt);
            }
        });
        historyLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                historyLabelKeyPressed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchField.setText("enter text for search...");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchDocumentLabel)
                .addGap(18, 18, 18)
                .addComponent(allDocumentsLabel)
                .addGap(18, 18, 18)
                .addComponent(historyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchDocumentLabel)
                    .addComponent(allDocumentsLabel)
                    .addComponent(historyLabel)
                    .addComponent(searchButton)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        searchPanel.setBackground(new java.awt.Color(255, 255, 255));

        searchResults.setBackground(new java.awt.Color(255, 255, 255));

        historyField.setBackground(new java.awt.Color(255, 255, 255));

        categoriesField.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(historyField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoriesField, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchResults, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(categoriesField, 0, 0, Short.MAX_VALUE)
                            .addComponent(historyField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, Short.MAX_VALUE)))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchResults, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(261, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        if (searchField.getText().equals("enter text for search...")) {
            searchField.setText("");
        }
    }//GEN-LAST:event_searchFieldFocusGained

    private void searchDocumentLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchDocumentLabelFocusGained
        searchDocumentLabel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_searchDocumentLabelFocusGained

    private void searchDocumentLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchDocumentLabelMouseEntered
        searchDocumentLabel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_searchDocumentLabelMouseEntered

    private void searchDocumentLabelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchDocumentLabelFocusLost
        searchDocumentLabel.setForeground(new Color(0, 0, 255));
    }//GEN-LAST:event_searchDocumentLabelFocusLost

    private void searchDocumentLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchDocumentLabelMouseExited
        if (!searchDocumentLabel.hasFocus())
            searchDocumentLabel.setForeground(new Color(0, 0, 255));
    }//GEN-LAST:event_searchDocumentLabelMouseExited

    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost
        if (searchField.getText().equals("")) {
            searchField.setText("enter text for search...");
        }
    }//GEN-LAST:event_searchFieldFocusLost

    private void searchDocumentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchDocumentLabelMouseClicked
        searchDocumentLabel.requestFocus();
        //searchPanel.tbxSearchField.requestFocus();
        activateSearchDocument();
    }//GEN-LAST:event_searchDocumentLabelMouseClicked

    private void searchDocumentLabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDocumentLabelKeyPressed
        if (evt.getKeyCode() == 32 || evt.getKeyCode() == 10) {
            activateSearchDocument();
        }
    }//GEN-LAST:event_searchDocumentLabelKeyPressed

    private void allDocumentsLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_allDocumentsLabelFocusGained
        allDocumentsLabel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_allDocumentsLabelFocusGained

    private void allDocumentsLabelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_allDocumentsLabelFocusLost
        allDocumentsLabel.setForeground(new Color(0, 0, 255));
    }//GEN-LAST:event_allDocumentsLabelFocusLost

    private void allDocumentsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allDocumentsLabelMouseEntered
        allDocumentsLabel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_allDocumentsLabelMouseEntered

    private void allDocumentsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allDocumentsLabelMouseExited
        if (!allDocumentsLabel.hasFocus()) {
            allDocumentsLabel.setForeground(new Color(0, 0, 255));
        }
    }//GEN-LAST:event_allDocumentsLabelMouseExited

    private void allDocumentsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allDocumentsLabelMouseClicked
        allDocumentsLabel.requestFocus();
        activateAllDocuments();
    }//GEN-LAST:event_allDocumentsLabelMouseClicked

    private void allDocumentsLabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allDocumentsLabelKeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 32) {
            activateAllDocuments();
        }
    }//GEN-LAST:event_allDocumentsLabelKeyPressed

    private void historyLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_historyLabelFocusGained
        historyLabel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_historyLabelFocusGained

    private void historyLabelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_historyLabelFocusLost
        historyLabel.setForeground(new Color(0, 0, 255));
    }//GEN-LAST:event_historyLabelFocusLost

    private void historyLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyLabelMouseEntered
        historyLabel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_historyLabelMouseEntered

    private void historyLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyLabelMouseExited
        if (!historyLabel.hasFocus()) {
            historyLabel.setForeground(new Color(0, 0, 255));
        }
    }//GEN-LAST:event_historyLabelMouseExited

    private void historyLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyLabelMouseClicked
        historyLabel.requestFocus();
        activateHistory();
    }//GEN-LAST:event_historyLabelMouseClicked

    private void historyLabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_historyLabelKeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 32) {
            activateHistory();
        }
    }//GEN-LAST:event_historyLabelKeyPressed

    protected  void setSearchPanel()
    {
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanelLayout.setHonorsVisibility(true);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        
        searchResults.setVisible(false);
        historyField.setVisible(false);
        categoriesField.setVisible(false);
        searchPanel.setVisible(true);
        viewerPanel.setVisible(false);
    }
    
    protected void setSearchResults()
    {
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanelLayout.setHonorsVisibility(true);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchResults, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchResults, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        
        searchResults.setVisible(true);
        historyField.setVisible(false);
        categoriesField.setVisible(false);
        searchPanel.setVisible(false);
        viewerPanel.setVisible(false);
    }
    
    protected void setHistoryPanel()
    {
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanelLayout.setHonorsVisibility(true);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(historyField, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(historyField, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        
        searchResults.setVisible(false);
        historyField.setVisible(true);
        categoriesField.setVisible(false);
        searchPanel.setVisible(false);
        viewerPanel.setVisible(false);
    }
    
    protected void setCategoriesPanel()
    {
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanelLayout.setHonorsVisibility(true);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categoriesField, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categoriesField, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        
        searchResults.setVisible(false);
        historyField.setVisible(false);
        categoriesField.setVisible(true);
        searchPanel.setVisible(false);
        viewerPanel.setVisible(false);
    }
    
    protected void setViewerPanel()
    {
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanelLayout.setHonorsVisibility(true);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        
        searchResults.setVisible(false);
        historyField.setVisible(false);
        categoriesField.setVisible(false);
        searchPanel.setVisible(false);
        viewerPanel.setVisible(true);
    }
    
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchPanel.loadDocs(searchField.getText());
        searchResults.setSource(searchPanel.gotDocs);
        setSearchResults();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        if (evt.getKeyChar() == 10 || evt.getKeyChar() == 13) {
            searchButtonActionPerformed(null);
        }
    }//GEN-LAST:event_searchFieldKeyTyped

    private void activateSearchDocument() {
        setSearchPanel();
    }
    
    private void activateAllDocuments() {
        categoriesField.setSource(database.getCategories());
        setCategoriesPanel();
    }
    
    private void activateHistory() {
        historyField.loadHistory();
        setHistoryPanel();
    }
    
    private void activateSearchResults() {
        setSearchResults();
    }
    
    private void activateViewer() {
        JOptionPane.showMessageDialog(this, "Viewer!");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allDocumentsLabel;
    protected app.CategoriesField categoriesField;
    private javax.swing.JPanel contentPanel;
    protected app.HistoryField historyField;
    private javax.swing.JLabel historyLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchDocumentLabel;
    private javax.swing.JTextField searchField;
    protected app.SearchPanel searchPanel;
    protected app.SearchResultField searchResults;
    protected app.HTMLViewer viewerPanel;
    // End of variables declaration//GEN-END:variables
}
