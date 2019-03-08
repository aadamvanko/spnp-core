package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;

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
}
