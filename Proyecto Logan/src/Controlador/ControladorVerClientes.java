
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Modelo.ModeloVerClientes;
import Vista.GestionarClientes;
import Vista.Principal;
import Vista.VerClientes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVerClientes implements ActionListener, ItemListener {
    
    private VerClientes obj1;
    private ModeloVerClientes obj2 = new ModeloVerClientes();
    private JComboBox cbx1;
    private JRadioButton jrd1, jrd2;
    private JTable jtb1;
    private JMenuItem jmi1;
    private JButton btn1;
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private Color fondo = new Color(232, 238, 244);

    public ControladorVerClientes(VerClientes obj1) {
        this.obj1 = obj1;
        this.cbx1 = obj1.cbx1;
        this.jrd1 = obj1.jrd1;
        this.jrd2 = obj1.jrd2;
        this.jtb1 = obj1.jtb1;
        this.jmi1 = obj1.jmi1;
        this.btn1 = obj1.btn1;
        
        this.cbx1.addItemListener(this);
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
    
    public void cargarCarteras() {
        
        cbx1.addItem("Seleccione");
        
        rss = obj2.consultaCarteras();
        try {
            while (rss.next()){
                cbx1.addItem("Cartera "+rss.getString("id_cartera"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
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
    
    public void cargarClientes(int valor1, String cartera,String estado) {
        vaciarTabla();
        
        Object[] fila = new Object[6];
        
        if (valor1==1) {
            rss = obj2.consultaClientes(estado);
        } else {
            rss = obj2.consultaClientes(cartera, estado);
        }
        
        try {
            while (rss.next()){
                fila[0] = rss.getString("c.id_cliente");
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
            GestionarClientes obj2 = new GestionarClientes();
            ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
            obj2.jPanel1.setBackground(fondo);
            obj2.jPanel2.setBackground(fondo);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
            else if(e.getSource()==jrd1) {
                String valor1 = null;
                valor1 = cbx1.getSelectedItem().toString();

                if (valor1.equals("Seleccione")) {
                    cargarClientes(1, "", "1");
                } else {
                    valor1 = valor1.replace("Cartera ", "");

                    cargarClientes(0, valor1, "1");
                }
            }
                else if(e.getSource()==jrd2) {
                    String valor1 = null;
                    valor1 = cbx1.getSelectedItem().toString();

                    if (valor1.equals("Seleccione")) {
                        cargarClientes(1, "", "2");
                    } else {
                        valor1 = valor1.replace("Cartera ", "");

                        cargarClientes(0, valor1, "2");
                    }
                }
                    else {
                        if(jtb1.getSelectedRow()!=-1) {
                            int fila = jtb1.getSelectedRow();
                            String id = (String) jtb1.getValueAt(fila, 0);
                            DatosGestionarClientes.setId(id);
                            
                            obj1.setVisible(false);
                            Principal.jDesktopPane1.remove(obj1);
                            GestionarClientes obj2 = new GestionarClientes();
                            ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
                            obj2.jPanel1.setBackground(fondo);
                            obj2.jPanel2.setBackground(fondo);
                            obj2obj1.cargarForm(true);
                            posicinarFrame(Principal.jDesktopPane1, obj2);
                        } else {
                            alerta("No ha seleccionado una fila. Seleccione una fila para poder Cargar los datos del Cliente,\n"
                                  +"Asegurese de dar clic primero sobre la Fila.");
                        }
                    }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String valor1 = null;

        if (cbx1.getItemCount()!=0) {
            valor1 = cbx1.getSelectedItem().toString();
            
            if (valor1.equals("Seleccione")) {
                if (jrd1.isSelected()) {
                    cargarClientes(1, "", "1");
                } else {
                    cargarClientes(1, "", "2");
                }
            } else {
                String valor2 = valor1;
                valor2 = valor2.replace("Cartera ", "");
                
                if (jrd1.isSelected()) {
                    cargarClientes(0, valor2, "1");
                } else {
                    cargarClientes(0, valor2, "2");
                }
            }
        }
    }
}