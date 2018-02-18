
package Vista;

import Controlador.ContenedorClases.BarraTitulo;
import java.text.DecimalFormat;

public class ModificarGasto extends BarraTitulo {
    
    public ModificarGasto() {
        initComponents();
        quitarBarraTitulo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbx2 = new javax.swing.JComboBox<>();
        btn1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        btn2 = new javax.swing.JButton();
        cbx1 = new javax.swing.JComboBox<>();

        cbx2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        btn1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn1.setText("Guardar Cambios");

        jLabel9.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Modificar Gasto");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setText("Valor Gasto");

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel3.setText("Tipo gasto");

        jLabel7.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel7.setText("Cartera");

        txt1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1KeyTyped(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn2.setText("Cancelar");

        cbx1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(cbx2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt1)
                            .addComponent(cbx1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1)
                    .addComponent(btn2))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        if (!txt1.getText().equals("")){
            DecimalFormat formateador = new DecimalFormat("###,###.###");
            txt1.setText( formateador.format(Double.valueOf(txt1.getText().replace(".", "").replace(",", ""))) );
        }
    }//GEN-LAST:event_txt1KeyReleased

    private void txt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyTyped
        char TipoDeTecla = evt.getKeyChar();
        if (!Character.isDigit(TipoDeTecla)){
            evt.consume();
        }

        if (txt1.getText().length()>=16){
            evt.consume();
        }       
    }//GEN-LAST:event_txt1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn2;
    public javax.swing.JComboBox<String> cbx1;
    public javax.swing.JComboBox<String> cbx2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JTextField txt1;
    // End of variables declaration//GEN-END:variables
}
