package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;

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

    @Override
    public void accept(TransitionProbabilityVisitor transitionProbabilityVisitor) {
        transitionProbabilityVisitor.visit(this);
    }
}
