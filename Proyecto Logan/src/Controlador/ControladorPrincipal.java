
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Controlador.ContenedorClases.DatosGestionarCobradores;
import Modelo.ModeloPrincipal;
import Vista.CopiaSeguridad;
import Vista.GestionarCarteras;
import Vista.GestionarClientes;
import Vista.GestionarCobradores;
import Vista.GestionarGastos;
import Vista.GestionarIntereses;
import Vista.GestionarModalidades;
import Vista.GestionarMoras;
import Vista.NuevaCartera;
import Vista.NuevoCliente;
import Vista.NuevoCobrador;
import Vista.NuevoPago;
import Vista.Principal;
import Vista.ReporteCartera;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ControladorPrincipal implements ActionListener{
    
    private Principal obj1;
    private NuevoCliente obj2 = new NuevoCliente();
    private ControladorNuevoCliente obj2obj1 = new ControladorNuevoCliente(obj2);
    private NuevaCartera obj3 = new NuevaCartera();
    private ControladorNuevaCartera obj3obj1 = new ControladorNuevaCartera(obj3);
    private NuevoCobrador obj4 = new NuevoCobrador();
    private ControladorNuevoCobrador obj4obj1 = new ControladorNuevoCobrador(obj4);
    private GestionarClientes obj5 = new GestionarClientes();
    private ControladorGestionarClientes obj5obj1 = new ControladorGestionarClientes(obj5);
    private GestionarCarteras obj6 = new GestionarCarteras();
    private ControladorGestionarCarteras obj6obj1 = new ControladorGestionarCarteras(obj6);
    private GestionarCobradores obj7 = new GestionarCobradores();
    private ControladorGestionarCobradores obj7obj1 = new ControladorGestionarCobradores(obj7);    
    private GestionarGastos obj8 = new GestionarGastos();
    private ControladorGestionarGastos obj8obj1 = new ControladorGestionarGastos(obj8);
    private ReporteCartera obj9 = new ReporteCartera();
    private ControladorReporteCartera obj9obj1 = new ControladorReporteCartera(obj9,null);
    private CopiaSeguridad   obj10 = new CopiaSeguridad();
    private ControladorCopiaSeguridad obj10bj1 = new ControladorCopiaSeguridad(obj10);
    private GestionarMoras obj11 = new GestionarMoras();
    private ControladorGestionarMoras obj11obj1 = new ControladorGestionarMoras(obj11);
    private NuevoPago obj12 = new NuevoPago();
    private ControladorNuevoPago obj12obj1 = new ControladorNuevoPago(obj12);
    private GestionarIntereses obj13 = new GestionarIntereses();
    private GestionarModalidades obj14 = new GestionarModalidades();
    private ModeloPrincipal obj15 = new ModeloPrincipal();
    private JDesktopPane escritorio;
    private JMenuItem vtn1, vtn2, vtn3, vtn4, vtn5, vtn6, vtn7, vtn8, ventana9, ventana10, ventana11, ventana12, ventana13;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private boolean ban1 = false, ban2 = false;
    
    public ControladorPrincipal(Principal obj1) {
        this.obj1 = obj1;
        escritorio = obj1.jDesktopPane1;
        vtn1 = obj1.jMenuItem6;
        vtn2 = obj1.jMenuItem7;
        vtn3 = obj1.jMenuItem8;
        vtn4 = obj1.jMenuItem3;
        vtn5 = obj1.jMenuItem5;
        vtn6 = obj1.jMenuItem4;
        vtn7 = obj1.jMenuItem2;
        vtn8 = obj1.jMenuItem1;
        ventana9 = obj1.jMenuItem9;
        ventana10 = obj1.jMenuItem10;
        ventana11 = obj1.jmi4;
        ventana12 = obj1.jMenuItem11;
        ventana13 = obj1.jMenuItem12;
        vtn1.addActionListener(this);
        vtn2.addActionListener(this);
        vtn3.addActionListener(this);
        vtn4.addActionListener(this);
        vtn5.addActionListener(this);
        vtn6.addActionListener(this);
        vtn7.addActionListener(this);
        vtn8.addActionListener(this);
        ventana9.addActionListener(this);
        ventana10.addActionListener(this);
        ventana11.addActionListener(this);
        ventana12.addActionListener(this);
        ventana13.addActionListener(this);
        
        //colocarIcono();
        aplicarMoras();
    }
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
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
    
    private String obtenerProximoPago(String id, int diap, int mesp, int añop) throws ParseException {
        int dia = 0, mes = 0, año = 0;
        String propag = null;
        
        dia = diap;
        mes = mesp-1;
        año = añop;
                        
        Calendar calendario2 = new GregorianCalendar();
        calendario2.set(año, mes, dia);
        int diamax1 = calendario2.getActualMaximum(Calendar.DAY_OF_MONTH);
                        
        String mol = null;
        rss = obj15.consultaModalidad(id);
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
    
    private void aplicarMoras() {
        while (true) {
            rss = obj15.consultaClientes();
            
            try {
                ban1 = false;
                while (rss.next()){
                    String propag = rss.getString("md.proximo_pago");
                    if (propag!=null) {
                        String[] fecha1 = new String[3];
                        if (propag!=null) {
                            fecha1 = propag.split("-");
                        }

                        Calendar calendario = new GregorianCalendar();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                        String fecha2 = fecha1[2]+"-"+(Integer.valueOf(fecha1[1])-1)+"-"+fecha1[0];
                        String fecha3 = calendario.get(calendario.DATE)+"-"+calendario.get(calendario.MONTH)+"-"+calendario.get(calendario.YEAR);

                        Date date1 = sdf.parse(fecha2);
                        Date date2 = sdf.parse(fecha3);

                        if (date2.compareTo(date1)>0) {
                            ban1 = true;
                            ban2 = true;

                            String iddeu = rss.getString("d.id_deuda");
                            int cancuo, cuoact, porint = 0;
                            long monpre, monint, monact;

                            cancuo = rss.getInt("d.cantidad_cuotas");
                            monpre = rss.getLong("d.monto_prestado");
                            monint = rss.getLong("d.monto_interes");
                            cuoact = rss.getInt("md.cuota_actual");
                            monact = rss.getLong("md.monto_actual");

                            rss = obj15.consultaInteresMora();
                            try {
                                while (rss.next()){
                                    porint = rss.getInt("taza_interes");
                                }                
                            } catch(SQLException error){
                                System.out.println("Erroy de MySql"+error);
                            } catch(NumberFormatException error){
                                System.out.println("se presento el siguiente error "+error.getMessage());
                            }

                            long valcuo = (monpre+monint)/cancuo;
                            long monmor = (valcuo*porint)/100;
                            String idest = "1";
                            String[] fecha4 = fecha3.split("-");
                            String fecmor = Integer.valueOf(fecha4[2])+"-"+(Integer.valueOf(fecha4[1])+1)+"-"+Integer.valueOf(fecha4[0]);
                            int resultado1 = obj15.insertarMora(monmor, cuoact, fecmor, idest, iddeu);

                            String idmov = "5";
                            int nuecuoact = cuoact+1;
                            long nuemonact = monact+monmor;
                            int diap = Integer.valueOf(fecha1[2]);
                            int mesp = Integer.valueOf(fecha1[1]);
                            int añop = Integer.valueOf(fecha1[0]);
                            String nuepropag = obtenerProximoPago(iddeu, diap, mesp, añop);
                            int resultado2 = obj15.registrarMovimiento(cuoact, nuecuoact, monact, nuemonact, nuepropag, idmov, iddeu);
                        } else {
                            ban1 = false;
                        }
                    }
                } 
            } catch(SQLException error){
                System.out.println("Erroy de MySql"+error);
            } catch(NumberFormatException error){
                System.out.println("se presento el siguiente error "+error.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            if (!ban1) {
                break;
            }
        }
        
        Timer timer = new Timer (2000, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(ban2) {
                    alerta("Se han aplicado las Moras Correspondientes");
                } else {
                    alerta("No hubieron Moras que Aplicar el día de Hoy");
                }
            } 
        });

        timer.setRepeats(false);
        timer.start();
    }
    
    private void colocarIcono() {
        Image icono = new ImageIcon(getClass().getResource("../Archivos/Azul.png")).getImage();
        this.obj1.setIconImage(icono);
        this.obj1.setTitle("App Logan");
    }
    
    private void posicinarFrame(JDesktopPane escritorio, JInternalFrame ventana) {
        escritorio.removeAll();
        escritorio.add(ventana);
        ventana.setBackground(fondo);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.show();
        obj1.repaint();
    }
    
    private void vaciarObj2() {
        obj2.txt1.setText("");
        obj2.txt2.setText("");
        obj2.txt3.setText("");
        obj2.txt4.setText("");
        obj2.txt5.setText("");
        obj2.txt6.setText("");
        obj2.txt7.setText("");
        obj2.txt8.setText("");
        obj2.txt9.setText("");
        obj2.txt10.setText("");
        obj2.txt11.setText("");
        obj2.txt12.setText("0");
        obj2.txt14.setText("0");
        obj2.txt15.setText("0");
        obj2.txt16.setText("0");
        obj2.ccb1.setSelected(false);
        obj2.cbx1.removeAllItems();
        obj2obj1.cargarTazint();
        obj2obj1.cargarModalidades();
        obj2.cbx1.setSelectedItem("Seleccione");
        
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
        obj2.txt17.setText(vdia+"/"+vmes+"/"+año);
        
        ControladorNuevoCliente.ban = false;
    }
    
    private void vaciarObj3() {
        obj3.txt1.setText("");
        obj3.txt2.setText("");
        obj3.cbx1.removeAllItems();
        obj3obj1.cargarCobradores();
        obj3.cbx1.setSelectedItem("Seleccione");
    }
    
    private void vaciarObj4() {
        obj4.txt1.setText("");
        obj4.txt2.setText("");
        obj4.txt3.setText("");
        obj4.txt4.setText("");
        obj4.txt5.setText("");
    }
    
    private void vaciarObj5() {
        DatosGestionarClientes.setId(null);
        obj5obj1.cargarForm(true);
    }
    
    private void vaciarObj6() {
        obj6.cbx1.removeAllItems();
        obj6obj1.cargarCarteras();
        obj6.cbx1.setSelectedItem("Seleccione");
    }
    
    private void vaciarObj7() {
        DatosGestionarCobradores.setId(null);
        obj7obj1.cargarForm(true);
    }
    
    public void vaciarobj8(){
        
    }
    
    public void vaciarobj9(){
        Calendar c = Calendar.getInstance(); 
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String anio = Integer.toString(c.get(Calendar.YEAR));
        
        if (mes.length()==1) {
            mes = "0"+mes;
        } 
            
        if (dia.length()==1) {
            dia = "0"+dia;
        }
        
        String fecha=dia+"/"+mes+"/"+anio;
        String fecha2=anio+"/"+mes+"/"+dia;
        obj9.txt17.setText(fecha);
        obj9.txt18.setText("—/—/———");
        
        obj9obj1.cargarReporte(fecha2, null, 0);               
        obj9.txt17.requestFocus();
        obj9.txt17.requestFocusInWindow();
        obj9.txt18.requestFocus();
        obj9.txt18.requestFocusInWindow(); 
    }
    
    public void vaciarobj10(){
        obj10.txt1.setText("");
    }
    
    private void vaciarObj11() {
        obj11.txt1.setText("");
        obj11.cbx1.removeAllItems();
        obj11obj1.cargarCarteras();
        obj11obj1.CargarMoras(1, "", 1);
        obj11.cbx1.setSelectedItem("Seleccione");
        
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
        obj11.txt2.setText(vdia+"/"+vmes+"/"+año);
    }
    
    private void vaciarObj12() {
        obj12.txt1.setText("");
        obj12.cbx1.removeAllItems();
        obj12obj1.cargarCarteras();
        obj12obj1.CargarPagos(1, "");
        obj12.cbx1.setSelectedItem("Seleccione");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vtn1) {
            obj2.jPanel1.setBackground(fondo);
            obj2.jPanel2.setBackground(fondo);
            obj2.jPanel3.setBackground(fondo);
            vaciarObj2();
            posicinarFrame(escritorio, obj2);
            obj2.txt12.setEditable(false);
            obj2.txt17.requestFocus();
            obj2.txt17.requestFocusInWindow();
            obj2.txt1.requestFocus();
        }
            else if (e.getSource()==vtn2) {
                vaciarObj3();
                posicinarFrame(escritorio, obj3);
                obj3.txt1.requestFocus();
            }
                else if (e.getSource()==vtn3) {
                    vaciarObj4();
                    posicinarFrame(escritorio, obj4);
                    obj4.txt1.requestFocus();
                }
                    else if (e.getSource()==vtn4) {
                        vaciarObj5();
                        obj5.jPanel1.setBackground(fondo);
                        obj5.jPanel2.setBackground(fondo);
                        posicinarFrame(escritorio, obj5);
                        obj5.txt1.requestFocus();
                    }   
                        else if (e.getSource()==vtn5) {
                            vaciarObj6();
                            posicinarFrame(escritorio, obj6);
                            obj6.cbx1.requestFocus();
                        }
                            else if (e.getSource()==vtn6) {
                                vaciarObj7();
                                posicinarFrame(escritorio, obj7);
                                obj7.txt1.requestFocus();
                            }
                                else if (e.getSource()==vtn7) {
                                    vaciarobj8();
                                    posicinarFrame(escritorio, obj8);
                                }
                                    else if (e.getSource()==vtn8) {
                                        vaciarobj9();
                                        posicinarFrame(escritorio, obj9);
                                    }
                                        else if (e.getSource()==ventana9) {
                                            posicinarFrame(escritorio, obj10);
                                            vaciarobj10();
                                        }
                                            else if (e.getSource()==ventana10) {
                                                vaciarObj11();
                                                posicinarFrame(escritorio, obj11);
                                                obj11.txt2.requestFocus();
                                                obj11.txt2.requestFocusInWindow();
                                                obj11.txt1.requestFocus();
                                            }
                                                else if (e.getSource()==ventana11) {
                                                    vaciarObj12();
                                                    posicinarFrame(escritorio, obj12);
                                                    obj12.txt1.requestFocus();
                                                }
                                                    else if (e.getSource()==ventana12) {
                                                        posicinarFrame(escritorio, obj13);
                                                    }
                                                        else if (e.getSource()==ventana13) {
                                                            posicinarFrame(escritorio, obj14);
                                                        }
    }
}