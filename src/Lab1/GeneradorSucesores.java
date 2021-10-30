package Lab1;

import Lab1.Peticiones.Peticion;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class GeneradorSucesores implements SuccessorFunction {
    @Override
    public List getSuccessors(Object estado) {
        ArrayList retVal = new ArrayList();
        Estado estadoActual = (Estado)estado;
        int nPeticiones = estadoActual.getNumeroPeticiones();
        for (int i = 0; i < nPeticiones; ++i) {

            Peticion p = estadoActual.getPeticiones().get(i);

            if (!p.isCumplido()){
                int numeroCamiones = estadoActual.getNumeroCamiones();

                for (int j = 0; j < numeroCamiones; ++j) {

                    //se compueba si un camión j puede cumplir la peticion i


                    if (estadoActual.getCamiones().get(j).puedoHacerViaje( p.getCoordX(), p.getCoordY() )){
                        Estado nuevoEstado = new Estado(estadoActual);

                        nuevoEstado.getCamiones().get(j).llenarGasolinera(
                                nuevoEstado.getPeticiones().get(i).getCoordX(),
                                nuevoEstado.getPeticiones().get(i).getCoordY() );

                        nuevoEstado.getPeticiones().get(i).setCumplido(true);

                        //System.out.println("petición:" + i + " es hecha por camíon:" + j);

                        /*System.out.println(
                                "Coordenadas petición: ("+ p.getCoordX() + ", " + p.getCoordY() +
                                ") Viaje?: " + estadoActual.getCamiones().get(j).puedoHacerViaje( p.getCoordX(), p.getCoordY() ) +
                                " Cisterna?:" + estadoActual.getCamiones().get(j).getEstadoCisterna() +
                                " Mover:"+ estadoActual.getCamiones().get(j).mePuedoMover() +
                                " km_Disponibles:" +estadoActual.getCamiones().get(j).getKmDisponibles() +
                                " ViajesRestanses:" + estadoActual.getCamiones().get(j).getViajes()
                        );*/

                        retVal.add(new Successor(nuevoEstado.toString(), nuevoEstado));


                    }


                    /*
                    1- elegir camion J
                    2- ver si puede ir desde donde está a la petición y volver a base
                    3.1- Si estadoCisterna == MEDIO_LLENO
                        calcular coste y ganancias de ir a lugar y volver a base.

                    3.2- Si estadoCisterna == LLENO
                        calcular coste y ganancias de ir al lugar
                    */


                                    /*
                SI AumentoDeBeneficio > 0 ->
                    1- Desplazar a ubicación de petición
                    2.1- Si estadoCisterna == MEDIO_LLENO
                        modificar estadoCisterna a VACIO
                        llevar camión a base y cambiar numViajes, estadoCisterna

                    2.2- si j.estadoCisterna == LLENO
                        modificar estadoCisterna a MEDIO_LLENO

                    3-Añadir sucecsor a lista de sucesores
                */
                    //retVal.add(new Successor(nuevoEstado.toString(), nuevoEstado));
                }
            }
        }
        //System.out.println("Número de sucecores creados:" + retVal.size());
        return (retVal);
    }


}

