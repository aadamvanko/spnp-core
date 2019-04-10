package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public interface TransitionDistribution {

    /**
     * Gets the {@link String} representation of the timed transition distribution definition.
     *
     * @param transition    {@link TimedTransition} on which the distribution is applied.
     * @return representation of the timed transition distribution definition
     */
    String getDefinition(TimedTransition transition);

    /**
     * Gets the {@link String} declarations of the functions defined in this distribution
     * in case of {@link TransitionDistributionType#Functional} distribution type.
     *
     * @return  functions declarations
     */
    String getFunctionsDeclarations();

    /**
     * Gets the {@link String} definitions of the functions defined in this distribution
     * in case of {@link TransitionDistributionType#Functional} distribution type.
     *
     * @return  functions definitions
     */
    String getFunctionsDefinitions();

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
}
