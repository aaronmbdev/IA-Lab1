package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import aima.search.framework.Successor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static Lab1.Utils.getRandNumber;

public abstract class Estado implements Cloneable {

    private List<Camion> camiones;
    private List<Peticion> peticiones;

    protected Estado(List<Camion> camiones, List<Peticion> peticiones) {
        this.camiones = camiones;
        this.peticiones = peticiones;
    }

    public Estado(Estado e){
        List<Camion> c =  new LinkedList<>();
        for (int i = 0; i < e.getNumeroCamiones(); i++){

            c.add(e.getCamiones().get(i).getCopy());
        }
        camiones = c;

        List<Peticion> p = new LinkedList<>();
        for (int j = 0; j < e.getNumeroPeticiones(); j++){
            p.add(e.getPeticiones().get(j).getCopy());
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

    public List getSuccessors() {
        ArrayList retVal = new ArrayList();
        int nPeticiones = getNumeroPeticiones();
        for (int i = 0; i < nPeticiones; ++i) {

            Peticion p = getPeticiones().get(i);

            if (!p.isCumplido()){
                int numeroCamiones = getNumeroCamiones();

                for (int j = 0; j < numeroCamiones; ++j) {
                    if (getCamiones().get(j).puedoHacerViaje( p.getCoordX(), p.getCoordY() )){
                        Estado nuevoEstado = EstadoFactory.createStateFromPrevious(this);
                        nuevoEstado.getCamiones().get(j).llenarGasolinera(
                                nuevoEstado.getPeticiones().get(i).getCoordX(),
                                nuevoEstado.getPeticiones().get(i).getCoordY() );

                        nuevoEstado.getPeticiones().get(i).setCumplido(true);

                        retVal.add(new Successor(nuevoEstado.toString(), nuevoEstado));
                    }
                }
            }
        }
        return (retVal);
    }


    public List getSuccessorsSA(){
        ArrayList retVal = new ArrayList();
        Estado nuevoEstado = EstadoFactory.createStateFromPrevious(this);
        boolean modificado = false;






        while(! modificado){

            int x = getRandNumber(this.getNumeroCamiones() -1);
            boolean peticionNoEncontrada = true;

            int y;
            Peticion p;

            do{
                y =getRandNumber(this.getNumeroPeticiones() -1);
                p = getPeticiones().get(y);
                peticionNoEncontrada = p.isCumplido();
            } while(peticionNoEncontrada );

            if (getCamiones().get(x).puedoHacerViaje( p.getCoordX(), p.getCoordY() )){

                nuevoEstado.getCamiones().get(x).llenarGasolinera(
                        nuevoEstado.getPeticiones().get(y).getCoordX(),
                        nuevoEstado.getPeticiones().get(y).getCoordY() );

                nuevoEstado.getPeticiones().get(y).setCumplido(true);

                modificado = true;
            }

        }

        retVal.add(new Successor(nuevoEstado.toString(), nuevoEstado));
        return (retVal);
    }
}
