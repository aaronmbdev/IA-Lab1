package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import java.util.List;
import java.util.Map;

public class EstadoFactory {
    public static EstadoDefault createDefaultState(final Map<Integer,Camion> camiones, final Map<Integer,Peticion> peticiones) {
        return new EstadoDefault(camiones,peticiones);
    }
    public static Estado createStateFromPrevious(final Estado prevState) {
        return new EstadoDefault(prevState);
    }
}
