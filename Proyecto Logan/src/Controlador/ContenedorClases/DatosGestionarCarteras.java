
package Controlador.ContenedorClases;

public class DatosGestionarCarteras {
    
    private static String id = null;
    private static String cartera = null;

    public DatosGestionarCarteras() {
        
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DatosGestionarCarteras.id = id;
    }

    public static String getCartera() {
        return cartera;
    }

    public static void setCartera(String cartera) {
        DatosGestionarCarteras.cartera = cartera;
    }
}
