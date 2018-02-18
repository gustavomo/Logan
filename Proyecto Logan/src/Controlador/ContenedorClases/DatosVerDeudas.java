
package Controlador.ContenedorClases;

public class DatosVerDeudas {

    private static String fecini = null;
    
    public DatosVerDeudas() {
        
    }

    public static String getFecini() {
        return fecini;
    }

    public static void setFecini(String fecini) {
        DatosVerDeudas.fecini = fecini;
    }
}
