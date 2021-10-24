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

    public Estado(Estado e){
        camiones = e.getCamiones();
        peticiones = e.getPeticiones();
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public List<Peticion> getPeticiones() {
        return peticiones;
    }

    public boolean isGoalState() {
        for(Camion c: camiones){
            if (c.mePuedoMover()) return false;
        }
        return true;

    }
    public int getNumeroPeticiones(){
        return peticiones.size();
    }

    public int getNumeroCamiones(){
        return camiones.size();
    }

}
