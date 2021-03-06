package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;

public class FunctionalTransitionProbability implements TransitionProbability {

    private Function function;

    public FunctionalTransitionProbability(Function function) {
        if (function == null)
            throw new IllegalArgumentException("Function must be defined.");
        if (function.getFunctionType() != FunctionType.Probability)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.function = function;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        if (function == null)
            throw new IllegalArgumentException("Function is not defined.");
        if (function.getFunctionType() != FunctionType.Probability)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.function = function;
    }

    @Override
    public void accept(TransitionProbabilityVisitor transitionProbabilityVisitor) {
        transitionProbabilityVisitor.visit(this);
    }
}
