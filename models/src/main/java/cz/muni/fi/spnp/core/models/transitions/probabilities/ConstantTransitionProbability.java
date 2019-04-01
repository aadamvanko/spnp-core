package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;

public class ConstantTransitionProbability implements TransitionProbability {

    private double value;

    public ConstantTransitionProbability() {
        this(0.0);
    }

    public ConstantTransitionProbability(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

        return String.format("void probval(\"%s\", %f);",
                transition.getName(), this.getValue());
    }
}
