package Lab1;

import Lab1.Camion.Camion;
import Lab1.Estado.Estado;
import aima.search.framework.HeuristicFunction;

import java.util.List;


public class GasolinaHeuristicFunction implements HeuristicFunction{

    public double getHeuristicValue(Object estado){
        double beneficio = 0;
        List<Camion> camions = ((Estado) estado).getCamiones();
        for(Camion c:camions) {
            beneficio = beneficio + c.calcularBeneficio();
        }
        return beneficio;
    }
}
