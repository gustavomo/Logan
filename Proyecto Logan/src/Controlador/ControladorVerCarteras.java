
package Controlador;

import Controlador.ContenedorClases.DatosGestionarCarteras;
import Modelo.ModeloVerCarteras;
import Vista.GestionarCarteras;
import Vista.Principal;
import Vista.VerCarteras;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVerCarteras implements ActionListener {

    private VerCarteras obj1;
    private ModeloVerCarteras obj2 = new ModeloVerCarteras();
    private JTable jtb1;
    private JMenuItem jmi1;
    private JButton btn1;
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private Color fondo = new Color(232, 238, 244);
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    
    public ControladorVerCarteras(VerCarteras obj1) {
        this.obj1 = obj1;
        this.jtb1 = obj1.jtb1;
        this.jmi1 = obj1.jmi1;
        this.btn1 = obj1.btn1;
        
        this.jmi1.addActionListener(this);
        this.btn1.addActionListener(this);
        
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
    
    private void vaciarTabla(){
        for (int i = 0; i < jtb1.getRowCount(); i++) {
            if (modtab!=null) {
               modtab.removeRow(i);
               i-=1; 
            }
        }
    }
    
    private String asignarValor(long valor) {
        String resultado = null;
        resultado = formato.format(valor);
        
        return resultado;
    }
    
    public void cargarCarteras() {
        vaciarTabla();
        
        Object[] fila = new Object[7];
        
        rss = obj2.consultaDatosCarteras();
        try {
            while (rss.next()){
                String id = rss.getString("ca.id_cartera");
                String fecha1 = rss.getString("ca.fecha_inicio");
                String[] fecha2 = fecha1.split("-");
                long monmax = rss.getLong("ca.monto_maximo");
                long capini = rss.getLong("ca.capital_inicial");
                long totpag = rss.getLong("total_pagado");
                long totdeucar = rss.getLong("total_deuda_cartera");
                long totgas = rss.getLong("total_gastos");
                
                fila[0] = "Cartera "+id;
                fila[1] = asignarValor(monmax);
                fila[2] = asignarValor(capini);
                fila[3] = rss.getString("co.nombre");
                fila[4] = rss.getString("total_clientes");
                fila[5] = fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0];
                fila[6] = asignarValor((capini+totpag)-(totdeucar+totgas));

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
            GestionarCarteras obj2 = new GestionarCarteras();
            ControladorGestionarCarteras obj2obj1 = new ControladorGestionarCarteras(obj2);
            posicinarFrame(Principal.jDesktopPane1, obj2);
            obj2obj1.cargarCarteras();
            obj2.cbx1.setSelectedItem(DatosGestionarCarteras.getCartera());
        } else {
            if(jtb1.getSelectedRow()!=-1) {
                int fila = jtb1.getSelectedRow();
                            String cartera = (String) jtb1.getValueAt(fila, 0);
                            DatosGestionarCarteras.setCartera(cartera);
                            
                            obj1.setVisible(false);
                            Principal.jDesktopPane1.remove(obj1);
                            GestionarCarteras obj2 = new GestionarCarteras();
                            ControladorGestionarCarteras obj2obj1 = new ControladorGestionarCarteras(obj2);
                            obj2obj1.cargarCarteras();
                            obj2.cbx1.setSelectedItem(DatosGestionarCarteras.getCartera());
                            posicinarFrame(Principal.jDesktopPane1, obj2);
            } else {
                alerta("No ha seleccionado una fila. Seleccione una fila para poder Cargar los datos de la Cartera,\n"
                      +"Asegurese de dar clic primero sobre la Fila.");
            }
        }
    }
}