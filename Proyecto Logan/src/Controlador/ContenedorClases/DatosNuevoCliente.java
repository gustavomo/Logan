
package Controlador.ContenedorClases;

import java.util.Calendar;

public class DatosNuevoCliente {
    
    private static String id = null, nom = null, tel = null, dir = null,
                         bar = null, idref = null, nomref = null,
                         telref = null, dirref = null, barref = null,
                         mol = null;
    private static long monpre = 0, cuo = 0, moncuo = 0, monint = 0, monpag;
    private static int cancuo = 0, ccb = 0, dia = 0, mes = 0, año = 0;
    private static Calendar calendario;
    
    public DatosNuevoCliente () {
        
    }
    
    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DatosNuevoCliente.id = id;
    }
    
    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        DatosNuevoCliente.nom = nom;
    }
    
    public static String getDir() {
        return dir;
    }

    public static void setDir(String dir) {
        DatosNuevoCliente.dir = dir;
    }

    public static String getBar() {
        return bar;
    }

    public static void setBar(String bar) {
        DatosNuevoCliente.bar = bar;
    }

    public static String getTel() {
        return tel;
    }

    public static void setTel(String tel) {
        DatosNuevoCliente.tel = tel;
    }
    
    public static String getIdref() {
        return idref;
    }

    public static void setIdref(String idref) {
        DatosNuevoCliente.idref = idref;
    }

    public static String getNomref() {
        return nomref;
    }

    public static void setNomref(String nomref) {
        DatosNuevoCliente.nomref = nomref;
    }

    public static String getTelref() {
        return telref;
    }

    public static void setTelref(String telref) {
        DatosNuevoCliente.telref = telref;
    }

    public static String getDirref() {
        return dirref;
    }

    public static void setDirref(String dirref) {
        DatosNuevoCliente.dirref = dirref;
    }

    public static String getBarref() {
        return barref;
    }

    public static void setBarref(String barref) {
        DatosNuevoCliente.barref = barref;
    }

    public static long getMonpre() {
        return monpre;
    }

    public static void setMonpre(long monpre) {
        DatosNuevoCliente.monpre = monpre;
    }
    
    public static String getMol() {
        return mol;
    }

    public static void setMol(String mol) {
        DatosNuevoCliente.mol = mol;
    }

    public static int getCancuo() {
        return cancuo;
    }

    public static void setCancuo(int cancuo) {
        DatosNuevoCliente.cancuo = cancuo;
    }

    public static long getCuo() {
        return cuo;
    }

    public static void setCuo(long cuo) {
        DatosNuevoCliente.cuo = cuo;
    }

    public static long getMoncuo() {
        return moncuo;
    }

    public static void setMoncuo(long moncuo) {
        DatosNuevoCliente.moncuo = moncuo;
    }

    public static int getCcb() {
        return ccb;
    }

    public static void setCcb(int ccb) {
        DatosNuevoCliente.ccb = ccb;
    }

    public static int getDia() {
        return dia;
    }

    public static void setDia(int dia) {
        DatosNuevoCliente.dia = dia;
    }

    public static int getMes() {
        return mes;
    }

    public static void setMes(int mes) {
        DatosNuevoCliente.mes = mes;
    }

    public static int getAño() {
        return año;
    }

    public static void setAño(int año) {
        DatosNuevoCliente.año = año;
    }

    public static Calendar getCalendario() {
        return calendario;
    }

    public static void setCalendario(Calendar calendario) {
        DatosNuevoCliente.calendario = calendario;
    }
    
    public static long getMonint() {
        return monint;
    }

    public static void setMonint(long monint) {
        DatosNuevoCliente.monint = monint;
    }

    public static long getMonpag() {
        return monpag;
    }

    public static void setMonpag(long monpag) {
        DatosNuevoCliente.monpag = monpag;
    }
}
