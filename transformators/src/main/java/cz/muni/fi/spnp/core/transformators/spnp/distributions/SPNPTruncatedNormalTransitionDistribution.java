package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPTruncatedNormalTransitionDistribution extends SPNPTwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link SPNPTruncatedNormalTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param expectation expectation value of truncated normal distribution
     * @param variance    variance value of truncated normal distribution
     */
    public SPNPTruncatedNormalTransitionDistribution(String expectation, String variance) {
        super(expectation, variance);
    }

    /**
     * Creates new {@link SPNPTruncatedNormalTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param expectationFunction reference to a function which calculates expectation value of truncated normal distribution
     * @param varianceFunction    reference to a function which calculates variance value of truncated normal distribution
     */
    public SPNPTruncatedNormalTransitionDistribution(FunctionSPNP<Double> expectationFunction, FunctionSPNP<Double> varianceFunction) {
        super(expectationFunction, varianceFunction);
    }

    /**
     * Creates new {@link SPNPTruncatedNormalTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param expectation    expectation value of truncated normal distribution
     * @param variance       variance value of truncated normal distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPTruncatedNormalTransitionDistribution(String expectation, String variance, StandardPlace dependentPlace) {
        super(expectation, variance, dependentPlace);
    }

    public String getExpectation() {
        return this.getFirstValue();
    }

    public void setExpectation(String expectation) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Expectation value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(expectation);
    }

    public String getVariance() {
        return this.getSecondValue();
    }

    public void setVariance(String variance) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Variance value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(variance);
    }

    public FunctionSPNP<Double> getExpectationFunction() {
        return this.getFirstFunction();
    }

    public void setExpectationFunction(FunctionSPNP<Double> expectationFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Expectation function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(expectationFunction);
    }

    public FunctionSPNP<Double> getVarianceFunction() {
        return this.getSecondFunction();
    }

    public void setVarianceFunction(FunctionSPNP<Double> varianceFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Variance function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(varianceFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            transitionDistributionVisitor.visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
