package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.utils.Constants;

public class ConstantTransitionDistribution extends SingleValueTransitionDistributionBase<Double> {

    /**
     * Creates new {@link ConstantTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param value constant value to be used in distribution
     */
    public ConstantTransitionDistribution(double value) {
        super(value);
    }

    /**
     * Creates new {@link ConstantTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param function  reference to a function which calculates value for distribution
     */
    public ConstantTransitionDistribution(Function<Double> function) {
        super(function);
    }

    /**
     * Creates new {@link ConstantTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param value             constant value to be used in distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public ConstantTransitionDistribution(double value, Place dependentPlace) {
        super(value, dependentPlace);
    }

    @Override
    public Double getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Double value) {
        super.setValue(value);
    }

    @Override
    public Function<Double> getFunction() {
        return super.getFunction();
    }

    @Override
    public void setFunction(Function<Double> function) {
        super.setFunction(function);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        var that = (ConstantTransitionDistribution) o;

        if (this.getDistributionType() != that.getDistributionType())
            return false;

        switch (this.getDistributionType()) {
            case Constant:
                return Math.abs(this.getValue() - that.getValue()) < Constants.DOUBLE_EPSILON;
            case Functional:
                return this.getFunction().equals(that.getFunction());
            case PlaceDependent:
                return Math.abs(this.getValue() - that.getValue()) < Constants.DOUBLE_EPSILON
                        && this.getDependentPlace().equals(that.getDependentPlace());
            default:
                return false;
        }
    }

    @Override
    public int hashCode() {
        switch (this.getDistributionType()) {
            case Constant:
                return Double.hashCode(this.getValue());
            case Functional:
                return this.getFunction().hashCode();
            case PlaceDependent:
                return 11 * Double.hashCode(this.getValue()) +
                        17 * this.getDependentPlace().hashCode();
            default:
                return super.hashCode();
        }
    }

    /**
     * Gets the {@link String} representation of the timed transition distribution definition.
     *
     * @param transition {@link TimedTransition} on which the distribution is applied.
     * @return representation of the timed transition distribution definition
     */
    @Override
    public String getDefinition(TimedTransition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        switch (this.getDistributionType()) {
            case Constant:
                return String.format("void detval(\"%s\", %f);",
                        transition.getName(), this.getValue());

            case Functional:
                return String.format("void detfun(\"%s\", %s);",
                        transition.getName(), this.getFunction().getName());

            case PlaceDependent:
                return String.format("void detdep(\"%s\", %f, %s);",
                        transition.getName(), this.getValue(), this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Constant transition distribution has unknown type.");
        }
    }
}
