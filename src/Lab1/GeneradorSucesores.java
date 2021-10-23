package Lab1;

import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class GeneradorSucesores implements SuccessorFunction {
    @Override
    public List getSuccessors(Object estado) {

//        estado = (Estado) estado;
//        return new LinkedList();

        ArrayList retVal = new ArrayList();
        Estado estadoActual = (Estado)estado;

        int nc = estadoActual.getNumeroPeticiones();

        for (int j = 0; j < nc; j++) {
            if (!estadoActual.getPeticiones().get(j).isCumplido()){
                Estado nuevoEstado = new Estado(estadoActual);

                //Brujeria para saber como hacer cambio o no

/*                if (nuevoEstado.cumpleRestricciones() ) {
                    //hacer cosas nuevoEstado.assignaPaquet();


                    retVal.add(new Successor(nuevoEstado.toString(), nuevoEstado));
                }*/
            }


        }


        return (retVal);
    }
}

