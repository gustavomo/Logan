
package Vista;

import Controlador.ControladorPrincipal;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        Image icono = new ImageIcon(getClass().getResource("Azul.png")).getImage();
        this.setIconImage(icono);
        this.setTitle("App Logan");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ControladorPrincipal obj1 = new ControladorPrincipal(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jmi4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N
        setForeground(java.awt.Color.white);
        setResizable(false);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu2.setText("Ingresar");
        jMenu2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem6.setText("Nuevo Cliente");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem7.setText("Nueva Cartera");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem8.setText("Nuevo Cobrador");
        jMenu2.add(jMenuItem8);

        jmi4.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jmi4.setText("Nuevo Pago");
        jMenu2.add(jmi4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Gestionar");
        jMenu3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem3.setText("Gestionar Clientes");
        jMenu3.add(jMenuItem3);

        jMenuItem5.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem5.setText("Gestionar Carteras");
        jMenu3.add(jMenuItem5);

        jMenuItem4.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem4.setText("Gestionar Cobradores");
        jMenu3.add(jMenuItem4);

        jMenuItem10.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem10.setText("Gestionar Moras");
        jMenu3.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem11.setText("Gestionar Intereses");
        jMenu3.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem12.setText("Gestionar Modalidades");
        jMenu3.add(jMenuItem12);

        jMenuItem2.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem2.setText("Gestionar Gastos");
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Reportes");
        jMenu1.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem1.setText("Reporte de Cartera");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Archivo");
        jMenu4.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Gadugi", 0, 16)); // NOI18N
        jMenuItem9.setText("Generar Copia de Seguridad");
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ModerateSkin");
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem10;
    public javax.swing.JMenuItem jMenuItem11;
    public javax.swing.JMenuItem jMenuItem12;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    public javax.swing.JMenuItem jMenuItem7;
    public javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JMenuItem jmi4;
    // End of variables declaration//GEN-END:variables
}