package Lab1;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;

import java.util.List;

public class Estado {

    private List<Camion> camiones;
    private List<Peticion> peticiones;

    public Estado(List<Camion> camiones, List<Peticion> peticiones) {
        this.camiones = camiones;
        this.peticiones = peticiones;
    }

}
