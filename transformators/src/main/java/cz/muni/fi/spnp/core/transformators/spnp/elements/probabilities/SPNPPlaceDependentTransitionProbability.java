package cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.probabilities.TransitionProbability;
import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionProbabilityVisitorSPNP;

public class SPNPPlaceDependentTransitionProbability implements TransitionProbability {

    private String value;
    private StandardPlace dependentPlace;

    public SPNPPlaceDependentTransitionProbability(String value, StandardPlace dependentPlace) {
        if (dependentPlace == null)
            throw new IllegalArgumentException("Dependent place must be defined.");

        this.value = value;
        this.dependentPlace = dependentPlace;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
        if (transitionProbabilityVisitor instanceof TransitionProbabilityVisitorSPNP) {
            ((TransitionProbabilityVisitorSPNP) transitionProbabilityVisitor).visit(this);
        }
    }

}
