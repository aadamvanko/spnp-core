package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPPoissonTransitionDistribution extends SPNPTwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link SPNPPoissonTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param lambdaValue lambda value of Poisson distribution
     * @param tValue      T value of Poisson distribution
     */
    public SPNPPoissonTransitionDistribution(String lambdaValue, String tValue) {
        super(lambdaValue, tValue);
    }

    /**
     * Creates new {@link SPNPPoissonTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param lambdaValueFunction reference to a function which calculates lambda value of Poisson distribution
     * @param tValueFunction      reference to a function which calculates T value of Poisson distribution
     */
    public SPNPPoissonTransitionDistribution(FunctionSPNP<Double> lambdaValueFunction, FunctionSPNP<Double> tValueFunction) {
        super(lambdaValueFunction, tValueFunction);
    }

    /**
     * Creates new {@link SPNPPoissonTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param lambdaValue    lambda value of Poisson distribution
     * @param tValue         T value of Poisson distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPPoissonTransitionDistribution(String lambdaValue, String tValue, StandardPlace dependentPlace) {
        super(lambdaValue, tValue, dependentPlace);
    }

    public String getLambdaValue() {
        return this.getFirstValue();
    }

    public void setLambdaValue(String lambdaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(lambdaValue);
    }

    public String getTValue() {
        return this.getSecondValue();
    }

    public void setTValue(String tValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("T value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(tValue);
    }

    public FunctionSPNP<Double> getLambdaValueFunction() {
        return this.getFirstFunction();
    }

    public void setLambdaValueFunction(FunctionSPNP<Double> lambdaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(lambdaValueFunction);
    }

    public FunctionSPNP<Double> getTValueFunction() {
        return this.getSecondFunction();
    }

    public void setTValueFunction(FunctionSPNP<Double> tValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("T value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(tValueFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
