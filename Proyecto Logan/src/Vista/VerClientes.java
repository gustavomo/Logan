
package Vista;

import Controlador.ContenedorClases.BarraTitulo;

public class VerClientes extends BarraTitulo {

    public VerClientes() {
        initComponents();
        quitarBarraTitulo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jmi1 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb1 = new javax.swing.JTable();
        jrd1 = new javax.swing.JRadioButton();
        jrd2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbx1 = new javax.swing.JComboBox<>();

        jmi1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi1.setText("Cargar Cliente");
        jPopupMenu1.add(jmi1);

        jtb1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jtb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificacion", "Nombre", "Telefono", "Direccion", "Barrio", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtb1.setComponentPopupMenu(jPopupMenu1);
        jtb1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jtb1);
        if (jtb1.getColumnModel().getColumnCount() > 0) {
            jtb1.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        buttonGroup1.add(jrd1);
        jrd1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jrd1.setText("Activos");

        buttonGroup1.add(jrd2);
        jrd2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jrd2.setText("Inactivos");
        jrd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrd2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ver Clientes");

        btn1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn1.setText("Volver");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setText("Cartera");

        cbx1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jrd1)
                            .addGap(18, 18, 18)
                            .addComponent(jrd2))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrd1)
                    .addComponent(jrd2))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(btn1)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrd2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<String> cbx1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JMenuItem jmi1;
    public javax.swing.JRadioButton jrd1;
    public javax.swing.JRadioButton jrd2;
    public javax.swing.JTable jtb1;
    // End of variables declaration//GEN-END:variables
}
