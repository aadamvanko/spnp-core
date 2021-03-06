package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;

public abstract class TransitionDistributionBase implements TransitionDistribution {
    protected final Function[] functions;
    
    private final TransitionDistributionType distributionType;
    private final StandardPlace dependentPlace;

    public TransitionDistributionBase(TransitionDistributionType distributionType, StandardPlace dependentPlace) {
        this.distributionType = distributionType;

        if (distributionType == TransitionDistributionType.PlaceDependent && dependentPlace == null)
            throw new IllegalArgumentException("Dependent place must be set when distribution type is place dependent");

        this.dependentPlace = dependentPlace;
        
        this.functions = createFunctionsArray();
    }
    
    protected abstract Function[] createFunctionsArray();
    
    public Function[] getFunctions(){
        return functions;
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
}
