package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;

import java.util.Map;

public class EstadoFactory {
    public static EstadoDefault createDefaultState(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        return new EstadoDefault(camiones,peticiones);
    }
    public static Estado createStateFromPrevious(Estado prevState) {
        return new EstadoDefault(prevState);
    }


    public static EstrategiaDistancia createStateD(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        return new EstrategiaDistancia(camiones,peticiones);
    }

    public static EstrategiaDistancia createStateFromPrevious(EstrategiaDistancia prevState) {
        return new EstrategiaDistancia(prevState);
    }

    public static EstrategiaDistanciaPlus createStateDPlus(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        return new EstrategiaDistanciaPlus(camiones,peticiones);
    }

    public static EstrategiaDistanciaPlus createStateFromPrevious(EstrategiaDistanciaPlus prevState) {
        return new EstrategiaDistanciaPlus(prevState);
    }

    public static EstrategiaDistanciaMinus createStateDMinus(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        return new EstrategiaDistanciaMinus(camiones,peticiones);
    }

    public static EstrategiaDistanciaMinus createStateFromPrevious(EstrategiaDistanciaMinus prevState) {
        return new EstrategiaDistanciaMinus(prevState);
    }
}
