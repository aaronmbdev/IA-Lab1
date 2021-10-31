package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;

import java.util.*;
import java.util.Map.Entry;

public class EstrategiaDistanciaPlus extends EstrategiaDistancia{
    protected EstrategiaDistanciaPlus(Map<Integer, Camion> camiones, Map<Integer, Peticion> peticiones) {
        super(camiones, peticiones);
        ordenarPeticiones();
    }

    public EstrategiaDistanciaPlus(Estado e) {
        super(e);
    }

    private void ordenarPeticiones() {
        Comparator<Entry<Integer,Peticion>> valueComparator = Comparator.comparingInt(t -> t.getValue().getDiasPendiente());
        List<Entry<Integer, Peticion>> listOfEntries = new ArrayList<>(getPeticiones().entrySet());
        listOfEntries.sort(valueComparator);
        LinkedHashMap<Integer,Peticion> sorted = new LinkedHashMap<>(listOfEntries.size());
        for(Entry<Integer,Peticion> entry:listOfEntries) {
            sorted.put(entry.getKey(),entry.getValue());
        }
        this.setPeticiones(sorted);
    }


}
