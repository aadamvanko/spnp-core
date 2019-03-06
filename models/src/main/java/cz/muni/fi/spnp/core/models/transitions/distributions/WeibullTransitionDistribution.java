package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;

import java.util.function.Supplier;

public class WeibullTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link WeibullTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param alphaValue    alpha value of Weibull distribution
     * @param lambdaValue   lambda value of Weibull distribution
     */
    public WeibullTransitionDistribution(Double alphaValue, Double lambdaValue) {
        super(alphaValue, lambdaValue);
    }

    /**
     * Creates new {@link WeibullTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param alphaValueFunction    reference to a function which calculates alpha value of Weibull distribution
     * @param lambdaValueFunction   reference to a function which calculates lambda value of Weibull distribution
     */
    public WeibullTransitionDistribution(Supplier<Double> alphaValueFunction, Supplier<Double> lambdaValueFunction) {
        super(alphaValueFunction, lambdaValueFunction);
    }

    /**
     * Creates new {@link WeibullTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param alphaValue        alpha value of Weibull distribution
     * @param lambdaValue       lambda value of Weibull distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public WeibullTransitionDistribution(Double alphaValue, Double lambdaValue, Place dependentPlace) {
        super(alphaValue, lambdaValue, dependentPlace);
    }

    public double getAlphaValue() {
        return this.getFirstValue();
    }

    public void setAlphaValue(double alphaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(alphaValue);
    }

    public double getLambdaValue() {
        return this.getSecondValue();
    }

    public void setLambdaValue(double lambdaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(lambdaValue);
    }

    public Supplier<Double> getAlphaValueFunction() {
        return this.getFirstFunction();
    }

    public void setAlphaValueFunction(Supplier<Double> alphaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(alphaValueFunction);
    }

    public Supplier<Double> getLambdaValueFunction() {
        return this.getSecondFunction();
    }

    public void setLambdaValueFunction(Supplier<Double> lambdaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(lambdaValueFunction);
    }
}
