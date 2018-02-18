
package Vista;

import Controlador.ContenedorClases.BarraTitulo;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RenovarCupo extends BarraTitulo {

    public RenovarCupo() {
        initComponents();
        quitarBarraTitulo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbx1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txt5 = new javax.swing.JTextField();
        jdc1 = new com.toedter.calendar.JDateChooser();
        ccb1 = new javax.swing.JCheckBox();

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Renovar Cupo ");

        btn2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn2.setText("Renovar");

        btn1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn1.setText("Volver");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos credito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gadugi", 0, 12))); // NOI18N

        cbx1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel14.setText("Modalidad");

        jLabel12.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel12.setText("Monto a Prestar");

        jLabel13.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel13.setText("Cuotas");

        txt1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        txt2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel7.setText("Monto Cuota");

        txt4.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel8.setText("Taza de Interés");

        txt3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel9.setText("Fecha de Inicio");

        jLabel10.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel10.setText("Valor Total Deuda");

        txt7.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel11.setText("Valor Interés Deuda");

        txt6.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt5.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jPanel4.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 31));

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

        ccb1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        ccb1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel9)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(ccb1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(ccb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn2)
                    .addComponent(btn1))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jdc1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseMoved
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt5.setText(sdf.format(date));
            txt5.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseMoved

    private void jdc1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseEntered
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt5.setText(sdf.format(date));
            txt5.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseEntered

    private void jdc1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdc1MouseExited
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt5.setText(sdf.format(date));
            txt5.requestFocus();
        }
    }//GEN-LAST:event_jdc1MouseExited

    private void jdc1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdc1PropertyChange
        if (jdc1.getDate()!=null) {
            String formato = "dd/MM/yyyy";
            Date date = jdc1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            txt5.setText(sdf.format(date));
            txt5.requestFocus();
        }
    }//GEN-LAST:event_jdc1PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn2;
    public javax.swing.JComboBox<String> cbx1;
    public javax.swing.JCheckBox ccb1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    public com.toedter.calendar.JDateChooser jdc1;
    public javax.swing.JTextField txt1;
    public javax.swing.JTextField txt2;
    public javax.swing.JTextField txt3;
    public javax.swing.JTextField txt4;
    public javax.swing.JTextField txt5;
    public javax.swing.JTextField txt6;
    public javax.swing.JTextField txt7;
    // End of variables declaration//GEN-END:variables
}
