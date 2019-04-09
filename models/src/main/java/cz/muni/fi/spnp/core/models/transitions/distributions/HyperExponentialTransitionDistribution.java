package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class HyperExponentialTransitionDistribution extends ThreeValuesTransitionDistributionBase<Double, Double, Double> {

    /**
     * Creates new {@link HyperExponentialTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstLambdaRate   first lambda rate value of hyper-exponential distribution
     * @param secondLambdaRate  second lambda rate value of hyper-exponential distribution
     * @param probabilityValue  probability value of hyper-exponential distribution
     */
    public HyperExponentialTransitionDistribution(Double firstLambdaRate,
                                                  Double secondLambdaRate,
                                                  Double probabilityValue) {
        super(firstLambdaRate, secondLambdaRate, probabilityValue);
    }

    /**
     * Creates new {@link HyperExponentialTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstLambdaRateFunction   reference to a function which calculates first lambda rate value of hyper-exponential distribution
     * @param secondLambdaRateFunction  reference to a function which calculates second lambda rate value of hyper-exponential distribution
     * @param probabilityValueFunction  reference to a function which calculates probability value of hyper-exponential distribution
     */
    public HyperExponentialTransitionDistribution(Function<Double> firstLambdaRateFunction,
                                                  Function<Double> secondLambdaRateFunction,
                                                  Function<Double> probabilityValueFunction) {
        super(firstLambdaRateFunction, secondLambdaRateFunction, probabilityValueFunction);
    }

    /**
     * Creates new {@link HyperExponentialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstLambdaRate   first lambda rate value of hyper-exponential distribution
     * @param secondLambdaRate  second lambda rate value of hyper-exponential distribution
     * @param probabilityValue  probability value of hyper-exponential distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public HyperExponentialTransitionDistribution(Double firstLambdaRate,
                                                  Double secondLambdaRate,
                                                  Double probabilityValue,
                                                  StandardPlace dependentPlace) {
        super(firstLambdaRate, secondLambdaRate, probabilityValue, dependentPlace);
    }

    public double getFirstLambdaRate() {
        return this.getFirstValue();
    }

    public void setFirstLambdaRate(double firstLambdaRate) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("First lambda rate cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(firstLambdaRate);
    }

    public double getSecondLambdaRate() {
        return this.getSecondValue();
    }

    public void setSecondLambdaRate(double secondLambdaRate) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Second lambda rate cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(secondLambdaRate);
    }

    public double getProbabilityValue() {
        return this.getThirdValue();
    }

    public void setProbabilityValue(double probabilityValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Probability value cannot be set on Functional Transition Distribution type.");

        this.setThirdValue(probabilityValue);
    }

    public Function<Double> getFirstLambdaRateFunction() {
        return this.getFirstFunction();
    }

    public void setFirstLambdaRateFunction(Function<Double> firstLambdaRateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("First lambda rate function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(firstLambdaRateFunction);
    }

    public Function<Double> getSecondLambdaRateFunction() {
        return this.getSecondFunction();
    }

    public void setSecondLambdaRateFunction(Function<Double> secondLambdaRateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Second lambda rate function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(secondLambdaRateFunction);
    }

    public Function<Double> getProbabilityValueFunction() {
        return this.getThirdFunction();
    }

    public void setProbabilityValueFunction(Function<Double> probabilityValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Probability value function can be set ONLY on Functional Transition Distribution type.");

        this.setThirdFunction(probabilityValueFunction);
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
                return String.format("void hyperval(\"%s\", %f, %f, %f);",
                        transition.getName(), this.getFirstLambdaRate(), this.getSecondLambdaRate(), this.getProbabilityValue());

            case Functional:
                return String.format("void hyperfun(\"%s\", %s, %s, %s);",
                        transition.getName(),
                        this.getFirstLambdaRateFunction().getName(),
                        this.getSecondLambdaRateFunction().getName(),
                        this.getProbabilityValueFunction().getName());

            case PlaceDependent:
                return String.format("void hyperdep(\"%s\", %f, %f, %f, \"%s\");",
                        transition.getName(),
                        this.getFirstLambdaRate(),
                        this.getSecondLambdaRate(),
                        this.getProbabilityValue(),
                        this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Hyper-exponential transition distribution has unknown type.");
        }
    }
}
