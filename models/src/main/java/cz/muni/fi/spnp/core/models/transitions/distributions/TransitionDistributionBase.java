package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;

public abstract class TransitionDistributionBase implements TransitionDistribution {

    private TransitionDistributionType distributionType;
    private StandardPlace dependentPlace;

    public TransitionDistributionBase(TransitionDistributionType distributionType, StandardPlace dependentPlace) {
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
     * Gets the {@link StandardPlace} object in case this is {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @return dependent {@link StandardPlace} object
     */
    @Override
    public StandardPlace getDependentPlace() {
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

        return this.getFunctionsFullDefinitions();
    }

    /**
     * Gets the {@link String} declarations of the functions defined in this distribution
     * in case of {@link TransitionDistributionType#Functional} distribution type.
     *
     * @return functions declarations
     */
    @Override
    public String getFunctionsDeclarations() {
        if (this.getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Functions declarations are available ONLY on Functional Transition Distribution type.");

        return this.getFunctionsFullDeclarations();
    }


    protected abstract String getFunctionsFullDefinitions();

    protected abstract String getFunctionsFullDeclarations();
}
