package Lab1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import Lab1.Camion.Camion;
import Lab1.Camion.CamionFactory;
import Lab1.Peticiones.Peticion;
import Lab1.Peticiones.PeticionFactory;

import java.util.List;

/**
 * This class is intented to be the starting point
 */
public class GasolinerasDemo {
    public static void main(String[] args){
        Gasolineras gas = new Gasolineras(Utils.getRandNumber(20),Utils.getRandNumber(3219));
        CentrosDistribucion dist = new CentrosDistribucion(Utils.getRandNumber(10),1,Utils.getRandNumber(3219));

        List<Camion> camiones = CamionFactory.fromDistributionCenter(dist);
        List<Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
        Estado inicial = new Estado(camiones,peticiones);
        System.out.println("Soy gay");
    }




}
