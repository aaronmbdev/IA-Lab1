package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import aima.search.framework.Successor;

import java.util.*;

public abstract class Estado {

    private Map<Integer,Camion> camiones;
    private Map<Integer,Peticion> peticiones;
    private boolean inicial = false;
    private double balance;

    protected Estado(final Map<Integer,Camion> camiones, final Map<Integer,Peticion> peticiones) {
        this.camiones = camiones;
        this.peticiones = peticiones;
    }

    public Estado(Estado e){
        Map<Integer,Camion> c =  new HashMap<>();
        for (int i = 0; i < e.camiones.size(); i++){
            c.put(i,e.camiones.get(i).getCopy());
        }
        camiones = c;
        Map<Integer,Peticion> p = new HashMap<>();
        for (int j = 0; j < e.peticiones.size(); j++){
            p.put(j,e.peticiones.get(j).getCopy());
        }
        peticiones = p;
        computeBalance();
    }

    public void setInicial(final boolean ini) {
        this.inicial = ini;
    }

    private void computeBalance() {
        for(Map.Entry<Integer,Camion> cEntry:camiones.entrySet()) {
            balance = balance + cEntry.getValue().calcularBeneficio();
        }
    }

    private Camion getCamion(final Integer i) {
        return camiones.get(i);
    }


    public boolean isGoalState() {
        for(Map.Entry<Integer,Camion> entry: camiones.entrySet()) {
            if(entry.getValue().mePuedoMover()) return false;
        }
        return true;

    }

    public List getSuccessors() {
        ArrayList retVal = new ArrayList();
        for(Map.Entry<Integer,Peticion> pEntry: peticiones.entrySet()) {
            boolean camionEncontrado = false;
            if(!pEntry.getValue().isCumplido()) {
                Peticion p = pEntry.getValue();
                for(Map.Entry<Integer,Camion> cEntry: camiones.entrySet()) {
                    if(camionEncontrado) break;
                    if(cEntry.getValue().puedoHacerViaje(p.getCoordX(),p.getCoordY())) {
                        camionEncontrado = true;
                        p.setCumplido(true);
                        Estado nuevoEstado = EstadoFactory.createStateFromPrevious(this);
                        nuevoEstado.getCamion(cEntry.getKey()).atenderPeticion(p.getCoordX(),p.getCoordY(),p.getDiasPendiente());
                        retVal.add(new Successor(getActionFromState(cEntry.getKey(),nuevoEstado.getCamion(cEntry.getKey()),p),nuevoEstado));
                    }
                }
            }
        }

        return (retVal);
    }

    private String getActionFromState(Integer i,Camion c, Peticion p) {
        return "Camion " + i + " viaja a repostar ["+p.getCoordX()+","+p.getCoordY()+"] y tiene un beneficio de "+c.calcularBeneficio();
    }

    public double getHeuristicValue() {
        if(inicial) {
            return Integer.MAX_VALUE;
        } else {
            double sum = 0.0;
            for(Map.Entry<Integer,Camion> entry:camiones.entrySet()) {
                sum = sum + entry.getValue().calcularGastos();
            }
            if(sum != 0) return sum;
            else return Integer.MAX_VALUE;
        }
    }
}
