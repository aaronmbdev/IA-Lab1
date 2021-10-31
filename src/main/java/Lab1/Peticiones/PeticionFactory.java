package Lab1.Peticiones;

import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.HashMap;
import java.util.Map;

public class PeticionFactory {

    public static Map<Integer,Peticion> fromGasolineras(final Gasolineras gasolineras) {
        Map<Integer,Peticion> peticiones = new HashMap<>();
        int latest = peticiones.size();
        for(Gasolinera gas:gasolineras){
            Map<Integer,Peticion> nuevasPeticiones = getPeticionesFromGasolinera(gas);
            for(Map.Entry<Integer,Peticion> entry:nuevasPeticiones.entrySet()) {
                peticiones.put(latest,entry.getValue());
                latest++;
            }
        }
        return peticiones;
    }

    private static Map<Integer,Peticion> getPeticionesFromGasolinera(final Gasolinera gas) {
        Map<Integer,Peticion> ret = new HashMap<>();
        for(int i = 0; i < gas.getPeticiones().size(); i++) {
            ret.put(i, new Peticion(
                    gas.getPeticiones().get(i),
                    gas.getCoordX(),
                    gas.getCoordY()
            ));
        }
        return ret;
    }
}
