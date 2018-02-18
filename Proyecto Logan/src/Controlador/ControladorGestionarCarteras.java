
package Controlador;

import Controlador.ContenedorClases.DatosGestionarCarteras;
import Modelo.ModeloGestionarCarteras;
import Vista.GestionarCarteras;
import Vista.ModificarCartera;
import Vista.Principal;
import Vista.VerCarteras;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorGestionarCarteras implements ActionListener, ItemListener {
    
    private GestionarCarteras obj1;
    private ModeloGestionarCarteras obj2 = new ModeloGestionarCarteras();
    private ModificarCartera obj3 = new ModificarCartera();
    private ControladorModificarCartera obj3obj1 = new ControladorModificarCartera(obj3);
    private VerCarteras obj4 = new VerCarteras();
    private ControladorVerCarteras obj4obj1 = new ControladorVerCarteras(obj4);
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10, txt11, txt12, txt13, txt14, txt15, txt16;
    private JComboBox cbx1;
    private JButton btn1, btn2;
    private ResultSet rss = null;
    private Color fondo = new Color(232, 238, 244);
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    
    public ControladorGestionarCarteras(GestionarCarteras obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.txt3 = obj1.txt3;
        this.txt4 = obj1.txt4;
        this.txt5 = obj1.txt5;
        this.txt6 = obj1.txt6;
        this.txt7 = obj1.txt7;
        this.txt8 = obj1.txt8;
        this.txt9 = obj1.txt9;
        this.txt10 = obj1.txt10;
        this.txt11 = obj1.txt11;
        this.txt12 = obj1.txt12;
        this.txt13 = obj1.txt13;
        this.txt14 = obj1.txt14;
        this.txt15 = obj1.txt15;
        this.txt16 = obj1.txt16;
        this.cbx1 = obj1.cbx1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.txt1.setEditable(false);
        this.txt2.setEditable(false);
        this.txt3.setEditable(false);
        this.txt4.setEditable(false);
        this.txt5.setEditable(false);
        this.txt6.setEditable(false);
        this.txt7.setEditable(false);
        this.txt8.setEditable(false);
        this.txt9.setEditable(false);
        this.txt10.setEditable(false);
        this.txt11.setEditable(false);
        this.txt12.setEditable(false);
        this.txt13.setEditable(false);
        this.txt14.setEditable(false);
        this.txt15.setEditable(false);
        this.txt16.setEditable(false);
        
        this.cbx1.addItemListener(this);
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
    }
    
    private void posicinarFrame(JInternalFrame ventana) {
        Principal.jDesktopPane1.remove(ventana);
        Principal.jDesktopPane1.add(ventana);
        Dimension desktopSize = Principal.jDesktopPane1.getSize();
        ventana.setBackground(fondo);
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.toFront();
        ventana.setVisible(true);
        obj1.setVisible(false);
    }
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
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
    
    private String asignarValor(long valor) {
        String resultado = null;
        resultado = formato.format(valor);
        
        return resultado;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {
            if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                String valor1= cbx1.getSelectedItem().toString();
                DatosGestionarCarteras.setCartera(valor1);
                String valor2 = valor1.replace("Cartera ", "");
                DatosGestionarCarteras.setId(valor2);
                posicinarFrame(obj3);
                obj3obj1.cargarForm();
                obj3obj1.cargarCobradores();
                obj3.cbx1.setSelectedItem(txt3.getText());
            } else {
                alerta("Debe de cargar los Datos de una Cartera antes de ir a Modificar Datos Cartera");
            }
        } else {
            obj4obj1.cargarCarteras();
            posicinarFrame(obj4);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String valor1 = null;

        if (cbx1.getItemCount()!=0) {
            valor1 = cbx1.getSelectedItem().toString();
            
            if (valor1.equals("Seleccione")) {
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                txt5.setText("");
                txt6.setText("");
                txt7.setText("");
                txt8.setText("");
                txt9.setText("");
                txt10.setText("");
                txt11.setText("");
                txt12.setText("");
                txt13.setText("");
                txt14.setText("");
                txt15.setText("");
                txt16.setText("");
            } else {
                String valor2 = valor1;
                valor2 = valor2.replace("Cartera ", "");

                rss = obj2.consultaDatosCarteras(valor2);
                try {
                    while (rss.next()){
                        long montmax = rss.getLong("ca.monto_maximo");
                        long capini = rss.getLong("ca.capital_inicial");
                        String fecha1 = rss.getString("ca.fecha_inicio");
                        String[] fecha2 = fecha1.split("-");
                        int totrencup = rss.getInt("total_renovacion_cupos");
                        long totpre = rss.getLong("total_prestado");
                        long totpag = rss.getLong("total_pagado");
                        long totmor = rss.getLong("total_moras");
                        long totdeucar = rss.getLong("total_deuda_cartera");
                        long totgas = rss.getLong("total_gastos");
                        int totdia = rss.getInt("total_diarios");
                        int totsem = rss.getInt("total_semanales");
                        int totqui = rss.getInt("total_quincenales");
                        int totmen = rss.getInt("total_mensuales");
                        
                        txt1.setText(asignarValor(montmax));
                        txt2.setText(asignarValor(totdia));
                        txt3.setText(rss.getString("co.nombre"));
                        txt4.setText(asignarValor(totsem));
                        txt5.setText(rss.getString("total_clientes"));
                        txt6.setText(String.valueOf(totrencup));
                        txt7.setText(asignarValor(totqui));
                        txt8.setText(asignarValor(capini));
                        txt9.setText(asignarValor(totpre));
                        txt10.setText(asignarValor((capini+totpag)-(totpre+totgas)));
                        txt11.setText(asignarValor(totpag));
                        txt12.setText(fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0]);
                        txt13.setText(asignarValor(totmen));
                        txt14.setText(asignarValor(totmor));
                        txt15.setText(asignarValor((totdeucar+totmor)-totpag));
                        txt16.setText(asignarValor(totgas));
                    }                
                } catch(SQLException error){
                    System.out.println("Erroy de MySql"+error);
                } catch(NumberFormatException error){
                    System.out.println("se presento el siguiente error "+error.getMessage());
                }
            }
        }
    }
}
