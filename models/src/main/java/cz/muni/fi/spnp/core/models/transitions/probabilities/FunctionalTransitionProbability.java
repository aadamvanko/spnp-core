package cz.muni.fi.spnp.core.models.transitions.probabilities;

import java.util.function.Supplier;

public class FunctionalTransitionProbability implements TransitionProbability {

    private Supplier<Double> function;

    public FunctionalTransitionProbability(Supplier<Double> function) {
        if (function == null)
            throw new IllegalArgumentException("Function must be defined.");

        this.function = function;
    }

    public Supplier<Double> getFunction() {
        return function;
    }

    public void setFunction(Supplier<Double> function) {
        if (function == null)
            throw new IllegalArgumentException("Function is not defined.");

        this.function = function;
    }
}
