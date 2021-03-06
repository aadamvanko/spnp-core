package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class CauchyTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link CauchyTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param alphaValue alpha value of Cauchy distribution
     * @param betaValue  beta value of Cauchy distribution
     */
    public CauchyTransitionDistribution(Double alphaValue, Double betaValue) {
        super(alphaValue, betaValue);
    }

    /**
     * Creates new {@link CauchyTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param alphaValueFunction reference to a function which calculates alpha value of Cauchy distribution
     * @param betaValueFunction  reference to a function which calculates beta value of Cauchy distribution
     */
    public CauchyTransitionDistribution(FunctionSPNP<Double> alphaValueFunction, FunctionSPNP<Double> betaValueFunction) {
        super(alphaValueFunction, betaValueFunction);
    }

    /**
     * Creates new {@link CauchyTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param alphaValue        alpha value of Cauchy distribution
     * @param betaValue         beta value of Cauchy distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public CauchyTransitionDistribution(Double alphaValue, Double betaValue, StandardPlace dependentPlace) {
        super(alphaValue, betaValue, dependentPlace);
    }

    public double getAlphaValue() {
        return this.getFirstValue();
    }

    public void setAlphaValue(double alphaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(alphaValue);
    }

    public double getBetaValue() {
        return this.getSecondValue();
    }

    public void setBetaValue(double betaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Beta value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(betaValue);
    }

    public FunctionSPNP<Double> getAlphaValueFunction() {
        return this.getFirstFunction();
    }

    public void setAlphaValueFunction(FunctionSPNP<Double> alphaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(alphaValueFunction);
    }

    public FunctionSPNP<Double> getBetaValueFunction() {
        return this.getSecondFunction();
    }

    public void setBetaValueFunction(FunctionSPNP<Double> betaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Beta value function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(betaValueFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if(transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
