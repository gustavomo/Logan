
package Vista;

import Controlador.ContenedorClases.BarraTitulo;
import java.text.DecimalFormat;

public class GestionarGastos extends BarraTitulo {
    
    public GestionarGastos() {
        initComponents();
        quitarBarraTitulo();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        btn3 = new javax.swing.JButton();
        cbx1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestionar Gastos");

        btn2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn2.setText("Registrar Gasto");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setText("Tipo de Gasto");

        btn1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn1.setText("Ver Gastos");

        txt1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1KeyTyped(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        btn3.setText("Registrar Nuevo Tipo de Gasto");

        cbx1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel3.setText("Valor Gasto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
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
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn2)
                    .addComponent(btn1))
                .addGap(15, 15, 15)
                .addComponent(btn3)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyTyped
        char TipoDeTecla = evt.getKeyChar();
        if (!Character.isDigit(TipoDeTecla)){
            evt.consume();
            
        }

        if (txt1.getText().length()>=16){
            evt.consume();

        }       
        
       
        
    }//GEN-LAST:event_txt1KeyTyped

    private void txt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyPressed
      
    }//GEN-LAST:event_txt1KeyPressed

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
      if (!txt1.getText().equals("")){
          
        DecimalFormat formateador = new DecimalFormat("###,###.###");
        txt1.setText( formateador.format(Double.valueOf(txt1.getText().replace(".", "").replace(",", ""))) );
        
        
        }
    }//GEN-LAST:event_txt1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn2;
    public javax.swing.JButton btn3;
    public javax.swing.JComboBox<String> cbx1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JTextField txt1;
    // End of variables declaration//GEN-END:variables
}
