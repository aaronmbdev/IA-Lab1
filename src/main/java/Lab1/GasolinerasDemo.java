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
        Experimento5();
    }

    private static void Experimento1() {
        System.out.println("Experimento 1 -->");
        for(int i = 1; i <= 10; i++) {
            int seed = Utils.getRandNumber(10000);
            System.out.println("Réplica "+i+" Seed: "+seed);
            long inicio = System.currentTimeMillis();
            Gasolineras gas = new Gasolineras(100,seed);
            CentrosDistribucion dist = new CentrosDistribucion(10,1,seed);
            Map<Integer,Camion> camiones = CamionFactory.fromDistributionCenter(dist);
            Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
            long fin = System.currentTimeMillis();
            long init = fin-inicio;

            System.out.println("SubExperimento 1: S");
            long s1Init = System.currentTimeMillis();
            Estado inicialD = EstadoFactory.createStateD(camiones,peticiones);
            HillClimbingSearchAux(inicialD);
            long s1Fin = System.currentTimeMillis();
            long s1 = init + (s1Fin - s1Init);
            System.out.println("Fin de SubExperimento 1. Tiempo :" + s1 + "ms");

            System.out.println("SubExperimento 2: S+");
            long s2Init = System.currentTimeMillis();
            Estado inicialDP = EstadoFactory.createStateDPlus(camiones,peticiones);
            HillClimbingSearchAux(inicialDP);
            long s2Fin = System.currentTimeMillis();
            long s2 = init + (s2Fin - s2Init);
            System.out.println("Fin de SubExperimento 2. Tiempo :" + s2 + "ms");

            System.out.println("SubExperimento 3: S-");
            long s3Init = System.currentTimeMillis();
            Estado incialDM = EstadoFactory.createStateDMinus(camiones,peticiones);
            HillClimbingSearchAux(incialDM);
            long s3Fin = System.currentTimeMillis();
            long s3 = init + (s3Fin - s3Init);
            System.out.println("Fin de SubExperimento 3. Tiempo :" + s3 + "ms");

            long finR = init + (s1Fin - s1Init) + (s2Fin - s2Init) + (s3Fin - s3Init);

            System.out.println("Fin de réplica - Tiempo: " + finR + " ms");
        }
        System.out.println("----FIN DE EXPERIMENTO----");
    }


        private static void Experimento3(){
     //   System.out.println("Experimento 3 -->");
        for(int i = 1; i <= 10; i++) {

            int seed = Utils.getRandNumber(10000);
            System.out.println("Réplica "+i+" Seed: "+seed);

            Gasolineras gas = new Gasolineras(100,seed);
            CentrosDistribucion dist = new CentrosDistribucion(10,1,seed);
            Map<Integer,Camion> camiones = CamionFactory.fromDistributionCenter(dist);
            Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);



            Estado inicialDM = EstadoFactory.createStateDMinus(camiones, peticiones);
            SimulatedAnnealingSearchAux(inicialDM, 1000);


          //  }
        }
        System.out.println("----FIN DEL EXPERIMENTO 3----");
    }



    private static void Experimento4(){
        System.out.println("Experimento 4 -->");



        double[] mediasTiemposHillClimbing = new double[10];
        double[] mediasTiemposSimulatedAnnealing = new double[10];

        //genera proporciones
        for(int i = 1; i <= 10 ; i++){
            double[] tiemposHillClimbing = new double[10];
            double[] tiemposSimulatedAnnealing = new double[10];

            //genera semillas
            for(int j = 0; j < 10 ; j++) {

                int seed = Utils.getRandNumber(10000);
                //System.out.println("Réplica " + i + " Seed: " + seed);
                long inicio = System.currentTimeMillis();
                Gasolineras gas = new Gasolineras(i*100, seed);
                CentrosDistribucion dist = new CentrosDistribucion(i*10, 1, seed);
                Map<Integer, Camion> camiones = CamionFactory.fromDistributionCenter(dist);
                Map<Integer, Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
                long fin = System.currentTimeMillis();
                long init = fin - inicio;



                long HCInit = System.currentTimeMillis();
                Estado incialDM = EstadoFactory.createStateDMinus(camiones, peticiones);
                HillClimbingSearchAux(incialDM);
                long HCFin = System.currentTimeMillis();
                long HC = init + (HCFin - HCInit);


                long SAInit = System.currentTimeMillis();
                Estado inicialDM = EstadoFactory.createStateDMinus(camiones, peticiones);
                SimulatedAnnealingSearchAux(inicialDM, 1000);
                long SAFin = System.currentTimeMillis();
                long SA = init + (SAFin - SAInit);



                tiemposHillClimbing[j] = HC;
                tiemposSimulatedAnnealing[j] = SA;
            }
            for(int k = 0; k < 10 ; k++){
                mediasTiemposHillClimbing[i-1] += tiemposHillClimbing[k];
                mediasTiemposSimulatedAnnealing[i-1] += tiemposSimulatedAnnealing[k];
            }
            mediasTiemposHillClimbing[i-1] = mediasTiemposHillClimbing[i-1] / 10;
            mediasTiemposSimulatedAnnealing[i-1] = mediasTiemposSimulatedAnnealing[i-1] / 10;

            Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
            long fin = System.currentTimeMillis();
            long init = fin-inicio;

            System.out.println("SubExperimento 1: VersiónA");
            long s2Init = System.currentTimeMillis();
            Estado inicialDM1 = EstadoFactory.createStateDMinus(camiones1,peticiones);
            HillClimbingSearchAux(inicialDM1);
            long s2Fin = System.currentTimeMillis();
            long s2 = init + (s2Fin - s2Init);
            System.out.println("Fin de SubExperimento 1. Tiempo :" + s2 + "ms");

            System.out.println("SubExperimento 2: VersiónB");
            long s3Init = System.currentTimeMillis();
            Estado incialDM2 = EstadoFactory.createStateDMinus(camiones2,peticiones);
            HillClimbingSearchAux(incialDM2);
            long s3Fin = System.currentTimeMillis();
            long s3 = init + (s3Fin - s3Init);
            System.out.println("Fin de SubExperimento 2. Tiempo :" + s3 + "ms");

            //long finR = init + s3Fin - s3Init;

            System.out.println("Fin de réplica");

            //System.out.println("Fin de réplica - Tiempo: " + finR + " ms");
        }
        System.out.println("----FIN DE EXPERIMENTO----");
    }


    private static void Experimento5() {
        System.out.println("Experimento 5 -->");
        for(int i = 1; i <= 10; i++) {
            int seed = Utils.getRandNumber(10000);
            System.out.println("Réplica "+i+" Seed: "+seed);
            long inicio = System.currentTimeMillis();
            Gasolineras gas = new Gasolineras(100,seed);
            CentrosDistribucion dist1 = new CentrosDistribucion(10,1,seed);
            CentrosDistribucion dist2 = new CentrosDistribucion(5,2,seed);

            Map<Integer,Camion> camiones1 = CamionFactory.fromDistributionCenter(dist1);
            Map<Integer,Camion> camiones2 = CamionFactory.fromDistributionCenter(dist2);

            Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
            long fin = System.currentTimeMillis();
            long init = fin-inicio;

            System.out.println("SubExperimento 1: VersiónA");
            long s2Init = System.currentTimeMillis();
            Estado inicialDM1 = EstadoFactory.createStateDMinus(camiones1,peticiones);
            HillClimbingSearchAux(inicialDM1);
            long s2Fin = System.currentTimeMillis();
            long s2 = init + (s2Fin - s2Init);
            System.out.println("Fin de SubExperimento 1. Tiempo :" + s2 + "ms");

            System.out.println("SubExperimento 2: VersiónB");
            long s3Init = System.currentTimeMillis();
            Estado incialDM2 = EstadoFactory.createStateDMinus(camiones2,peticiones);
            HillClimbingSearchAux(incialDM2);
            long s3Fin = System.currentTimeMillis();
            long s3 = init + (s3Fin - s3Init);
            System.out.println("Fin de SubExperimento 2. Tiempo :" + s3 + "ms");

            //long finR = init + s3Fin - s3Init;

            System.out.println("Fin de réplica");

            //System.out.println("Fin de réplica - Tiempo: " + finR + " ms");
        }
        System.out.println("----FIN DE EXPERIMENTO----");
    }

    private static void Experimento6() {
        System.out.println("Experimento 6 -->");
        //int seed = Utils.getRandNumber(10000);
        int seed = 8406;

        System.out.println("Seed: "+seed);
        long inicio = System.currentTimeMillis();
        Gasolineras gas = new Gasolineras(100,seed);
        CentrosDistribucion dist1 = new CentrosDistribucion(10,1,seed);
        Map<Integer,Camion> camiones1 = CamionFactory.fromDistributionCenter(dist1);
        Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
        long fin = System.currentTimeMillis();
        long init = fin-inicio;

        long s2Init = System.currentTimeMillis();
        Estado inicialDM = EstadoFactory.createStateDMinus(camiones1,peticiones);
        HillClimbingSearchAux(inicialDM);
        long s2Fin = System.currentTimeMillis();
        long s2 = init + (s2Fin - s2Init);
        System.out.println("Tiempo :" + s2 + "ms");

        System.out.println("----FIN DE EXPERIMENTO----");
    }





    private static void Experimento8(){
        System.out.println("Experimento 8 -->");
        for(int i = 1; i <= 10; i++) {
            int seed = Utils.getRandNumber(1234);
            System.out.println("Réplica "+i+" Seed: "+seed);
            long inicio = System.currentTimeMillis();
            Gasolineras gas = new Gasolineras(100,seed);
            CentrosDistribucion dist = new CentrosDistribucion(10,1,seed);
            Map<Integer,Camion> camiones = CamionFactory.fromDistributionCenter(dist);
            Map<Integer,Peticion> peticiones = PeticionFactory.fromGasolineras(gas);
            long fin = System.currentTimeMillis();
            long init = fin-inicio;



            long s3Init = System.currentTimeMillis();
            Estado incialDM = EstadoFactory.createStateDMinus(camiones,peticiones);
            HillClimbingSearchAux(incialDM);
            long s3Fin = System.currentTimeMillis();
            long s3 = init + (s3Fin - s3Init);
            long finR = init + (s3Fin - s3Init);

            System.out.println("Tiempo: " + finR + " ms");
        }
        System.out.println("----FIN DEL EXPERIMENTO 8----");
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
            //printActions(agent.getActions());
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
