package Lab1;

import aima.search.framework.GoalTest;

public class GasolineraGoalTest implements GoalTest {

    @Override
    public boolean isGoalState(Object state) {
        return ((Estado)(state)).isGoalState();
    }
}
