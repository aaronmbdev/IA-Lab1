package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;

import java.util.List;
import java.util.Map;

public class EstadoDefault extends Estado{
    public EstadoDefault(Map<Integer,Camion> camiones, Map<Integer,Peticion> peticiones) {
        super(camiones, peticiones);
    }
    public EstadoDefault(Estado prevState) {
        super(prevState);
    }
}
