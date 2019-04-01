package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class BetaTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link BetaTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstValue    first value of beta distribution
     * @param secondValue   second value of beta distribution
     */
    public BetaTransitionDistribution(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Creates new {@link BetaTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstValueFunction     reference to a function which calculates first value of beta distribution
     * @param secondValueFunction    reference to a function which calculates second value of beta distribution
     */
    public BetaTransitionDistribution(Function<Double> firstValueFunction, Function<Double> secondValueFunction) {
        super(firstValueFunction, secondValueFunction);
    }

    /**
     * Creates new {@link BetaTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue        first value of beta distribution
     * @param secondValue       second value of beta distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public BetaTransitionDistribution(Double firstValue, Double secondValue, Place dependentPlace) {
        super(firstValue, secondValue, dependentPlace);
    }

    /**
     * Gets the {@link String} representation of the timed transition distribution definition.
     *
     * @param transition {@link TimedTransition} on which the distribution is applied.
     * @return representation of the timed transition distribution definition
     */
    @Override
    public String getDefinition(TimedTransition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (this.getDistributionType()) {
            case Constant:
                return String.format("void betval(\"%s\", %f, %f);",
                        transition.getName(), this.getFirstValue(), this.getSecondValue());

            case Functional:
                return String.format("void betfun(\"%s\", %s, %s);",
                        transition.getName(), this.getFirstFunction().getName(), this.getSecondFunction().getName());

            case PlaceDependent:
                return String.format("void betdep(\"%s\", %f, %f, \"%s\");",
                        transition.getName(), this.getFirstValue(), this.getSecondValue(), this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Beta transition distribution has unknown type.");
        }
    }
}
