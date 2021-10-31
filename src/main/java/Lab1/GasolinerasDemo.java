package Lab1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import Lab1.Camion.Camion;
import Lab1.Camion.CamionFactory;
import Lab1.Estado.Estado;
import Lab1.Estado.EstadoFactory;
import Lab1.Peticiones.Peticion;
import Lab1.Peticiones.PeticionFactory;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * This class is intented to be the starting point
 */
public class GasolinerasDemo {
    public static void main(String[] args){

        long inicio = System.currentTimeMillis();
        Gasolineras gas = new Gasolineras(100,1234);
        CentrosDistribucion dist = new CentrosDistribucion(10,1,1234);
        Map<Integer,Camion> camiones = CamionFactory.fromDistributionCenter(dist);
        Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
        Estado inicial = EstadoFactory.createDefaultState(camiones,peticiones);
        HillClimbingSearchAux(inicial);

//        SimulatedAnnealingSearchAux(inicial);

        long fin = System.currentTimeMillis();


        System.out.println("tiempo: " + (fin- inicio) + " ms");
    }





    private static void HillClimbingSearchAux( Estado board) {
        System.out.println("\nHillClimbing  -->");
        try {
            Problem problem=new Problem(board,new GeneradorSucesores (), new GasolineraGoalTest(), new GasolinaHeuristicFunction());
            Search search= new HillClimbingSearch();
            SearchAgent agent = new SearchAgent (problem,search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void SimulatedAnnealingSearchAux(Estado board) {
        System.out.println("\nSimulated Annealing  -->");
        try {
            Problem problem =  new Problem(board, new GeneradorSucesores(), new GasolineraGoalTest(),new GasolinaHeuristicFunction());
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(2000,100,5,0.001);
            //search.traceOn();
            SearchAgent agent = new SearchAgent(problem,search);

            System.out.println();
//            printActions(agent.getActions());
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
