package Lab1;

import aima.search.framework.SuccessorFunction;

import java.util.LinkedList;
import java.util.List;

public class GeneradorSucesores implements SuccessorFunction {
    @Override
    public List getSuccessors(Object state) {
        state = (Estado) state;
        return new LinkedList();
    }
}
