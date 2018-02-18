
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Controlador.ContenedorClases.DatosVerDeudas;
import Modelo.ModeloVerPagos;
import Vista.GestionarClientes;
import Vista.ModificarUltimoPago;
import Vista.Principal;
import Vista.VerDeudas;
import Vista.VerPagos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVerPagos implements ActionListener {
     
    private VerPagos obj1;
    private ModeloVerPagos obj2 = new ModeloVerPagos();
    private JTable jtb1;
    private JButton btn1, btn2, btn3;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private DecimalFormat formato = new DecimalFormat("###,###.##");

    public ControladorVerPagos(VerPagos obj1) {
        this.obj1 = obj1;
        this.obj1 = obj1;
        this.jtb1 = obj1.jtb1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        this.btn3 = obj1.btn3;
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
        this.btn3.addActionListener(this);
        
        modtab = (DefaultTableModel) jtb1.getModel();
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
    
    private String asignarValor(long valor) {
        String resultado = null;
        resultado = formato.format(valor);
        
        return resultado;
    }
    
    private void vaciarTabla(){
        for (int i = 0; i < jtb1.getRowCount(); i++) {
            if (modtab!=null) {
               modtab.removeRow(i);
               i-=1; 
            }
        }
    }

    public void CargarPagos(String fecini) {
        vaciarTabla();

        Object[] fila = new Object[2];
        
        rss = obj2.consultaPagos(DatosGestionarClientes.getId(), fecini);
        
        try {
            while (rss.next()) {
                long monpag = rss.getLong("monto_pagado");
                String fecha1 = rss.getString("fecha_pago");
                String[] fecha2 = fecha1.split("-");
                
                fila[0] = fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0];
                fila[1] = asignarValor(monpag);
                
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
        if (e.getSource()==btn1) {
            int estadodeuda = 0;
                            
            rss = obj2.consultaIdEstadoDeuda(DatosGestionarClientes.getId(), DatosVerDeudas.getFecini());
            try {
                while (rss.next()) {
                    estadodeuda = rss.getInt("id_estado_deuda");
                }                
            } catch(SQLException error){
                System.out.println("Erroy de MySql"+error);
            } catch(NumberFormatException error){
                System.out.println("se presento el siguiente error "+error.getMessage());
            }
            
            if (estadodeuda==2) {
                alerta("No puede Modificar el Ultimo Pago Registrado,\n"
                      +"Porque la Deuda esta Cancelada");
            } else {
                if (jtb1.getRowCount()!=0) {
                    String monpag = (String) jtb1.getValueAt(jtb1.getRowCount()-1, 1);
                    obj1.setVisible(false);
                    Principal.jDesktopPane1.remove(obj1);
                    ModificarUltimoPago obj3 = new ModificarUltimoPago();
                    ControladorModificarUltimoPago obj3obj1 = new ControladorModificarUltimoPago(obj3);
                    obj3obj1.cargarForm(monpag);
                    posicinarFrame(Principal.jDesktopPane1, obj3);
                } else {
                    alerta("No hay Pagos que Modificar");
                }
            }
        }
            else if (e.getSource()==btn2) {
                obj1.setVisible(false);
                Principal.jDesktopPane1.remove(obj1);
                VerDeudas obj4 = new VerDeudas();
                ControladorVerDeudas obj4obj1 = new ControladorVerDeudas(obj4);
                obj4obj1.CargarDeudas();
                posicinarFrame(Principal.jDesktopPane1, obj4);
            } else {
                obj1.setVisible(false);
                Principal.jDesktopPane1.remove(obj1);
                GestionarClientes obj5 = new GestionarClientes();
                ControladorGestionarClientes obj5obj1 = new ControladorGestionarClientes(obj5);
                obj5.jPanel1.setBackground(fondo);
                obj5.jPanel2.setBackground(fondo);
                posicinarFrame(Principal.jDesktopPane1, obj5);
            }
    }
}