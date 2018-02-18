
package Vista;

import Controlador.ContenedorClases.BarraTitulo;
import Controlador.ControladorVerGastos;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VerGastos extends BarraTitulo{

    public VerGastos() {
        initComponents();
        ControladorVerGastos obj1 = new ControladorVerGastos(this);
        quitarBarraTitulo();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbx1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        limpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txt17 = new javax.swing.JTextField();
        jdc1 = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        txt18 = new javax.swing.JTextField();
        jdc2 = new com.toedter.calendar.JDateChooser();
        btn2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Historial Gastos");

        jtb1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jtb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Gasto", "Valor Gasto", "Cartera", "Fecha Gasto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtb1.setFocusCycleRoot(true);
        jtb1.setRowSelectionAllowed(false);
        jtb1 = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        jScrollPane1.setViewportView(jtb1);
        if (jtb1.getColumnModel().getColumnCount() > 0) {
            jtb1.getColumnModel().getColumn(0).setResizable(false);
            jtb1.getColumnModel().getColumn(1).setResizable(false);
            jtb1.getColumnModel().getColumn(2).setResizable(false);
            jtb1.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jButton1.setText("Volver");

        jLabel9.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel9.setText("Cartera");

        cbx1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel3.setText("Fecha Inicio");

        jLabel4.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel4.setText("Fecha Final");

        limpiar.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt17.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        txt17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt17ActionPerformed(evt);
            }
        });
        jPanel4.add(txt17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        jdc1.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N
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

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt18.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        txt18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt18MouseExited(evt);
            }
        });
        jPanel5.add(txt18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        jdc2.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N
        jdc2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jdc2MouseMoved(evt);
            }
        });
        jdc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jdc2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jdc2MouseExited(evt);
            }
        });
        jdc2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdc2PropertyChange(evt);
            }
        });
        jPanel5.add(jdc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 142, 31));

        btn2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn2.setText("Modificar");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(limpiar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn2))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limpiar)
                    .addComponent(btn2))
                .addGap(15, 15, 15)
                .addComponent(jButton1)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        
    }//GEN-LAST:event_limpiarActionPerformed

    private void txt17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt17ActionPerformed

    }//GEN-LAST:event_txt17ActionPerformed

    private void jdc1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseMoved
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt17.setText(sdf.format(date));
            txt17.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseMoved

    private void jdc1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseEntered
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt17.setText(sdf.format(date));
            txt17.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseEntered

    private void jdc1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseExited
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt17.setText(sdf.format(date));
            txt17.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseExited

    private void jdc1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdc1PropertyChange
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt17.setText(sdf.format(date));
            txt17.requestFocus();
        }
    }//GEN-LAST:event_jdc1PropertyChange

    private void jdc2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc2MouseEntered
        if (jdc2.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc2.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt18.setText(sdf.format(date));
            txt18.requestFocus();
        }
    }//GEN-LAST:event_jdc2MouseEntered

    private void txt18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt18MouseExited
        
    }//GEN-LAST:event_txt18MouseExited

    private void jdc2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc2MouseExited
        if (jdc2.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc2.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt18.setText(sdf.format(date));
            txt18.requestFocus();
        }
    }//GEN-LAST:event_jdc2MouseExited

    private void jdc2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdc2PropertyChange
        if (jdc2.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc2.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt18.setText(sdf.format(date));
            txt18.requestFocus();
        }
    }//GEN-LAST:event_jdc2PropertyChange

    private void jdc2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc2MouseMoved
        if (jdc2.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc2.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt18.setText(sdf.format(date));
            txt18.requestFocus();
        }
    }//GEN-LAST:event_jdc2MouseMoved

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        
    }//GEN-LAST:event_btn2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn2;
    public javax.swing.JComboBox<String> cbx1;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jdc1;
    public com.toedter.calendar.JDateChooser jdc2;
    public javax.swing.JTable jtb1;
    public javax.swing.JButton limpiar;
    public javax.swing.JTextField txt17;
    public javax.swing.JTextField txt18;
    // End of variables declaration//GEN-END:variables
}
