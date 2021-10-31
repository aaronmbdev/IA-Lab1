package Lab1;

import Lab1.Estado.Estado;
import aima.search.framework.SuccessorFunction;
import java.util.List;

public class GeneradorSucesores implements SuccessorFunction {
    @Override
    public List getSuccessors(Object estado) {
        return ((Estado) estado).getSuccessors();
    }
}

