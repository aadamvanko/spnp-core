package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

import java.util.Objects;

public class SPNPExponentialTransitionDistribution extends SPNPSingleValueTransitionDistributionBase {

    /**
     * Creates new {@link SPNPExponentialTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param rate constant rate to be used in distribution
     */
    public SPNPExponentialTransitionDistribution(String rate) {
        super(rate);
    }

    /**
     * Creates new {@link SPNPExponentialTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param rateFunction reference to a function which calculates rate for distribution
     */
    public SPNPExponentialTransitionDistribution(FunctionSPNP<Double> rateFunction) {
        super(rateFunction);
    }

    /**
     * Creates new {@link SPNPExponentialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param rate           constant to be used in exponential distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPExponentialTransitionDistribution(String rate, StandardPlace dependentPlace) {
        super(rate, dependentPlace);
    }

    public String getRate() {
        return this.getValue();
    }

    public void setRate(String rate) {
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

        var that = (SPNPExponentialTransitionDistribution) o;

        if (this.getDistributionType() != that.getDistributionType())
            return false;

        switch (this.getDistributionType()) {
            case Constant:
                return this.getRate().equals(that.getRate());
            case Functional:
                return this.getRateFunction().equals(that.getRateFunction());
            case PlaceDependent:
                return this.getRate().equals(that.getRate())
                        && this.getDependentPlace().equals(that.getDependentPlace());
            default:
                return false;
        }
    }

    @Override
    public int hashCode() {
        switch (this.getDistributionType()) {
            case Constant:
                return this.getRate().hashCode();
            case Functional:
                return this.getRateFunction().hashCode();
            case PlaceDependent:
                return Objects.hash(this.getRate(), this.getDependentPlace());
            default:
                return super.hashCode();
        }
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            transitionDistributionVisitor.visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
