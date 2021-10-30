package Lab1;

import Lab1.Camion.Camion;
import Lab1.Estado.Estado;
import Lab1.Peticiones.Peticion;
import aima.search.framework.HeuristicFunction;

import java.util.List;


public class GasolinaHeuristicFunction implements HeuristicFunction{

    public double getHeuristicValue(Object estado){


        int contadorPeticionesCumplidas = 0;
        Estado estadoActual = (Estado)estado;
        List<Camion> camiones = estadoActual.getCamiones();
        double perdidaCamiones = 0;
        for (Camion camione : camiones) {
            perdidaCamiones += (2 * (640 - camione.getKmDisponibles()));
        }


        double gananciasPeticiones = 0;
        List<Peticion> peticiones = estadoActual.getPeticiones();
        for (Peticion peticione : peticiones) {
            if (peticione.isCumplido()) {
                contadorPeticionesCumplidas++;
                gananciasPeticiones += peticione.getGananciasActuales();
            }
        }
        return -(gananciasPeticiones - perdidaCamiones);

    }
}
