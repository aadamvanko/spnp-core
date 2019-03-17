package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;

public class PoissonTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link PoissonTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param lambdaValue   lambda value of Poisson distribution
     * @param tValue        T value of Poisson distribution
     */
    public PoissonTransitionDistribution(Double lambdaValue, Double tValue) {
        super(lambdaValue, tValue);
    }

    /**
     * Creates new {@link PoissonTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param lambdaValueFunction   reference to a function which calculates lambda value of Poisson distribution
     * @param tValueFunction        reference to a function which calculates T value of Poisson distribution
     */
    public PoissonTransitionDistribution(Function<Double> lambdaValueFunction, Function<Double> tValueFunction) {
        super(lambdaValueFunction, tValueFunction);
    }

    /**
     * Creates new {@link PoissonTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param lambdaValue       lambda value of Poisson distribution
     * @param tValue            T value of Poisson distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public PoissonTransitionDistribution(Double lambdaValue, Double tValue, Place dependentPlace) {
        super(lambdaValue, tValue, dependentPlace);
    }

    public double getLambdaValue() {
        return this.getFirstValue();
    }

    public void setLambdaValue(double lambdaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(lambdaValue);
    }

    public double getTValue() {
        return this.getSecondValue();
    }

    public void setTValue(double tValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("T value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(tValue);
    }

    public Function<Double> getLambdaValueFunction() {
        return this.getFirstFunction();
    }

    public void setLambdaValueFunction(Function<Double> lambdaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(lambdaValueFunction);
    }

    public Function<Double> getTValueFunction() {
        return this.getSecondFunction();
    }

    public void setTValueFunction(Function<Double> tValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("T value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(tValueFunction);
    }
}
