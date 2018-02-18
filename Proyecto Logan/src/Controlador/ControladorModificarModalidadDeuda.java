
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Controlador.ContenedorClases.DatosVerDeudas;
import Modelo.ModeloModificarModalidadDeuda;
import Vista.ModificarModalidadDeuda;
import Vista.Principal;
import Vista.VerDeudas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class ControladorModificarModalidadDeuda implements ActionListener {

    private ModificarModalidadDeuda obj1;
    private ModeloModificarModalidadDeuda obj2 = new ModeloModificarModalidadDeuda();
    private JComboBox cbx1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    
    public ControladorModificarModalidadDeuda(ModificarModalidadDeuda obj1) {
        this.obj1 = obj1;
        this.cbx1 = obj1.cbx1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
    }
    
    public void cargarModalidades() {
        cbx1.addItem("Seleccione");
        
        rss = obj2.consultaModalidad();
        try {
            while (rss.next()){
                cbx1.addItem(rss.getString("descripcion"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }

    public void cargarForm() {
        String id = DatosGestionarClientes.getId();
        String fecini = DatosVerDeudas.getFecini();
        
        rss = obj2.consultaModalidadDeuda(id, fecini);
        try {
            while (rss.next()){
                cbx1.setSelectedItem(rss.getString("mo.descripcion"));
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            VerDeudas obj2 = new VerDeudas();
            ControladorVerDeudas obj2obj1 = new ControladorVerDeudas(obj2);
            obj2obj1.CargarDeudas();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        } else {
            String idmod = null;
            String id = DatosGestionarClientes.getId();
            String fecini = DatosVerDeudas.getFecini();
            
            String des = cbx1.getSelectedItem().toString();
            rss = obj2.consultaIdModalidad(des);
            try {
                while (rss.next()){
                    idmod = rss.getString("id_modalidad");
                }                
            } catch(SQLException error){
                System.out.println("Erroy de MySql"+error);
            } catch(NumberFormatException error){
                System.out.println("se presento el siguiente error "+error.getMessage());
            }
            
            int respuesta = obj2.actualizarModalidadDeuda(idmod, id, fecini);
            
            if (respuesta==0) {
                alerta("Ha ocurrido un error al Modificar la Modalidad de la Deuda, Vuelve a intertarlo");
            } else {
                alerta("Se Modifico la Modalidad de la Deuda con Exito!");
            }
        }
    }
}