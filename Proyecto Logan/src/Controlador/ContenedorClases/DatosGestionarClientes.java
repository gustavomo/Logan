
package Controlador.ContenedorClases;

public class DatosGestionarClientes {
    
    private static String id = null;

    public DatosGestionarClientes() {
        
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DatosGestionarClientes.id = id;
    }
}
