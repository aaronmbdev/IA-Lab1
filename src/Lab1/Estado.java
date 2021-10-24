package Lab1;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;

import java.util.LinkedList;
import java.util.List;

public class Estado {

    private List<Camion> camiones;
    private List<Peticion> peticiones;

    public Estado(List<Camion> camiones, List<Peticion> peticiones) {
        this.camiones = camiones;
        this.peticiones = peticiones;
    }

    public Estado(Estado e){
//        camiones = e.getCamiones();
//        peticiones = e.getPeticiones();

        List<Camion> c =  new LinkedList<>();
        for (int i = 0; i < e.getNumeroCamiones(); i++){

            c.add( e.getCamiones().get(i).getCopy() );
        }
        camiones = c;

        List<Peticion> p = new LinkedList<>();
        for (int j = 0; j < e.getNumeroPeticiones(); j++){
            p.add( e.getPeticiones().get(j).getCopy() );
        }
        peticiones = p;

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
