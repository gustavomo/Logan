
package Controlador;

import Modelo.ModeloNuevoPago;
import Vista.AgregarAbono;
import Vista.NuevoPago;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ControladorNuevoPago implements ActionListener, KeyListener, ItemListener {
    
    private NuevoPago obj1;
    private ModeloNuevoPago obj2 = new ModeloNuevoPago();
    private AgregarAbono obj3 = new AgregarAbono();
    private JTextField txt1;
    private JComboBox cbx1;
    private JTable jtb1;
    private JMenuItem jmi1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    private static int fila = 0;
    
    public ControladorNuevoPago(NuevoPago obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.cbx1 = obj1.cbx1;
        this.jtb1 = obj1.jtb1;
        this.jmi1 = obj1.jmi1;
        this.btn1 = obj1.btn1;
        
        txt1.addKeyListener(this);
        cbx1.addItemListener(this);
        jmi1.addActionListener(this);
        btn1.addActionListener(this);
        
        modtab = (DefaultTableModel) jtb1.getModel();
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
        obj3.txt1.setText(null);
        obj3.txt1.requestFocus();
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
    
    private void vaciarTabla(){
        for (int i = 0; i < jtb1.getRowCount(); i++) {
            if (modtab!=null) {
               modtab.removeRow(i);
               i-=1; 
            }
        }
    }
    
    public void CargarPagos(int valor1, String valor2) {
        vaciarTabla();
        
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(calendario.DATE);
        int mes = calendario.get(calendario.MONTH)+1;
        int año = calendario.get(calendario.YEAR);
        String fecha = "2017"+"-"+"09"+"-"+"29";
        
        Object[] fila = new Object[5];
        
        if (valor1==1) {
            rss = obj2.consultaPagos(fecha);
        }
        else if(valor1==2) {
            rss = obj2.consultaPagosCliente(fecha, valor2);
        }
        else {
            rss = obj2.consultaPagosCartera(fecha, valor2);
        }
        
        try {
            while (rss.next()){
                int cancuo, cuomor;
                long monpre, monint, monmor, totpag, valpag, totsal;
                
                cancuo = rss.getInt("d.cantidad_cuotas");
                cuomor = rss.getInt("cuotas_mora");
                monpre = rss.getLong("d.monto_prestado");
                monint = rss.getLong("d.monto_interes");
                monmor = rss.getLong("monto_mora");
                totpag = rss.getLong("monto_pagado");
                valpag = (monpre+monint+monmor)/(cancuo+cuomor);
                totsal = (monpre+monint+monmor)-totpag;
                
                if (totsal<valpag) {
                    valpag = totsal;
                }
                
                fila[0] = rss.getString("c.id_cliente");
                fila[1] = rss.getString("c.nombre");
                fila[2] = asignarValor(valpag);
                fila[3] = "";
                fila[4] = false;
                
                modtab.addRow(fila);
                jtb1.setModel(modtab);
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    private Calendar ajustarfecha1(int vala, int dia, int diaa, int mesa, int año,
                                   int diamax, Calendar calendario) {
        diaa = dia+vala;
        if(diaa>diamax) {
            if (mesa>11) {
                calendario.set(calendario.MONTH, 0);
                calendario.set(calendario.YEAR, año+1);
            } else {
                calendario.set(calendario.MONTH, mesa);
            }
            diaa = vala-(diamax-dia);
            calendario.set(calendario.DATE, diaa);
        } else {
            calendario.set(calendario.DATE, diaa);
        }
        
        return calendario;
    }
    
    private Calendar ajustarfecha2(int vala, int diaa1, int diaa2, int mesa, int añoa,
                                   int diamax, Calendar calendario) {
        diaa2 = diaa1+vala;
        if(diaa2>diamax) {
            if (mesa>11) {
                calendario.set(calendario.MONTH, 0);
                calendario.set(calendario.YEAR, añoa);
            } else {
                calendario.set(calendario.MONTH, mesa);
            }
            diaa2 = vala-(diamax-diaa1);
            calendario.set(calendario.DATE, diaa2);
        } else {
            calendario.set(calendario.DATE, diaa2);
        }
        
        return calendario;
    }
    
    private String obtenerProximoPago(String id) {
        int dia = 0, mes = 0, año = 0;
        String propag = null;
                        
        Calendar calendario1 = new GregorianCalendar();
        dia = (calendario1.get(Calendar.DATE));
        mes = (calendario1.get(Calendar.MONTH));
        año = (calendario1.get(Calendar.YEAR));
                        
        Calendar calendario2 = new GregorianCalendar();
        int diamax1 = calendario2.getActualMaximum(Calendar.DAY_OF_MONTH);
                        
        String mol = null;
        rss = obj2.consultaModalidad(id);
        try {
            while (rss.next()){
                mol = rss.getString("descripcion");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
                        
        int diaa1 = 0, mesa1 = 0;
        mesa1 = mes+1;
        int diasemana = (calendario2.get(Calendar.DAY_OF_WEEK));

        if(mol.equals("Diario")) {
            ajustarfecha1(1, dia, diaa1, mesa1, año, diamax1, calendario2);
        }
            else if(mol.equals("Semanal")) {
                ajustarfecha1(8, dia, diaa1, mesa1, año, diamax1, calendario2);
            }
                else if(mol.equals("Quincenal")) {
                    ajustarfecha1(15, dia, diaa1, mesa1, año, diamax1, calendario2);
                }
                    else {
                        diaa1 = dia+30;
                        int diamax2 = 0;
                        if(diaa1>diamax1) {
                            diaa1 = 30-(diamax1-dia);
                            if (mesa1>11) {
                                calendario2.set(calendario2.MONTH, 0);
                                calendario2.set(calendario2.YEAR, año+1);
                            } else {
                                calendario2.set(calendario2.DATE, 1);
                                calendario2.set(calendario2.MONTH, mesa1);
                                diamax2 = calendario2.getActualMaximum(Calendar.DAY_OF_MONTH);
                                
                                if (diaa1>diamax2) {
                                    diaa1 = diaa1-diamax2;
                                    mesa1 = mesa1+1; 
                                    calendario2.set(calendario2.MONTH, mesa1);
                                }
                            }
                            
                            calendario2.set(calendario2.DATE, diaa1);
                        } else {
                            calendario2.set(calendario2.DATE, diaa1);
                        }
                    }

        int diaa2 = 0;
        int mesa2 = calendario2.get(calendario2.MONTH)+1;
        int añoa2 = calendario2.get(calendario2.YEAR)+1;
        int diamax3 = calendario2.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(mol.equals("Diario") && diasemana==6) {
            calendario2 = ajustarfecha2(2, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
        } else if(mol.equals("Diario") && diasemana==7) {
            calendario2 = ajustarfecha2(1, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
        }
            if(mol.equals("Semanal") && diasemana==6) {
                calendario2 = ajustarfecha2(2, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
            } else if(mol.equals("Semanal") && diasemana==7) {
                calendario2 = ajustarfecha2(1, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
            }
                if(mol.equals("Quincenal") && diasemana==6) {
                    calendario2 = ajustarfecha2(2, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
                } else if(mol.equals("Quincenal") && diasemana==7) {
                    calendario2 = ajustarfecha2(1, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
                }
                    if(mol.equals("Mensual") && diasemana==5) {
                        calendario2 = ajustarfecha2(2, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
                    } else if(mol.equals("Mensual") && diasemana==6) {
                        calendario2 = ajustarfecha2(1, calendario2.get(calendario2.DATE), diaa2, mesa2, añoa2, diamax3, calendario2);
                    }
                    
        propag = calendario2.get(calendario2.YEAR)+"-"+
                 (calendario2.get(calendario2.MONTH)+1)+"-"+
                 calendario2.get(calendario2.DATE);
                        
        return propag;
    }
    
    public static int getFila() {
        return fila;
    }

    public static void setFila(int fila) {
        ControladorNuevoPago.fila = fila;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            if (jtb1.getRowCount()!=0) {
                boolean check = false;
                boolean pago = false;
                
                for (int i = 0; i < jtb1.getRowCount(); i++) {
                    pago = (boolean) jtb1.getValueAt(i, 4);
                    
                    if (pago==true) {
                        check  = true;
                        
                        String id = (String) jtb1.getValueAt(i, 0);
                        String iddeu = null;
                        String apagar = (String) jtb1.getValueAt(i, 2);
                        String abono = (String) jtb1.getValueAt(i, 3);
                        long monpag = 0;
                        String propag = null;
                        
                        apagar = apagar.replace(",", "").replace(".", "");
                        abono = abono.replace(",", "").replace(".", "");
                        
                        abono = abono.replace(",", "").replace(".", "");
                        
                        rss = obj2.consultaIdDeuda(id);
                        try {
                            while (rss.next()){
                                iddeu = rss.getString("id_deuda");
                            }                
                        } catch(SQLException error){
                            System.out.println("Erroy de MySql"+error);
                        } catch(NumberFormatException error){
                            System.out.println("se presento el siguiente error "+error.getMessage());
                        }
                        
                        if (abono!=null && !abono.equals("") && !abono.equals(" ")) {
                            monpag = Long.valueOf(abono);
                        } else {
                            monpag = Long.valueOf(apagar);
                        }
                        
                        int cuovie = 0;
                        long monvie = 0;
                        rss = obj2.consultaMovimiento(iddeu);
                        try {
                            while (rss.next()){
                                monvie = rss.getInt("monto_actual");
                                cuovie = rss.getInt("cuota_actual");
                            }                
                        } catch(SQLException error){
                            System.out.println("Erroy de MySql"+error);
                        } catch(NumberFormatException error){
                            System.out.println("se presento el siguiente error "+error.getMessage());
                        }
                        
                        int cuoact = cuovie-1;
                        long monact = 0;
                        if (abono!=null && !abono.equals("") && !abono.equals(" ")) {
                            monact = monvie-Integer.valueOf(abono);
                        } else {
                            monact = monvie-Integer.valueOf(apagar);
                        }
                        
                        if (cuoact<=0) {
                            if (monact<=0) {
                                monact = 0;
                                propag = null;
                            } else {
                                cuoact = 1;
                                propag = obtenerProximoPago(iddeu);
                            }
                        } 
                            else if (monact<=0) {
                                if (cuoact!=0) {
                                    cuoact = 0;
                                    monact = 0;
                                    propag = null;
                                }
                            }
                                else {
                                    propag = obtenerProximoPago(iddeu);
                                }
                        
                        String idmov = "2";
                        
                        int respuesta1 = obj2.registrarPago(monpag , iddeu);
                        int respuesta2 = obj2.registrarMovimiento(cuovie, cuoact, monvie, monact, propag, idmov, iddeu);
                        
                        if (monact<=0) {
                            obj2.actualizarDeuda(iddeu);
                        }
                        
                        boolean val = false; 
                        if (respuesta1==0) {
                            val = true;
                        } 
                            else if (respuesta2==0) {
                                val = true;
                            }
                        
                        if (val==true) {
                            alerta("Ha ocurrido un error al registar un nuevo Pago, Vuelve a intertarlo");
                        } else {
                            vaciarTabla();
                            CargarPagos(1 , "");
                            txt1.setText("");
                            cbx1.setSelectedItem("Seleccione");
                            alerta("Registro Exitoso!");
                        }
                    }
                }
                if (check==false) {
                    alerta("No ha checkeado ningun Pago, Por favor vuelve a intertarlo");
                }
            }
        } else {
            if(jtb1.getSelectedRow()!=-1) {
                posicinarFrame(obj3);
                fila = jtb1.getSelectedRow();
                ControladorAgregarAbono.setModtab(modtab);
            } else {
                alerta("No ha seleccionado una fila. Seleccione una fila para poder Agrebar un Abono,\n"
                      +"Asegurese de dar clic primero sobre la Fila.");
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource()==txt1) {
            if (txt1.getText().length()==32) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txt1) {
            String id = txt1.getText();
            
            cbx1.setSelectedItem("Seleccione");
            vaciarTabla();

            CargarPagos(2, id);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String valor1 = null;

        if (cbx1.getItemCount()!=0) {
            valor1 = cbx1.getSelectedItem().toString();
            
            if (valor1.equals("Seleccione")) {
                if (txt1.getText().isEmpty() && txt1.getText().equals("")) {
                    CargarPagos(1, "");
                }
            } else {
                vaciarTabla();
                txt1.setText("");

                String valor2 = cbx1.getSelectedItem().toString();
                valor2 = valor2.replace("Cartera ", "");       

                CargarPagos(3, valor2);
            }
        }
    }
}