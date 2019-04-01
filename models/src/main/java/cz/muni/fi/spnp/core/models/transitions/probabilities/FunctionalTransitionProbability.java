package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;

public class FunctionalTransitionProbability implements TransitionProbability {

    private Function<Double> function;

    public FunctionalTransitionProbability(Function<Double> function) {
        if (function == null)
            throw new IllegalArgumentException("Function must be defined.");

        this.function = function;
    }

    public Function<Double> getFunction() {
        return function;
    }

    public void setFunction(Function<Double> function) {
        if (function == null)
            throw new IllegalArgumentException("Function is not defined.");

        this.function = function;
    }

    /**
     * Gets the {@link String} representation of the immediate transition probability.
     *
     * @param transition {@link ImmediateTransition} on which the probability is applied.
     * @return representation of the immediate transition probability
     */
    @Override
    public String getDefinition(ImmediateTransition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        return String.format("void probfun(\"%s\", %s);",
                transition.getName(), this.getFunction().getName());
    }
}
