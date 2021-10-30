package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import java.util.List;

public class EstadoFactory {
    public static EstadoDefault createDefaultState(final List<Camion> camiones, final List<Peticion> peticiones) {
        return new EstadoDefault(camiones,peticiones);
    }
    public static Estado createStateFromPrevious(final Estado prevState) {
        return new EstadoDefault(prevState);
    }
}
