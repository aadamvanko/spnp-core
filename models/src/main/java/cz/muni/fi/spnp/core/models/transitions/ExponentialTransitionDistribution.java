package cz.muni.fi.spnp.core.models.transitions;

import cz.muni.fi.spnp.core.models.places.Place;

import java.util.function.Supplier;

public class ExponentialTransitionDistribution extends TransitionDistributionBase {

    private double value;
    private Supplier<Double> function;

    /**
     * Creates new Exponential TransitionDistribution object with Constant distribution type
     * @param value
     */
    public ExponentialTransitionDistribution(double value) {
        super(TransitionDistributionType.Constant, null);

        this.value = value;
    }

    /**
     * Creates new Exponential TransitionDistribution object with Functional distribution type
     * @param function
     */
    public ExponentialTransitionDistribution(Supplier<Double> function) {
        super(TransitionDistributionType.Functional, null);

        this.function = function;
    }

    /**
     * Creates new Exponential TransitionDistribution object with Place dependent distribution type
     * @param value
     * @param dependentPlace
     */
    public ExponentialTransitionDistribution(double value,
                                             Place dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.value = value;
    }
}
