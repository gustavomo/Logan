
package Vista;

import Controlador.ContenedorClases.BarraTitulo;

public  class VerDeudas extends BarraTitulo {

    public VerDeudas() {
        initComponents();
        quitarBarraTitulo();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jmi1 = new javax.swing.JMenuItem();
        jmi2 = new javax.swing.JMenuItem();
        jmi3 = new javax.swing.JMenuItem();
        jmi4 = new javax.swing.JMenuItem();
        jmi5 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb1 = new javax.swing.JTable();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();

        jPopupMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jmi1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi1.setText("Ver movimientos");
        jPopupMenu1.add(jmi1);

        jmi2.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi2.setText("Modificar Datos de la Deuda");
        jPopupMenu1.add(jmi2);

        jmi3.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi3.setText("Ver Pagos");
        jPopupMenu1.add(jmi3);

        jmi4.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi4.setText("Modificar Modalidad");
        jPopupMenu1.add(jmi4);

        jmi5.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi5.setText("Observación");
        jPopupMenu1.add(jmi5);

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Deudas Cliente");

        jtb1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jtb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha de Solicitud", "Fecha de Inicio", "Modalidad", "N° de Cuotas", "Coutas Mora", "Cuotas Pagadas", "Coutas Pendientes", "Monto Prestado", "Monto Interés", "Monto Mora", "Valor Couta", "Total Préstamo", "Total Deuda", "Total Pagado", "Total Saldo", "Estado Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtb1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jtb1);

        btn1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn1.setText("Volver");

        btn2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn2.setText("Nueva Deuda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1237, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1)
                    .addComponent(btn2))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JMenuItem jmi1;
    public javax.swing.JMenuItem jmi2;
    public javax.swing.JMenuItem jmi3;
    public javax.swing.JMenuItem jmi4;
    public javax.swing.JMenuItem jmi5;
    public javax.swing.JTable jtb1;
    // End of variables declaration//GEN-END:variables
}
