
package Controlador.ContenedorClases;

import java.awt.Dimension;
import javax.swing.JComponent;

public class BarraTitulo extends javax.swing.JInternalFrame {
    
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null;
    
    public BarraTitulo() {

    }
    
    protected void quitarBarraTitulo() { 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint();
    }
}
