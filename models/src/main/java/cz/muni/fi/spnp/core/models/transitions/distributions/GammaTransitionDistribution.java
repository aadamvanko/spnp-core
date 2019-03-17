package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;

public class GammaTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link GammaTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstValue    first value of gamma distribution
     * @param secondValue   second value of gamma distribution
     */
    public GammaTransitionDistribution(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Creates new {@link GammaTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstValueFunction     reference to a function which calculates first value of gamma distribution
     * @param secondValueFunction    reference to a function which calculates second value of gamma distribution
     */
    public GammaTransitionDistribution(Function<Double> firstValueFunction, Function<Double> secondValueFunction) {
        super(firstValueFunction, secondValueFunction);
    }

    /**
     * Creates new {@link GammaTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue        first value of gamma distribution
     * @param secondValue       second value of gamma distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public GammaTransitionDistribution(Double firstValue, Double secondValue, Place dependentPlace) {
        super(firstValue, secondValue, dependentPlace);
    }
}
