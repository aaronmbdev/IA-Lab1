package Lab1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import Lab1.Camion.Camion;
import Lab1.Camion.CamionFactory;
import Lab1.Peticiones.Peticion;
import Lab1.Peticiones.PeticionFactory;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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
        System.out.println("Soy un genio");

        System.out.println("Camiones:" + inicial.getNumeroCamiones() + " Peticiones:" + inicial.getNumeroPeticiones());


        HillClimbingSearchAux(inicial);
    }





    private static void HillClimbingSearchAux( Estado board) {
        System.out.println("\nHillClimbing  -->");
        try {

            Problem problem=new Problem(board,new GeneradorSucesores (), new GasolineraGoalTest(), new GasolinaHeuristicFunction());
            Search search= new HillClimbingSearch();
            SearchAgent agent = new SearchAgent (problem,search);


            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }



}
