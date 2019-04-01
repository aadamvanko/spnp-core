package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class LogarithmicNormalTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link LogarithmicNormalTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstValue    first value of logarithmic normal distribution
     * @param secondValue   second value of logarithmic normal distribution
     */
    public LogarithmicNormalTransitionDistribution(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Creates new {@link LogarithmicNormalTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstFunction     reference to a function which calculates first value of logarithmic normal distribution
     * @param secondFunction    reference to a function which calculates second value of logarithmic normal distribution
     */
    public LogarithmicNormalTransitionDistribution(Function<Double> firstFunction, Function<Double> secondFunction) {
        super(firstFunction, secondFunction);
    }

    /**
     * Creates new {@link LogarithmicNormalTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue        first value of logarithmic normal distribution
     * @param secondValue       second value of logarithmic normal distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public LogarithmicNormalTransitionDistribution(Double firstValue, Double secondValue, Place dependentPlace) {
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
                return String.format("void lognval(\"%s\", %f, %f);",
                        transition.getName(), this.getFirstValue(), this.getSecondValue());

            case Functional:
                return String.format("void lognfun(\"%s\", %s, %s);",
                        transition.getName(),
                        this.getFirstFunction().getName(),
                        this.getSecondFunction().getName());

            case PlaceDependent:
                return String.format("void logndep(\"%s\", %f, %f, \"%s\");",
                        transition.getName(),
                        this.getFirstValue(),
                        this.getSecondValue(),
                        this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Logarithmic normal transition distribution has unknown type.");
        }
    }
}
