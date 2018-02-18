
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Modelo.ModeloGestionarClientes;
import Vista.GestionarClientes;
import Vista.ModificarDatos;
import Vista.ModificarDatosCuenta;
import Vista.Principal;
import Vista.RenovarCupo;
import Vista.VerClientes;
import Vista.VerDeudas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorGestionarClientes implements ActionListener, KeyListener {
    
    private GestionarClientes obj1;
    private ModificarDatos obj2 = new ModificarDatos();
    private ControladorModificarDatos obj2obj1 = new ControladorModificarDatos(obj2);
    private ModificarDatosCuenta obj3 = new ModificarDatosCuenta();
    private ControladorModificarDatosCuenta obj3obj1 = new ControladorModificarDatosCuenta(obj3);
    private VerDeudas obj4 = new VerDeudas();
    private ControladorVerDeudas obj4obj1 = new ControladorVerDeudas(obj4);
    private RenovarCupo obj5 = new RenovarCupo();
    private ControladorRenovarCupo obj5obj1 = new ControladorRenovarCupo(obj5);
    private ModeloGestionarClientes obj6 = new ModeloGestionarClientes();
    private VerClientes obj7 = new VerClientes();
    private ControladorVerClientes obj7obj1 = new ControladorVerClientes(obj7);
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10, txt11;
    private JButton btn1, btn3, btn4, btn5, btn6;
    private ResultSet rss = null;
    private Color fondo = new Color(232, 238, 244);
    
    public ControladorGestionarClientes(GestionarClientes obj1) {
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
        this.btn1 = obj1.btn1;
        this.btn3 = obj1.btn3;
        this.btn4 = obj1.btn4;
        this.btn5 =  obj1.btn5;
        this.btn6 =  obj1.btn6;
        
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
        
        this.txt1.addKeyListener(this);
        this.btn1.addActionListener(this);
        this.btn3.addActionListener(this);
        this.btn4.addActionListener(this);
        this.btn5.addActionListener(this);
        this.btn6.addActionListener(this);
        
        cargarForm(true);
    }
    
    public void cargarForm(boolean val) {
        if (val==true) {
            txt1.setText(DatosGestionarClientes.getId());
        }
        String id = txt1.getText();
            
        rss = obj6.consultaCliente(id);
        try {
            boolean ban = false;
            while (rss.next()){
                txt2.setText(rss.getString("nombre"));
                txt3.setText(rss.getString("id_cliente"));
                txt4.setText(rss.getString("barrio"));
                txt5.setText(rss.getString("direccion"));
                txt6.setText(rss.getString("telefono"));
                txt7.setText(rss.getString("nombre_ref"));
                txt8.setText(rss.getString("identificacion_ref"));
                txt9.setText(rss.getString("barrio_ref"));
                txt10.setText(rss.getString("direccion_ref"));
                txt11.setText(rss.getString("telefono_ref"));
                
                ban = true;
            }
                
            if (ban==false) {
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
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
            
        rss = obj6.consultaEstadoCliente(id);
        try {
            boolean ban = false;
            while (rss.next()){
                if (rss.getInt("id_estado")==1) {
                    btn5.setText("Desactivar");
                } else  {
                    btn5.setText("Activar");
                }
                
                ban = true;
            }
                
            if (ban==false) {
                btn5.setText("Estado");
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                DatosGestionarClientes.setId(txt3.getText());
                obj2.jPanel1.setBackground(fondo);
                obj2.jPanel2.setBackground(fondo);
                obj2obj1.cargarForm();
                posicinarFrame(obj2);
            } else {
                alerta("Debe de cargar los Datos de un Cliente antes de ir a Modificar Datos del Cliente");
            }
        }
            else if (e.getSource()==btn3) {
                if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                    DatosGestionarClientes.setId(txt3.getText());
                    obj4obj1.CargarDeudas();
                    posicinarFrame(obj4);
                } else {
                    alerta("Debe de cargar los Datos de un Cliente antes de ir a Ver Deudas");
                }
            }
                else if (e.getSource()==btn4) {
                    if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                        int totpag = 0;
                        
                        rss = obj6.consultaPagos(txt1.getText());
                        try {
                            while (rss.next()){
                                totpag = rss.getInt("total_pagos");
                            }
                        } catch(SQLException error){
                            System.out.println("Erroy de MySql"+error);
                        } catch(NumberFormatException error){
                            System.out.println("se presento el siguiente error "+error.getMessage());
                        }
                        
                        if (totpag!=0) {
                            String idest = null;
                            
                            rss = obj6.consultaEstadoDeuda(txt1.getText());
                            try {
                                while (rss.next()){
                                    idest = rss.getString("id_estado_deuda");
                                }
                            } catch(SQLException error){
                                System.out.println("Erroy de MySql"+error);
                            } catch(NumberFormatException error){
                                System.out.println("se presento el siguiente error "+error.getMessage());
                            }
                            
                            if (idest.equals("1")) {
                                DatosGestionarClientes.setId(txt3.getText());
                                obj5.jPanel1.setBackground(fondo);

                                Calendar calendario = new GregorianCalendar();
                                int dia = (calendario.get(Calendar.DATE));
                                int mes = (calendario.get(Calendar.MONTH))+1;
                                int año = (calendario.get(Calendar.YEAR));

                                String vdia = String.valueOf(dia);
                                if (vdia.length()==1) {
                                    vdia = "0"+dia;
                                } else {
                                    vdia = String.valueOf(dia);
                                }

                                String vmes = String.valueOf(mes);
                                if (vmes.length()==1) {
                                    vmes = "0"+mes;
                                } else {
                                    vmes = String.valueOf(mes);
                                }

                                obj5.cbx1.removeAllItems();
                                obj5.txt5.setText(vdia+"/"+vmes+"/"+año);
                                obj5obj1.cargarModalidades();
                                obj5obj1.cargarTazint();
                                posicinarFrame(obj5);
                            } else {
                                alerta("No puede ir a Renovar Cupo, porque las cuentas del cliente estan Canceladas");
                            }
                        } else {
                            alerta("No puede ir a Renovar Cupo, porque la cuenta no tiene nigun pago\n"
                                  +"la accion que debe de realizar es ir Modificar Datos de la Cuenta");
                        }
                    } else {
                        alerta("Debe de cargar los Datos de un Cliente antes de ir a Renovar Cupo");
                    }
                }
                    else if (e.getSource()==btn5) {
                        if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                            String id = txt3.getText();
                            int respuesta2 = 0;

                            if (btn5.getText().equals("Activar")) {
                                respuesta2 = obj6.actualizarEstadoCliente(id,1);
                            } else {
                                respuesta2 = obj6.actualizarEstadoCliente(id, 2);
                            }

                            if (respuesta2==0) {
                                alerta("Ha ocurrido un error al cambiar el Estado del Cliente, Vuelve a intertarlo");
                            } else {
                                if (btn5.getText().equals("Activar")) {
                                    btn5.setText("Inactivar");
                                    alerta("Se Activo al Cliente Exitosamente!");
                                } else {
                                    btn5.setText("Activar");
                                    alerta("Se Inactivo al Cliente Exitosamente!");
                                }
                            }
                                
                        } else {
                            alerta("Debe de cargar los Datos de un Cliente, antes de poder\n"
                                +"realizar una acción de cambio de Estado");
                        }
                    }
                        else {
                            obj7.cbx1.removeAllItems();
                            obj7obj1.cargarCarteras();
                            obj7obj1.cargarClientes(1, "", "1");
                            obj7.jrd1.setSelected(true);
                            obj7.jrd1.setBackground(fondo);
                            obj7.jrd2.setBackground(fondo);
                            posicinarFrame(obj7);
                        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txt1) {
            cargarForm(false);
        }
    }
}
