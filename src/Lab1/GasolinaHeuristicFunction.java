package Lab1;

import aima.search.framework.HeuristicFunction;


public class GasolinaHeuristicFunction implements HeuristicFunction{

    //implementa la función heurística
    public double getHeuristicValue(Object state){
        /*
        * 1- Valores
        * resultado = state.GananciasActuales
        *
        * 2- for aplicando a cada posible trayecto la transformacion X y luego calcular c
        * ganancias_nuevas
        *   3- if (ganancias_nuevas > resultado ){
        *           resultado = ganancias_nuevas;
        *           Cosa para guardar estado ???
        *      }
        *   //////////////////////////////
        * 1- Suma de ganancias peticiones que cumple el estado
        * 2- Resta de distancia que recorre los camiones con el estado actual
        *
        *   //////////////////////////////
        *
        * GasolinaBoard board = (GasolinaBoard)state;
        *
        * */
        return 0.0;

    }

}
/*

// heuristica problema del viajante

public boolean equals(Object obj) {
      boolean retValue;

      retValue = super.equals(obj);
      return retValue;
  }

  public double getHeuristicValue(Object state) {
   ProbTSPBoard board=(ProbTSPBoard)state;
   int sum=0,nc;
   nc=board.getNCities();   //numero de ciudades

   for(int i=0;i<nc;i++) sum=sum+board.distCities(i);

   return (sum);
  }



* */