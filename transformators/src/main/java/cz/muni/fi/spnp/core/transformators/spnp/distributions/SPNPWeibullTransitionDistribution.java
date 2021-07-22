package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPWeibullTransitionDistribution extends SPNPTwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link SPNPWeibullTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param alphaValue  alpha value of Weibull distribution
     * @param lambdaValue lambda value of Weibull distribution
     */
    public SPNPWeibullTransitionDistribution(String alphaValue, String lambdaValue) {
        super(alphaValue, lambdaValue);
    }

    /**
     * Creates new {@link SPNPWeibullTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param alphaValueFunction  reference to a function which calculates alpha value of Weibull distribution
     * @param lambdaValueFunction reference to a function which calculates lambda value of Weibull distribution
     */
    public SPNPWeibullTransitionDistribution(FunctionSPNP<Double> alphaValueFunction, FunctionSPNP<Double> lambdaValueFunction) {
        super(alphaValueFunction, lambdaValueFunction);
    }

    /**
     * Creates new {@link SPNPWeibullTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param alphaValue     alpha value of Weibull distribution
     * @param lambdaValue    lambda value of Weibull distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPWeibullTransitionDistribution(String alphaValue, String lambdaValue, StandardPlace dependentPlace) {
        super(alphaValue, lambdaValue, dependentPlace);
    }

    public String getAlphaValue() {
        return this.getFirstValue();
    }

    public void setAlphaValue(String alphaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(alphaValue);
    }

    public String getLambdaValue() {
        return this.getSecondValue();
    }

    public void setLambdaValue(String lambdaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(lambdaValue);
    }

    public FunctionSPNP<Double> getAlphaValueFunction() {
        return this.getFirstFunction();
    }

    public void setAlphaValueFunction(FunctionSPNP<Double> alphaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(alphaValueFunction);
    }

    public FunctionSPNP<Double> getLambdaValueFunction() {
        return this.getSecondFunction();
    }

    public void setLambdaValueFunction(FunctionSPNP<Double> lambdaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(lambdaValueFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
