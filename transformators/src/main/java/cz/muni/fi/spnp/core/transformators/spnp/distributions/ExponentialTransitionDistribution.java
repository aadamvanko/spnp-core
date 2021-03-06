package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.utils.Constants;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class ExponentialTransitionDistribution extends SingleValueTransitionDistributionBase<Double> {

    /**
     * Creates new {@link ExponentialTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param rate constant rate to be used in distribution
     */
    public ExponentialTransitionDistribution(double rate) {
        super(rate);
    }

    /**
     * Creates new {@link ExponentialTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param rateFunction  reference to a function which calculates rate for distribution
     */
    public ExponentialTransitionDistribution(FunctionSPNP<Double> rateFunction) {
        super(rateFunction);
    }

    /**
     * Creates new {@link ExponentialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param rate              constant to be used in exponential distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public ExponentialTransitionDistribution(double rate, StandardPlace dependentPlace) {
        super(rate, dependentPlace);
    }

    public double getRate() {
        return this.getValue();
    }

    public void setRate(double rate) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate value cannot be set on Functional Transition Distribution type.");

        this.setValue(rate);
    }

    public FunctionSPNP<Double> getRateFunction() {
        return this.getFunction();
    }

    public void setRateFunction(FunctionSPNP<Double> rateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate function can be set ONLY on Functional Transition Distribution type.");

        this.setFunction(rateFunction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        var that = (ExponentialTransitionDistribution) o;

        if (this.getDistributionType() != that.getDistributionType())
            return false;

        switch (this.getDistributionType()) {
            case Constant:
                return Math.abs(this.getRate() - that.getRate()) < Constants.DOUBLE_EPSILON;
            case Functional:
                return this.getRateFunction().equals(that.getRateFunction());
            case PlaceDependent:
                return Math.abs(this.getRate() - that.getRate()) < Constants.DOUBLE_EPSILON
                        && this.getDependentPlace().equals(that.getDependentPlace());
            default:
                return false;
        }
    }

    @Override
    public int hashCode() {
        switch (this.getDistributionType()) {
            case Constant:
                return Double.hashCode(this.getRate());
            case Functional:
                return this.getRateFunction().hashCode();
            case PlaceDependent:
                return 11 * Double.hashCode(this.getRate()) +
                        17 * this.getDependentPlace().hashCode();
            default:
                return super.hashCode();
        }
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if(transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
