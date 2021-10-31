package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import java.util.List;
import java.util.Map;

public class EstadoFactory {
    public static EstadoDefault createDefaultState(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        return new EstadoDefault(camiones,peticiones);
    }
    public static Estado createStateFromPrevious(Estado prevState) {
        return new EstadoDefault(prevState);
    }


    public static EstrategiaAaron createAaronState(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        return new EstrategiaAaron(camiones,peticiones);
    }

    public static EstrategiaAaron createStateFromPrevious(EstrategiaAaron prevState) {
        return new EstrategiaAaron(prevState);
    }
}
