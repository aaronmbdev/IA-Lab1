package Lab1.Estado;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import aima.search.framework.Successor;

import java.util.*;

public abstract class Estado {

    private Map<Integer,Camion> camiones;
    private Map<Integer,Peticion> peticiones;
    private double balance;

    protected Map<Integer, Camion> getCamiones() {
        return camiones;
    }

    protected Map<Integer, Peticion> getPeticiones() {
        return peticiones;
    }

    protected void setPeticiones(Map<Integer,Peticion> map) {
        this.peticiones = map;
    }

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
    }

    protected void computeBalance() {
        for(Map.Entry<Integer,Camion> cEntry:camiones.entrySet()) {
            balance = balance + cEntry.getValue().calcularBeneficio();
        }
    }

    private Camion getCamion(final Integer i) {
        return camiones.get(i);
    }


    public boolean isGoalState() {
        if(quedanPeticionesPendientes()) {
            for(Map.Entry<Integer,Camion> entry: camiones.entrySet()) {
                if(entry.getValue().mePuedoMover()) return false;
            }
        }
        return true;
    }

    private boolean quedanPeticionesPendientes() {
        for(Map.Entry<Integer,Peticion> entry:peticiones.entrySet()) {
            if(!entry.getValue().isCumplido()) return true;
        }
        return false;
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
                        nuevoEstado.computeBalance();
                        retVal.add(new Successor(getActionFromState(cEntry.getKey(),nuevoEstado.getCamion(cEntry.getKey()),p,this.balance),nuevoEstado));
                    }
                }
            }
        }

        return (retVal);
    }

    protected double getBalance() {
        return this.balance;
    }

    protected String getActionFromState(Integer i,Camion c, Peticion p, double balance) {
        return "Camion " + i + " viaja a repostar ["+p.getCoordX()+","+p.getCoordY()+"] y tiene un beneficio de "+c.calcularBeneficio() + " - Balance: " + balance;
    }

    public double getHeuristicValue() {
        double ganancias = 0.0;
        for(Map.Entry<Integer,Camion> camionEntry:camiones.entrySet()) {
            ganancias = ganancias + camionEntry.getValue().calcularBeneficio();
        }
        ganancias = -ganancias;
        return ganancias;
    }
}
