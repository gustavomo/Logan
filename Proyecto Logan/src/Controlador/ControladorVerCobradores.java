
package Controlador;

import Controlador.ContenedorClases.DatosGestionarCobradores;
import Modelo.ModeloVerCobradores;
import Vista.GestionarCobradores;
import Vista.Principal;
import Vista.VerCobradores;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVerCobradores implements ActionListener {

    private VerCobradores obj1;
    private ModeloVerCobradores obj2 = new ModeloVerCobradores();
    private JRadioButton jrd1, jrd2;
    private JTable jtb1;
    private JMenuItem jmi1;
    private JButton btn1;
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private Color fondo = new Color(232, 238, 244);
    
    public ControladorVerCobradores(VerCobradores obj1) {
        this.obj1 = obj1;
        this.jrd1 = obj1.jrd1;
        this.jrd2 = obj1.jrd2;
        this.jtb1 = obj1.jtb1;
        this.jmi1 = obj1.jmi1;
        this.btn1 = obj1.btn1;
        
        this.jrd1.addActionListener(this);
        this.jrd2.addActionListener(this);
        this.jmi1.addActionListener(this);
        this.btn1.addActionListener(this);
        
        this.modtab = (DefaultTableModel) this.jtb1.getModel();
    }
    
    private void posicinarFrame(JDesktopPane escritorio, JInternalFrame ventana) {
        escritorio.removeAll();
        escritorio.add(ventana);
        ventana.setBackground(fondo);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.show();
    }
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private void vaciarTabla(){
        for (int i = 0; i < jtb1.getRowCount(); i++) {
            if (modtab!=null) {
               modtab.removeRow(i);
               i-=1; 
            }
        }
    }
    
    public void cargarCobradores(int estado) {
        vaciarTabla();
        
        Object[] fila = new Object[6];
        
        rss = obj2.consultaCobradores(estado);
        
        try {
            while (rss.next()){
                fila[0] = rss.getString("c.id_cobrador");
                fila[1] = rss.getString("c.nombre");
                fila[2] = rss.getString("c.telefono");
                fila[3] = rss.getString("c.direccion");
                fila[4] = rss.getString("c.barrio");
                fila[5] = rss.getString("e.descripcion");
                
                modtab.addRow(fila);
                jtb1.setModel(modtab);
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarCobradores obj2 = new GestionarCobradores();
            ControladorGestionarCobradores obj2obj1 = new ControladorGestionarCobradores(obj2);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
            else if(e.getSource()==jrd1) {
                cargarCobradores(1);
            }
                else if(e.getSource()==jrd2) {
                    cargarCobradores(2);
                }
                    else {
                        if(jtb1.getSelectedRow()!=-1) {
                            int fila = jtb1.getSelectedRow();
                            String id = (String) jtb1.getValueAt(fila, 0);
                            DatosGestionarCobradores.setId(id);
                            
                            obj1.setVisible(false);
                            Principal.jDesktopPane1.remove(obj1);
                            GestionarCobradores obj2 = new GestionarCobradores();
                            ControladorGestionarCobradores obj2obj1 = new ControladorGestionarCobradores(obj2);
                            obj2obj1.cargarForm(true);
                            posicinarFrame(Principal.jDesktopPane1, obj2);
                        } else {
                            alerta("No ha seleccionado una fila. Seleccione una fila para poder Cargar los datos del Cobrador,\n"
                                  +"Asegurese de dar clic primero sobre la Fila.");
                        }
                    }
    }
}