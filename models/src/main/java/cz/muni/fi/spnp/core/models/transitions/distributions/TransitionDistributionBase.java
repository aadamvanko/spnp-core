package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;

public abstract class TransitionDistributionBase implements TransitionDistribution {

    private TransitionDistributionType distributionType;
    private Place dependentPlace;

    public TransitionDistributionBase(TransitionDistributionType distributionType, Place dependentPlace) {
        this.distributionType = distributionType;

        if (distributionType == TransitionDistributionType.PlaceDependent && dependentPlace == null)
            throw new IllegalArgumentException("Dependent place must be set when distribution type is place dependent");

        this.dependentPlace = dependentPlace;
    }

    /**
     * Gets the type of this transition distribution.
     *
     * @return {@link TransitionDistributionType} object
     */
    @Override
    public TransitionDistributionType getDistributionType() {
        return this.distributionType;
    }

    /**
     * Gets the {@link Place} object in case this is {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @return dependent {@link Place} object
     */
    @Override
    public Place getDependentPlace() {
        return this.dependentPlace;
    }

    /**
     * Gets the {@link String} representation of the functions definitions in case of
     * {@link TransitionDistributionType#Functional} distribution type.
     *
     * @return representation of functions definitions
     */
    @Override
    public final String getFunctionsDefinitions() {
        if (this.getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Functions definitions are available ONLY on Functional Transition Distribution type.");

        return this.getFunctionsStringRepresentation();
    }


    protected abstract String getFunctionsStringRepresentation();
}
