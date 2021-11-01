package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import Lab1.Utils;
import aima.search.framework.Successor;

import java.util.*;
import java.util.Map.Entry;

public class EstrategiaDistanciaPlus extends EstrategiaDistancia{
    protected EstrategiaDistanciaPlus(Map<Integer, Camion> camiones, Map<Integer, Peticion> peticiones) {
        super(camiones, peticiones);
    }

    public EstrategiaDistanciaPlus(Estado e) {
        super(e);
    }

    @Override
    public List getSuccessors() {
        ArrayList retVal = new ArrayList();
        for(Map.Entry<Integer,Camion> camionEntry: this.getCamiones().entrySet()) {
            int index = laMejorPeticion(camionEntry.getValue());
            if(index != -1) {
                EstrategiaDistancia nuevoEstado = EstadoFactory.createStateFromPrevious(this);
                Peticion p = nuevoEstado.getPeticiones().get(index);
                nuevoEstado.getCamiones().get(camionEntry.getKey()).atenderPeticion(p.getCoordX(),p.getCoordY(),p.getDiasPendiente());
                p.setCumplido(true);
                nuevoEstado.computeBalance();
                nuevoEstado.computeKms();
                retVal.add(new Successor(getActionFromState(camionEntry.getKey(),
                        nuevoEstado.getCamiones().get(camionEntry.getKey()),
                        nuevoEstado.getPeticiones().get(index),
                        nuevoEstado.getBalance(),
                        nuevoEstado.getKms()),nuevoEstado));
            }
        }
        return retVal;
    }

    private Integer laMejorPeticion(Camion c) {
        int distancia = Integer.MAX_VALUE;
        ordenarPeticiones();
        int index = -1;
        for(Map.Entry<Integer,Peticion> pEntry: this.getPeticiones().entrySet()) {
            if(!pEntry.getValue().isCumplido()) {
                int tempDistance = Utils.computeDistance(c.getCoordX(),c.getCoordY(),pEntry.getValue().getCoordX(),pEntry.getValue().getCoordY());
                if(c.puedoHacerViaje(pEntry.getValue().getCoordX(),pEntry.getValue().getCoordY())) {
                    if(tempDistance < distancia) index = pEntry.getKey();
                }
            }
        }
        return index;
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
