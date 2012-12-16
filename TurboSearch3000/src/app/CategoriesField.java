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

/**
 *
 * @author Андрей
 */
public class CategoriesField extends javax.swing.JPanel {

    /** Creates new form CategoriesField */
    public CategoriesField() {
        initComponents();
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
        lblSortBy = new javax.swing.JLabel();
        cmbSortBy = new javax.swing.JComboBox();
        searchResult4 = new app.SearchResult();
        lblCurPage = new javax.swing.JLabel();
        searchResult3 = new app.SearchResult();

        lnkNextPage.setText(">>");

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24));
        lblTitle.setText("Categories");

        lnkPrevPage.setText("<<");

        lblSortBy.setText("sort by:");

        cmbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "name ascending", "name descending", "date" }));

        lblCurPage.setText("curr");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                                .addComponent(lblSortBy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle)
                    .addComponent(lblSortBy))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JComboBox cmbSortBy;
    protected javax.swing.JLabel lblCurPage;
    protected javax.swing.JLabel lblSortBy;
    protected javax.swing.JLabel lblTitle;
    protected javax.swing.JLabel lnkNextPage;
    protected javax.swing.JLabel lnkPrevPage;
    protected app.SearchResult searchResult1;
    protected app.SearchResult searchResult2;
    protected app.SearchResult searchResult3;
    protected app.SearchResult searchResult4;
    // End of variables declaration//GEN-END:variables
}
