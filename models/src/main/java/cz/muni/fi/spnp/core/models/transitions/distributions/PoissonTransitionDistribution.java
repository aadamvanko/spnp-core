package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class PoissonTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link PoissonTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param lambdaValue   lambda value of Poisson distribution
     * @param tValue        T value of Poisson distribution
     */
    public PoissonTransitionDistribution(Double lambdaValue, Double tValue) {
        super(lambdaValue, tValue);
    }

    /**
     * Creates new {@link PoissonTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param lambdaValueFunction   reference to a function which calculates lambda value of Poisson distribution
     * @param tValueFunction        reference to a function which calculates T value of Poisson distribution
     */
    public PoissonTransitionDistribution(Function<Double> lambdaValueFunction, Function<Double> tValueFunction) {
        super(lambdaValueFunction, tValueFunction);
    }

    /**
     * Creates new {@link PoissonTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param lambdaValue       lambda value of Poisson distribution
     * @param tValue            T value of Poisson distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public PoissonTransitionDistribution(Double lambdaValue, Double tValue, StandardPlace dependentPlace) {
        super(lambdaValue, tValue, dependentPlace);
    }

    public double getLambdaValue() {
        return this.getFirstValue();
    }

    public void setLambdaValue(double lambdaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(lambdaValue);
    }

    public double getTValue() {
        return this.getSecondValue();
    }

    public void setTValue(double tValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("T value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(tValue);
    }

    public Function<Double> getLambdaValueFunction() {
        return this.getFirstFunction();
    }

    public void setLambdaValueFunction(Function<Double> lambdaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Lambda value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(lambdaValueFunction);
    }

    public Function<Double> getTValueFunction() {
        return this.getSecondFunction();
    }

    public void setTValueFunction(Function<Double> tValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("T value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(tValueFunction);
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
                return String.format("void poisval(\"%s\", %f, %f);",
                        transition.getName(), this.getLambdaValue(), this.getTValue());

            case Functional:
                return String.format("void poisfun(\"%s\", %s, %s);",
                        transition.getName(), this.getLambdaValueFunction().getName(), this.getTValueFunction().getName());

            case PlaceDependent:
                return String.format("void poisdep(\"%s\", %f, %f, \"%s\");",
                        transition.getName(), this.getLambdaValue(), this.getTValue(), this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Poisson transition distribution has unknown type.");
        }
    }
}
