package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPHyperExponentialTransitionDistribution extends SPNPThreeValuesTransitionDistributionBase {

    /**
     * Creates new {@link SPNPHyperExponentialTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstLambdaRate  first lambda rate value of hyper-exponential distribution
     * @param secondLambdaRate second lambda rate value of hyper-exponential distribution
     * @param probabilityValue probability value of hyper-exponential distribution
     */
    public SPNPHyperExponentialTransitionDistribution(String firstLambdaRate,
                                                      String secondLambdaRate,
                                                      String probabilityValue) {
        super(firstLambdaRate, secondLambdaRate, probabilityValue);
    }

    /**
     * Creates new {@link SPNPHyperExponentialTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstLambdaRateFunction  reference to a function which calculates first lambda rate value of hyper-exponential distribution
     * @param secondLambdaRateFunction reference to a function which calculates second lambda rate value of hyper-exponential distribution
     * @param probabilityValueFunction reference to a function which calculates probability value of hyper-exponential distribution
     */
    public SPNPHyperExponentialTransitionDistribution(FunctionSPNP<Double> firstLambdaRateFunction,
                                                      FunctionSPNP<Double> secondLambdaRateFunction,
                                                      FunctionSPNP<Double> probabilityValueFunction) {
        super(firstLambdaRateFunction, secondLambdaRateFunction, probabilityValueFunction);
    }

    /**
     * Creates new {@link SPNPHyperExponentialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstLambdaRate  first lambda rate value of hyper-exponential distribution
     * @param secondLambdaRate second lambda rate value of hyper-exponential distribution
     * @param probabilityValue probability value of hyper-exponential distribution
     * @param dependentPlace   reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPHyperExponentialTransitionDistribution(String firstLambdaRate,
                                                      String secondLambdaRate,
                                                      String probabilityValue,
                                                      StandardPlace dependentPlace) {
        super(firstLambdaRate, secondLambdaRate, probabilityValue, dependentPlace);
    }

    public String getFirstLambdaRate() {
        return this.getFirstValue();
    }

    public void setFirstLambdaRate(String firstLambdaRate) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("First lambda rate cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(firstLambdaRate);
    }

    public String getSecondLambdaRate() {
        return this.getSecondValue();
    }

    public void setSecondLambdaRate(String secondLambdaRate) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Second lambda rate cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(secondLambdaRate);
    }

    public String getProbabilityValue() {
        return this.getThirdValue();
    }

    public void setProbabilityValue(String probabilityValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Probability value cannot be set on Functional Transition Distribution type.");

        this.setThirdValue(probabilityValue);
    }

    public FunctionSPNP<Double> getFirstLambdaRateFunction() {
        return this.getFirstFunction();
    }

    public void setFirstLambdaRateFunction(FunctionSPNP<Double> firstLambdaRateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("First lambda rate function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(firstLambdaRateFunction);
    }

    public FunctionSPNP<Double> getSecondLambdaRateFunction() {
        return this.getSecondFunction();
    }

    public void setSecondLambdaRateFunction(FunctionSPNP<Double> secondLambdaRateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Second lambda rate function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(secondLambdaRateFunction);
    }

    public FunctionSPNP<Double> getProbabilityValueFunction() {
        return this.getThirdFunction();
    }

    public void setProbabilityValueFunction(FunctionSPNP<Double> probabilityValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Probability value function can be set ONLY on Functional Transition Distribution type.");

        this.setThirdFunction(probabilityValueFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            transitionDistributionVisitor.visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
