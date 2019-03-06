package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;

public abstract class TransitionDistributionBase {

    private TransitionDistributionType distributionType;
    private Place dependentPlace;

    public TransitionDistributionBase(TransitionDistributionType distributionType, Place dependentPlace) {
        this.distributionType = distributionType;

        if (distributionType == TransitionDistributionType.PlaceDependent && dependentPlace == null)
            throw new IllegalArgumentException("Dependent place must be set when distribution type is place dependent");

        this.dependentPlace = dependentPlace;
    }

    public TransitionDistributionType getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(TransitionDistributionType distributionType) {
        this.distributionType = distributionType;
    }

    public Place getDependentPlace() {
        return dependentPlace;
    }

    public void setDependentPlace(Place dependentPlace) {
        this.dependentPlace = dependentPlace;
    }
}
