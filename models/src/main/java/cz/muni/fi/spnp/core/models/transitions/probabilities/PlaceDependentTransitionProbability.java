package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;

public class PlaceDependentTransitionProbability implements TransitionProbability {

    private double value;
    private StandardPlace dependentPlace;

    public PlaceDependentTransitionProbability(double value, StandardPlace dependentPlace) {
        if (dependentPlace == null)
            throw new IllegalArgumentException("Dependent place must be defined.");

        this.value = value;
        this.dependentPlace = dependentPlace;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public StandardPlace getDependentPlace() {
        return dependentPlace;
    }

    public void setDependentPlace(StandardPlace dependentPlace) {
        this.dependentPlace = dependentPlace;
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

        return String.format("void probdep(\"%s\", %f, \"%s\");",
                transition.getName(), this.getValue(), this.getDependentPlace().getName());
    }
}
