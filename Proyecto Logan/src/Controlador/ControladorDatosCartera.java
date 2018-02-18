
package Controlador;

import Controlador.ContenedorClases.DatosNuevoCliente;
import Modelo.ModeloDatosCartera;
import Modelo.ModeloGestionarGastos;
import Vista.DatosCartera;
import Vista.GestionarGastos;
import Vista.NuevoCliente;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorDatosCartera implements ActionListener, ItemListener {
    
    private DatosCartera obj1;
    private ModeloDatosCartera obj2 = new ModeloDatosCartera();
    private JTextField txt1, txt2, txt3, txt4, txt5;
    private JComboBox cbx1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private int recibir = 0;
    private ResultSet rss = null;
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    private String valor,tipo;
    private ModeloGestionarGastos modelo = new ModeloGestionarGastos();

    public ControladorDatosCartera(DatosCartera obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.txt3 = obj1.txt3;
        this.txt4 = obj1.txt4;
        this.txt5 = obj1.txt5;
        this.cbx1 = obj1.cbx1;
        this.btn1 = obj1.jButton1;
        this.btn2 = obj1.jButton2;
        
        this.txt1.setEditable(false);
        this.txt2.setEditable(false);
        this.txt3.setEditable(false);
        this.txt4.setEditable(false);
        this.txt5.setEditable(false);
        
        this.cbx1.addItemListener(this);
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
    }
    
    // Funciones para obtener valor y tipo de gasto delcontrolador gestionar gastos
    public void setValor(String valor){
        this.valor=valor;
    }
    
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    ///////////////////////////////////////////
    
    private void posicinarFrame(JDesktopPane escritorio, JInternalFrame ventana) {
        escritorio.removeAll();
        escritorio.add(ventana);
        ventana.setBackground(fondo);
        NuevoCliente.jPanel1.setBackground(fondo);
        NuevoCliente.jPanel2.setBackground(fondo);
        NuevoCliente.jPanel3.setBackground(fondo);
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
    
    private int insertarCliente() {
        String id, nom, tel, dir, bar, idref, nomref, telref, dirref, barref, est;
        id = DatosNuevoCliente.getId();
        nom = DatosNuevoCliente.getNom();
        tel = DatosNuevoCliente.getTel();
        dir = DatosNuevoCliente.getDir();
        bar = DatosNuevoCliente.getBar();
        idref = DatosNuevoCliente.getIdref();
        nomref = DatosNuevoCliente.getNomref();
        telref = DatosNuevoCliente.getTelref();
        dirref = DatosNuevoCliente.getDirref();
        barref = DatosNuevoCliente.getBarref();
        est = "1";
                    
        String valor1 = cbx1.getSelectedItem().toString();
        char[] valor2 = valor1.toCharArray();
        String valor3 = "";
        for (int i = 0; i < valor2.length; i++) {
            if (i>7) {
                valor3 = valor3+valor2[i];
            }
        }
        int car = Integer.parseInt(valor3);
        
        int respuesta = obj2.registrarCliente(id, nom, tel, dir, bar,
                                             idref, nomref, telref,
                                             dirref, barref, est, car);
        
        return respuesta;
    }
    
    private int insertarDeuda() {
        long monpre, monint;
        String fecini = null, idmol = null, idestcue, idcli;
        monpre = DatosNuevoCliente.getMonpre();
        monint = DatosNuevoCliente.getMonint();
        int cancou = DatosNuevoCliente.getCancuo();
        
        String des = DatosNuevoCliente.getMol();
        rss = obj2.consultaIdModalidad(des);
        try {
            while (rss.next()){
                idmol = rss.getString("id_modalidad");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        if (DatosNuevoCliente.getDia()!=0) {
            int dia = 0, mes = 0, año = 0;
            dia = DatosNuevoCliente.getDia();
            mes = DatosNuevoCliente.getMes();
            año = DatosNuevoCliente.getAño();
            
            fecini = año+"-"+mes+"-"+dia;
        } else {
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
            
            fecini = año+"-"+mes+"-"+dia;
        }
        
        idestcue = "1";
        idcli = DatosNuevoCliente.getId();
        
        int respuesta = obj2.registrarDueda(monpre, monint, cancou, fecini, idmol,
                                            idestcue, idcli);
        
        return respuesta;
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
    
    private int insertarMovimiento() {
        int cuovie, cuoact;
        long monvie, monact;
        String propag = null, idmov, iddeu = null;
        
        cuovie = 0;
        cuoact = (int) DatosNuevoCliente.getCuo();
        monvie = 0;
        monact = DatosNuevoCliente.getMonpag();
        
        int dia = 0, mes = 0, año = 0;
        
        if (DatosNuevoCliente.getDia()!=0) {
            dia = DatosNuevoCliente.getDia();
            mes = DatosNuevoCliente.getMes()-1;
            año = DatosNuevoCliente.getAño();
        } else {
            Calendar calendario = new GregorianCalendar();
            dia = (calendario.get(Calendar.DATE));
            mes = (calendario.get(Calendar.MONTH));
            año = (calendario.get(Calendar.YEAR));
        }
        
        Calendar calendario = DatosNuevoCliente.getCalendario();
        int diamax1 = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        String mol = DatosNuevoCliente.getMol();
        int diaa1 = 0, mesa1 = 0;
        mesa1 = mes+1;
        int diasemana = (calendario.get(Calendar.DAY_OF_WEEK));
        
        if(mol.equals("Diario")) {
            ajustarfecha1(1, dia, diaa1, mesa1, año, diamax1, calendario);
        }
            else if(mol.equals("Semanal")) {
                ajustarfecha1(8, dia, diaa1, mesa1, año, diamax1, calendario);
            }
                else if(mol.equals("Quincenal")) {
                    ajustarfecha1(15, dia, diaa1, mesa1, año, diamax1, calendario);
                }
                    else {
                        diaa1 = dia+30;
                        int diamax2 = 0;
                        if(diaa1>diamax1) {
                            diaa1 = 30-(diamax1-dia);
                            if (mesa1>11) {
                                calendario.set(calendario.MONTH, 0);
                                calendario.set(calendario.YEAR, año+1);
                            } else {
                                calendario.set(calendario.DATE, 1);
                                calendario.set(calendario.MONTH, mesa1);
                                diamax2 = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
                                
                                if (diaa1>diamax2) {
                                    diaa1 = diaa1-diamax2;
                                    mesa1 = mesa1+1; 
                                    calendario.set(calendario.MONTH, mesa1);
                                }
                            }
                            
                            calendario.set(calendario.DATE, diaa1);
                        } else {
                            calendario.set(calendario.DATE, diaa1);
                        }
                    }
        
        int diaa2 = 0;
        int mesa2 = calendario.get(calendario.MONTH)+1;
        int añoa2 = calendario.get(calendario.YEAR)+1;
        int diamax3 = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if(mol.equals("Diario") && diasemana==6) {
            calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
        } else if(mol.equals("Diario") && diasemana==7) {
            calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
        }
            if(mol.equals("Semanal") && diasemana==6) {
                calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
            } else if(mol.equals("Semanal") && diasemana==7) {
                calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
            }
                if(mol.equals("Quincenal") && diasemana==6) {
                    calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                } else if(mol.equals("Quincenal") && diasemana==7) {
                    calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                }
                    if(mol.equals("Mensual") && diasemana==5) {
                        calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                    } else if(mol.equals("Mensual") && diasemana==6) {
                        calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                    }
                    
        propag = calendario.get(calendario.YEAR)+"-"+
                 (calendario.get(calendario.MONTH)+1)+"-"+
                 calendario.get(calendario.DATE);
                    
        idmov = "1";
        
        String ide = DatosNuevoCliente.getId();
        rss = obj2.consultaIdDeuda(ide);
        try {
            while (rss.next()){
                iddeu = rss.getString("id");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        int resultado = obj2.registrarMovimiento(cuovie, cuoact, monvie, monact, propag, idmov, iddeu);
        
        return resultado;
    }
    
    private void restaurarCamposCliente() {
        cbx1.setSelectedItem("Seleccione");
                        
        DatosNuevoCliente.setNom(null);
        DatosNuevoCliente.setId(null);
        DatosNuevoCliente.setDir(null);
        DatosNuevoCliente.setBar(null);
        DatosNuevoCliente.setTel(null);
        DatosNuevoCliente.setNomref(null);
        DatosNuevoCliente.setIdref(null);
        DatosNuevoCliente.setDirref(null);
        DatosNuevoCliente.setBarref(null);
        DatosNuevoCliente.setTelref(null);
        DatosNuevoCliente.setMonpre(0);
        DatosNuevoCliente.setMol("Seleccione");
        DatosNuevoCliente.setCuo(0);
        DatosNuevoCliente.setMoncuo(0);
        DatosNuevoCliente.setCancuo(0);
        DatosNuevoCliente.setCcb(0);
        
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
        DatosNuevoCliente.setDia(Integer.parseInt(vdia));
        DatosNuevoCliente.setMes(Integer.parseInt(vmes));
        DatosNuevoCliente.setAño(año);
        
        DatosNuevoCliente.setMonint(0);
        DatosNuevoCliente.setMonpag(0);
    }
    
    private String asignarValor(long valor) {
        String resultado = null;
        resultado = formato.format(valor);
        
        return resultado;
    }
    
    public int getRecibir() {
        return recibir;
    }

    public void setRecibir(int recibir) {
        this.recibir = recibir;
    }
    
    public JButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JButton boton2) {
        this.btn2 = boton2;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            
            if (getRecibir()==1) {
                NuevoCliente obj2 = new NuevoCliente();
                posicinarFrame(Principal.jDesktopPane1, obj2);
                ControladorNuevoCliente obj2obj1 = new ControladorNuevoCliente(obj2);
                obj2obj1.cargarTazint();
                obj2obj1.cargarModalidades();
                obj2.cbx1.setSelectedItem(DatosNuevoCliente.getMol());
            }
                else if (getRecibir()==2) {
                    GestionarGastos obj2 = new GestionarGastos();
                    posicinarFrame(Principal.jDesktopPane1, obj2);
                    ControladorGestionarGastos obj22= new ControladorGestionarGastos(obj2);
                }
        } else {
            if (cbx1.getSelectedItem().equals("Seleccione")) {
                alerta("Por favor seleccione una Cartera");
            } else {
                if (getRecibir()==1) {
                    if (DatosNuevoCliente.getMonpre()>Long.valueOf(txt1.getText().replace(".","").replace(",",""))) {
                        alerta("El Monto a Prestar supera el Máximo Monto a Prestar,\n"
                              +"Por favor cambie el Valor del Monto a Prestar");
                    }
                        else if (DatosNuevoCliente.getMonpre()>Long.valueOf(txt5.getText().replace(".","").replace(",",""))) {
                            alerta("El Monto a Prestar supera el Saldo de la Cartera,\n"
                                  +"Por favor cambie el Valor del Monto a Prestar");
                        }
                            else {
                                int respuesta1 = insertarCliente();
                                int respuesta2 = insertarDeuda();
                                int respuesta3 = insertarMovimiento();

                                boolean val = false; 

                                if (respuesta1==0) {
                                    val = true;
                                } 
                                    else if (respuesta2==0) {
                                        val = true;
                                    }
                                        else if (respuesta3==0) {
                                            val = true;
                                        }

                                if (val==true) {
                                    alerta("Ha ocurrido un error al registar un nuevo Cliente, Vuelve a intertarlo");
                                } else {
                                    restaurarCamposCliente();
                                    NuevoCliente obj2 = new NuevoCliente();
                                    posicinarFrame(Principal.jDesktopPane1, obj2);
                                    ControladorNuevoCliente obj2obj1 = new ControladorNuevoCliente(obj2);
                                    obj2obj1.cargarTazint();
                                    obj2obj1.cargarModalidades();
                                    alerta("Registro Exitoso!");
                                }
                            }
                } else {    
                    //Evento para el boton en donde se registra el gasto
                    if(e.getSource()==btn2){

                        if (obj1.cbx1.getSelectedItem().equals("Seleccione")){
                            JOptionPane.showMessageDialog(obj1,"Por favor seleccione una cartera","Alerta",JOptionPane.WARNING_MESSAGE);
                            return;
                        } 
                        int monto =Integer.valueOf(valor.replace(".", "").replace(",", ""));
                        int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ",""));

                        Calendar c = Calendar.getInstance(); 
                        String dia = Integer.toString(c.get(Calendar.DATE));
                        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
                        String anio = Integer.toString(c.get(Calendar.YEAR));            
                        String fecha=anio+"/"+mes+"/"+dia;     

                        boolean verificar = modelo.registrarGasto(monto, fecha,tipo, idCartera);
            
                        if(verificar){
                            obj1.cbx1.setSelectedItem("Seleccione");
                            JOptionPane.showMessageDialog(obj1,"Se ha registrado el Gasto con Exito!");
                        }
                    }
                    ///////////////////////////////////////          
                }
            }
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
            } else {
                String valor2 = valor1;
                valor2 = valor2.replace("Cartera ", "");

                rss = obj2.consultaDatosCartera(valor2);
                try {
                    while (rss.next()){
                        String fecha1 = rss.getString("ca.fecha_inicio");
                        String[] fecha2 = fecha1.split("-");
                        long monmax = rss.getLong("ca.monto_maximo");
                        long capini = rss.getLong("ca.capital_inicial");
                        long totpag = rss.getLong("total_pagado");
                        long totdeucar = rss.getLong("total_deuda_cartera");
                        long totgas = rss.getLong("total_gastos");
                        
                        txt1.setText(asignarValor(monmax));
                        txt2.setText(rss.getString("co.nombre"));
                        txt3.setText(rss.getString("total_clientes"));
                        txt4.setText(fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0]);
                        txt5.setText(asignarValor((capini+totpag)-(totdeucar+totgas)));
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