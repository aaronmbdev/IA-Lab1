package Lab1;

public class GasolinaHeuristicFunction {

    //implementa la función heurística
    public double getHeuristicValue(Object n){

        return ((GasolinaBoard) n).heuristic();
    }

}
