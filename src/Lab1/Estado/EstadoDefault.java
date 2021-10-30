package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;

import java.util.List;

public class EstadoDefault extends Estado{
    public EstadoDefault(List<Camion> camiones, List<Peticion> peticiones) {
        super(camiones, peticiones);
    }
    public EstadoDefault(Estado prevState) {
        super(prevState);
    }
}
