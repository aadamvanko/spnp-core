package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

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
    public UniformTransitionDistribution(Function<Double> lowerBoundFunction, Function<Double> upperBoundFunction) {
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

    public Function<Double> getLowerBoundFunction() {
        return this.getFirstFunction();
    }

    public void setLowerBoundFunction(Function<Double> lowerBoundFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lower bound function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(lowerBoundFunction);
    }

    public Function<Double> getUpperBoundFunction() {
        return this.getSecondFunction();
    }

    public void setUpperBoundFunction(Function<Double> upperBoundFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Upper bound function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(upperBoundFunction);
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
                return String.format("void unifval(\"%s\", %f, %f);",
                        transition.getName(), this.getLowerBound(), this.getUpperBound());

            case Functional:
                return String.format("void uniffun(\"%s\", %s, %s);",
                        transition.getName(),
                        this.getLowerBoundFunction().getName(),
                        this.getUpperBoundFunction().getName());

            case PlaceDependent:
                return String.format("void unifdep(\"%s\", %f, %f, \"%s\");",
                        transition.getName(), this.getLowerBound(), this.getUpperBound(), this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Uniform transition distribution has unknown type.");
        }
    }
}
