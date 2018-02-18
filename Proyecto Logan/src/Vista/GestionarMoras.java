
package Vista;

import Controlador.ContenedorClases.BarraTitulo;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionarMoras extends BarraTitulo {
    
    public GestionarMoras() {
        initComponents();
        quitarBarraTitulo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jmi1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cbx1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txt2 = new javax.swing.JTextField();
        jdc1 = new com.toedter.calendar.JDateChooser();
        btn1 = new javax.swing.JButton();

        jmi1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jmi1.setText("Cancelar Mora");
        jPopupMenu1.add(jmi1);

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestionar Moras");

        btn2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn2.setText("Cambiar Estado");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setText("N° Identificación");

        txt1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jtb1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jtb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Identificación", "Cliente", "Fecha de Mora", "Estado", "Cambio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtb1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jtb1);
        if (jtb1.getColumnModel().getColumnCount() > 0) {
            jtb1.getColumnModel().getColumn(1).setPreferredWidth(250);
        }

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel3.setText("Cartera");

        cbx1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel9.setText("Fecha de Inicio");

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jPanel4.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 31));

        jdc1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jdc1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jdc1MouseMoved(evt);
            }
        });
        jdc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jdc1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jdc1MouseExited(evt);
            }
        });
        jdc1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdc1PropertyChange(evt);
            }
        });
        jPanel4.add(jdc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 142, 31));

        btn1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn1.setText("Seleccionar Todos");

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
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addGap(10, 10, 10)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn1))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btn2)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jdc1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseMoved
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt2.setText(sdf.format(date));
            txt2.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseMoved

    private void jdc1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseEntered
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt2.setText(sdf.format(date));
            txt2.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseEntered

    private void jdc1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseExited
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt2.setText(sdf.format(date));
            txt2.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseExited

    private void jdc1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdc1PropertyChange
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt2.setText(sdf.format(date));
            txt2.requestFocus();
        }
    }//GEN-LAST:event_jdc1PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn2;
    public javax.swing.JComboBox<String> cbx1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jdc1;
    public javax.swing.JMenuItem jmi1;
    public javax.swing.JTable jtb1;
    public javax.swing.JTextField txt1;
    public javax.swing.JTextField txt2;
    // End of variables declaration//GEN-END:variables
}
