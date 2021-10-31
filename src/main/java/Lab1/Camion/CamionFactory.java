package Lab1.Camion;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Distribucion;

import java.util.HashMap;
import java.util.Map;

public class CamionFactory {
    public static Map<Integer,Camion> fromDistributionCenter(final CentrosDistribucion distribucions) {
        Map<Integer,Camion> lista = new HashMap<>();
        for(int i = 0; i < distribucions.size(); i++) {
            Distribucion dist = distribucions.get(i);
            lista.put(i,new Camion(dist.getCoordX(),dist.getCoordY()));
        }
        return lista;
    }
}
