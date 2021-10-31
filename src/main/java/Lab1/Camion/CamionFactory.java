package Lab1.Camion;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;

import java.util.LinkedList;
import java.util.List;

public class CamionFactory {
    public static List<Camion> fromDistributionCenter(final CentrosDistribucion distribucions) {
        List<Camion> lista = new LinkedList<>();
        for(Distribucion dist:distribucions) {
            lista.add(new Camion(dist.getCoordX(),dist.getCoordY()));
        }
        return lista;
    }
}
