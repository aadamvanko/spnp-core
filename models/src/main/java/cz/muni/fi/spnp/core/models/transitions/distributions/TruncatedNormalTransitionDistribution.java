package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;

import java.util.function.Supplier;

public class TruncatedNormalTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link TruncatedNormalTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param expectation   expectation value of truncated normal distribution
     * @param variance      variance value of truncated normal distribution
     */
    public TruncatedNormalTransitionDistribution(Double expectation, Double variance) {
        super(expectation, variance);
    }

    /**
     * Creates new {@link TruncatedNormalTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param expectationFunction   reference to a function which calculates expectation value of truncated normal distribution
     * @param varianceFunction      reference to a function which calculates variance value of truncated normal distribution
     */
    public TruncatedNormalTransitionDistribution(Supplier<Double> expectationFunction, Supplier<Double> varianceFunction) {
        super(expectationFunction, varianceFunction);
    }

    /**
     * Creates new {@link TruncatedNormalTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param expectation       expectation value of truncated normal distribution
     * @param variance          variance value of truncated normal distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public TruncatedNormalTransitionDistribution(Double expectation, Double variance, Place dependentPlace) {
        super(expectation, variance, dependentPlace);
    }

    public double getExpectation() {
        return this.getFirstValue();
    }

    public void setExpectation(double expectation) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Expectation value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(expectation);
    }

    public double getVariance() {
        return this.getSecondValue();
    }

    public void setVariance(double variance) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Variance value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(variance);
    }
}
