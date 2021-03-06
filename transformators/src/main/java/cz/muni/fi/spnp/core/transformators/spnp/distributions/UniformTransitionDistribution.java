package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class UniformTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link UniformTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param lowerBound    lower bound value of uniform distribution
     * @param upperBound    upper bound value of uniform distribution
     */
    public UniformTransitionDistribution(double lowerBound, double upperBound) {
        super(lowerBound, upperBound);
    }

    /**
     * Creates new {@link UniformTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param lowerBoundFunction    reference to a function which calculates lower bound value of uniform distribution
     * @param upperBoundFunction    reference to a function which calculates upper bound value of uniform distribution
     */
    public UniformTransitionDistribution(FunctionSPNP<Double> lowerBoundFunction, FunctionSPNP<Double> upperBoundFunction) {
        super(lowerBoundFunction, upperBoundFunction);
    }

    /**
     * Creates new {@link UniformTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param lowerBound        lower bound value of uniform distribution
     * @param upperBound        upper bound value of uniform distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public UniformTransitionDistribution(double lowerBound, double upperBound, StandardPlace dependentPlace) {
        super(lowerBound, upperBound, dependentPlace);
    }

    public double getLowerBound() {
        return this.getFirstValue();
    }

    public void setLowerBound(double lowerBound) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Lower bound value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(lowerBound);
    }

    public double getUpperBound() {
        return this.getSecondValue();
    }

    public void setUpperBound(double upperBound) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Upper bound value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(upperBound);
    }

    public FunctionSPNP<Double> getLowerBoundFunction() {
        return this.getFirstFunction();
    }

    public void setLowerBoundFunction(FunctionSPNP<Double> lowerBoundFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lower bound function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(lowerBoundFunction);
    }

    public FunctionSPNP<Double> getUpperBoundFunction() {
        return this.getSecondFunction();
    }

    public void setUpperBoundFunction(FunctionSPNP<Double> upperBoundFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Upper bound function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(upperBoundFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if(transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
