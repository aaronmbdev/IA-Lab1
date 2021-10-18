package Lab1;

import aima.search.framework.HeuristicFunction;

import Lab1.Camion.Camion;
import Lab1.Peticiones.Peticion;
import Lab1.Estado.*;
import Lab1.Peticiones.PeticionFactory;
import java.util.List;


public class GasolinaHeuristicFunction implements HeuristicFunction{

    //implementa la función heurística
    public double getHeuristicValue(Object estado){
        //Cast
        Estado estadoActual = (Estado)estado;

        //Dinero perdido en los Km recorridos por los camiones
        List<Camion> camiones = estadoActual.getCamiones();
        double perdidaCamiones = 0;
        for (Camion camione : camiones) {
            //Sumatorio de: 2 * (Km disponibles de los camiones inicialmente - Km Que todavia pueden recorrer)
            perdidaCamiones += (2 * (640 - camione.getKmDisponibles()));
        }


        //Dinero ganado por las peticiones cumplidass
        double gananciasPeticiones = 0;
        List<Peticion> peticiones = estadoActual.getPeticiones();
        for (Peticion peticione : peticiones) {
            if (peticione.isCumplido()) {
                gananciasPeticiones += peticione.getGananciasActuales();
            }
        }
        return gananciasPeticiones - perdidaCamiones;

    }


}

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
