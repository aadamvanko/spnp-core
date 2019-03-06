package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.places.Place;

public class PlaceDependentTransitionProbability implements TransitionProbability {

    private double value;
    private Place dependentPlace;

    public PlaceDependentTransitionProbability(double value, Place dependentPlace) {
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

    public Place getDependentPlace() {
        return dependentPlace;
    }

    public void setDependentPlace(Place dependentPlace) {
        this.dependentPlace = dependentPlace;
    }
}
