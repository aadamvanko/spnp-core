package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.utils.Constants;

import java.util.function.Supplier;

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
    public ExponentialTransitionDistribution(Supplier<Double> rateFunction) {
        super(rateFunction);
    }

    /**
     * Creates new {@link ExponentialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param rate              constant to be used in exponential distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public ExponentialTransitionDistribution(double rate, Place dependentPlace) {
        super(rate, dependentPlace);
    }

    public double getRate() {
        return this.getValue();
    }

    public void setRate(double rate) {
        this.setValue(rate);
    }

    public Supplier<Double> getRateFunction() {
        return this.getFunction();
    }

    public void setRateFunction(Supplier<Double> rateFunction) {
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
}
