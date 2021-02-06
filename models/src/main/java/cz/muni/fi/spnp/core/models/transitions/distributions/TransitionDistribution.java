package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsDeclarationsVisitor;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsDefinitionsVisitor;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;

public interface TransitionDistribution {
    /**
     * Gets the type of this transition distribution.
     *
     * @return {@link TransitionDistributionType} object
     */
    TransitionDistributionType getDistributionType();

    /**
     * Gets the {@link StandardPlace} object in case this is {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @return dependent {@link StandardPlace} object
     */
    StandardPlace getDependentPlace();

    void accept(TransitionDistributionVisitor transitionDistributionVisitor);

    void accept(TransitionDistributionFunctionsDefinitionsVisitor transitionDistributionFunctionsDefinitionsVisitor);

    void accept(TransitionDistributionFunctionsDeclarationsVisitor transitionDistributionFunctionsDeclarationsVisitor);
}
