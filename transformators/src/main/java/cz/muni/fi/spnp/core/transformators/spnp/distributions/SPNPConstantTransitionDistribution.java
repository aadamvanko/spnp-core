package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

import java.util.Objects;

public class SPNPConstantTransitionDistribution extends SPNPSingleValueTransitionDistributionBase {

    /**
     * Creates new {@link SPNPConstantTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param value constant value to be used in distribution
     */
    public SPNPConstantTransitionDistribution(String value) {
        super(value);
    }

    /**
     * Creates new {@link SPNPConstantTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param function reference to a function which calculates value for distribution
     */
    public SPNPConstantTransitionDistribution(FunctionSPNP<Double> function) {
        super(function);
    }

    /**
     * Creates new {@link SPNPConstantTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param value          constant value to be used in distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPConstantTransitionDistribution(String value, StandardPlace dependentPlace) {
        super(value, dependentPlace);
    }

    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    @Override
    public FunctionSPNP<Double> getFunction() {
        return super.getFunction();
    }

    @Override
    public void setFunction(FunctionSPNP<Double> function) {
        super.setFunction(function);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        var that = (SPNPConstantTransitionDistribution) o;

        if (this.getDistributionType() != that.getDistributionType())
            return false;

        switch (this.getDistributionType()) {
            case Constant:
                return this.getValue().equals(that.getValue());
            case Functional:
                return this.getFunction().equals(that.getFunction());
            case PlaceDependent:
                return this.getValue().equals(that.getValue())
                        && this.getDependentPlace().equals(that.getDependentPlace());
            default:
                return false;
        }
    }

    @Override
    public int hashCode() {
        switch (this.getDistributionType()) {
            case Constant:
                return getValue().hashCode();
            case Functional:
                return this.getFunction().hashCode();
            case PlaceDependent:
                return Objects.hash(getValue(), getDependentPlace());
            default:
                return super.hashCode();
        }
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
