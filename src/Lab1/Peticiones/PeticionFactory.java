package Lab1.Peticiones;

import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;
import java.util.LinkedList;
import java.util.List;

public class PeticionFactory {

    public static List<Peticion> fromGasolineras(final Gasolineras gasolineras) {
        List<Peticion> peticiones = new LinkedList<>();
        for(Gasolinera gas:gasolineras){
            List<Peticion> nuevasPeticiones = getPeticionesFromGasolinera(gas);
            peticiones.addAll(nuevasPeticiones);
        }
        return peticiones;
    }

    private static List<Peticion> getPeticionesFromGasolinera(final Gasolinera gas) {
        List<Peticion> ret = new LinkedList<>();
        for(Integer dias:gas.getPeticiones()) {
            ret.add(new Peticion(dias,gas.getCoordX(),gas.getCoordY()));
        }
        return ret;
    }
}
