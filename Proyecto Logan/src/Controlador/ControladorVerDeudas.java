
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Controlador.ContenedorClases.DatosVerDeudas;
import Modelo.ModeloVerDeudas;
import Vista.GestionarClientes;
import Vista.ModificarDatosCuenta;
import Vista.ModificarModalidadDeuda;
import Vista.NuevaDeuda;
import Vista.VerMovimientosDeuda;
import Vista.Principal;
import Vista.VerDeudas;
import Vista.VerObservacion;
import Vista.VerPagos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVerDeudas implements ActionListener {
    
    private VerDeudas obj1;
    private ModeloVerDeudas obj2 = new ModeloVerDeudas();
    private JTable jtb1;
    private JMenuItem jmi1, jmi2, jmi3, jmi4, jmi5;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private DecimalFormat formato = new DecimalFormat("###,###.##");

    public ControladorVerDeudas(VerDeudas obj1) {
        this.obj1 = obj1;
        this.jtb1 = obj1.jtb1;
        this.jmi1 = obj1.jmi1;
        this.jmi2 = obj1.jmi2;
        this.jmi3 = obj1.jmi3;
        this.jmi4 = obj1.jmi4;
        this.jmi5 = obj1.jmi5;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.jmi1.addActionListener(this);
        this.jmi2.addActionListener(this);
        this.jmi3.addActionListener(this);
        this.jmi4.addActionListener(this);
        this.jmi5.addActionListener(this);
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
        
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
    
    public void CargarDeudas() {
        vaciarTabla();

        Object[] fila = new Object[16];
        
        rss = obj2.consultaDeudas(DatosGestionarClientes.getId());
        try {
            while (rss.next()) {
                int cancuo, cuomor, cuopag, cuopen;
                long monpre, monint, monmor, valpag, totpre, totdeu, totpag, totsal;
                String fecha3 = rss.getString("d.fecha_solicitud");
                String[] fecha4 = fecha3.split("-");
                String fecha1 = rss.getString("d.fecha_inicio");
                String[] fecha2 = fecha1.split("-");
                
                cancuo = rss.getInt("d.cantidad_cuotas");
                cuomor = rss.getInt("cuotas_mora");
                cuopag = rss.getInt("cuotas_pagas");
                cuopen = rss.getInt("cuotas_pendientes");
                monpre = rss.getLong("d.monto_prestado");
                monint = rss.getLong("d.monto_interes");
                monmor = rss.getLong("monto_mora");
                valpag = (monpre+monint+monmor)/(cancuo+cuomor);
                totpre = monpre+monint;
                totdeu = monpre+monint+monmor;
                totpag = rss.getLong("monto_pagado");
                totsal = (monpre+monint+monmor)-totpag;
                
                fila[0] = fecha4[2]+"/"+fecha4[1]+"/"+fecha4[0];
                fila[1] = fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0];
                fila[2] = rss.getString("mo.descripcion");
                fila[3] = asignarValor(cancuo);
                fila[4] = cuomor;
                fila[5] = cuopag;
                fila[6] = cuopen;
                fila[7] = asignarValor(monpre);
                fila[8] = asignarValor(monint);
                fila[9] = asignarValor(monmor);
                fila[10] = asignarValor(valpag);
                fila[11] = asignarValor(totpre);
                fila[12] = asignarValor(totdeu);
                fila[13] = asignarValor(totpag);
                fila[14] = asignarValor(totsal);
                fila[15] = rss.getString("e.descripcion");;
                
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
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarClientes obj2 = new GestionarClientes();
            ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
            obj2.jPanel1.setBackground(fondo);
            obj2.jPanel2.setBackground(fondo);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
            else if (e.getSource()==btn2) {
                boolean flag = false;
                
                for (int i = 0; i < jtb1.getRowCount(); i++) {
                    String estado =(String) jtb1.getValueAt(i, 14);
                    
                    if (estado.equals("En deuda")) {
                        flag = true;
                    }
                }
                
                if (flag==false) {
                    obj1.setVisible(false);
                    Principal.jDesktopPane1.remove(obj1);
                    NuevaDeuda obj2 = new NuevaDeuda();
                    ControladorNuevaDeuda obj2obj1 = new ControladorNuevaDeuda(obj2);
                    
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
                    
                    obj2.txt5.setText(vdia+"/"+vmes+"/"+año);
                    obj2.jPanel1.setBackground(fondo);
                    obj2obj1.cargarModalidades();
                    obj2obj1.cargarTazint();
                    posicinarFrame(Principal.jDesktopPane1, obj2);
                } else {
                    alerta("No puede ir a Nueva Deuda,\n"
                          +"Porque todavía hay una cuenta en Deuda");
                }
            }
                else if (e.getSource()==jmi1) {
                    if(jtb1.getSelectedRow()!=-1) {
                        int fila = jtb1.getSelectedRow();
                        fila = jtb1.getSelectedRow();
                        String fecha1 = (String) jtb1.getValueAt(fila, 0);
                        String[] fecha2 = fecha1.split("/");

                        obj1.setVisible(false);
                        Principal.jDesktopPane1.remove(obj1);
                        VerMovimientosDeuda obj3 = new VerMovimientosDeuda();
                        ControladorVerMovimientosDeuda obj3obj1 = new ControladorVerMovimientosDeuda(obj3);
                        obj3obj1.CargarMovimientos(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);
                        posicinarFrame(Principal.jDesktopPane1, obj3);
                    } else {
                        alerta("No ha seleccionado una fila. Seleccione una fila para ir a Movimientos de la Deuda,\n"
                              +"Asegurese de dar clic primero sobre la Fila.");
                    }
                }
                    else if (e.getSource()==jmi2) {
                        if(jtb1.getSelectedRow()!=-1) {
                            int fila = jtb1.getSelectedRow();
                            fila = jtb1.getSelectedRow();
                            String fecha1 = (String) jtb1.getValueAt(fila, 0);
                            String[] fecha2 = fecha1.split("/");
                            String estado = (String) jtb1.getValueAt(fila, 14);

                            if (estado.equals("Cancelada")) {
                                alerta("No puede Modificar los Datos de la Cuenta,\n"
                                      +"Porque la Cuenta esta Cancelada");
                            } else {
                                int pagos = 0;

                                rss = obj2.consultaPagos(DatosGestionarClientes.getId(), fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);
                                try {
                                    while (rss.next()) {
                                        pagos = rss.getInt("pagos");
                                    }                
                                } catch(SQLException error){
                                    System.out.println("Erroy de MySql"+error);
                                } catch(NumberFormatException error){
                                    System.out.println("se presento el siguiente error "+error.getMessage());
                                }

                                if (pagos!=0) {
                                    alerta("No puede Modificar los Datos de la Cuenta,\n"
                                          +"Porque ya ha realizado un primer Pago");
                                } else {
                                    obj1.setVisible(false);
                                    Principal.jDesktopPane1.remove(obj1);
                                    ModificarDatosCuenta obj4 = new ModificarDatosCuenta();
                                    ControladorModificarDatosCuenta obj4obj1 = new ControladorModificarDatosCuenta(obj4);
                                    obj4.jPanel1.setBackground(fondo);
                                    obj4obj1.cargarModalidades();
                                    obj4obj1.cargarTazint();
                                    obj4obj1.cargarForm(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);
                                    posicinarFrame(Principal.jDesktopPane1, obj4);
                                }
                            }
                        } else {
                            alerta("No ha seleccionado una fila. Seleccione una fila para poder Modificar Datos de la Cuenta,\n"
                                  +"Asegurese de dar clic primero sobre la Fila.");
                        }
                    }
                        else if(e.getSource()==jmi3) {
                            if(jtb1.getSelectedRow()!=-1) {
                                int fila = jtb1.getSelectedRow();
                                fila = jtb1.getSelectedRow();
                                String fecha1 = (String) jtb1.getValueAt(fila, 0);
                                String[] fecha2 = fecha1.split("/");
                                DatosVerDeudas.setFecini(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);

                                obj1.setVisible(false);
                                Principal.jDesktopPane1.remove(obj1);
                                VerPagos obj4 = new VerPagos();
                                ControladorVerPagos obj4obj1 = new ControladorVerPagos(obj4);
                                obj4obj1.CargarPagos(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);
                                posicinarFrame(Principal.jDesktopPane1, obj4);
                            } else {
                                alerta("No ha seleccionado una fila. Seleccione una fila para ir a Pagos,\n"
                                      +"Asegurese de dar clic primero sobre la Fila.");
                            }
                        }  
                            else if(e.getSource()==jmi4) {
                                if(jtb1.getSelectedRow()!=-1) {
                                    int fila = jtb1.getSelectedRow();
                                    fila = jtb1.getSelectedRow();
                                    String fecha1 = (String) jtb1.getValueAt(fila, 0);
                                    String[] fecha2 = fecha1.split("/");
                                    DatosVerDeudas.setFecini(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);

                                    obj1.setVisible(false);
                                    Principal.jDesktopPane1.remove(obj1);
                                    ModificarModalidadDeuda obj3 = new ModificarModalidadDeuda();
                                    ControladorModificarModalidadDeuda obj3obj1 = new ControladorModificarModalidadDeuda(obj3);
                                    obj3obj1.cargarModalidades();
                                    obj3obj1.cargarForm();
                                    posicinarFrame(Principal.jDesktopPane1, obj3);
                                } else {
                                    alerta("No ha seleccionado una fila. Seleccione una fila para ir a Modificar Modalidad,\n"
                                          +"Asegurese de dar clic primero sobre la Fila.");
                                }
                            }
                                else {
                                    if(jtb1.getSelectedRow()!=-1) {
                                        int fila = jtb1.getSelectedRow();
                                        fila = jtb1.getSelectedRow();
                                        String fecha1 = (String) jtb1.getValueAt(fila, 0);
                                        String[] fecha2 = fecha1.split("/");
                                        
                                        String obs = null;
                                        rss = obj2.consultaObservacion(DatosGestionarClientes.getId(), fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);
        
                                        try {
                                            while (rss.next()) {
                                                obs = rss.getString("d.observacion");
                                            }                
                                        } catch(SQLException error){
                                            System.out.println("Erroy de MySql"+error);
                                        } catch(NumberFormatException error){
                                            System.out.println("se presento el siguiente error "+error.getMessage());
                                        }
                                        
                                        if (obs!=null && !obs.isEmpty() && !obs.equals("")) {
                                            DatosVerDeudas.setFecini(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);

                                            obj1.setVisible(false);
                                            Principal.jDesktopPane1.remove(obj1);
                                            VerObservacion obj4 = new VerObservacion();
                                            ControladorVerObservacion obj4obj1 = new ControladorVerObservacion(obj4);
                                            obj4obj1.CargarObservacion(fecha2[2]+"-"+fecha2[1]+"-"+fecha2[0]);
                                            posicinarFrame(Principal.jDesktopPane1, obj4);
                                        } else {
                                            alerta("La Deuda no tiene una Observación");
                                        }
                                    } else {
                                        alerta("No ha seleccionado una fila. Seleccione una fila para ir a Obserbación,\n"
                                              +"Asegurese de dar clic primero sobre la Fila.");
                                    }
                                }
    }
}