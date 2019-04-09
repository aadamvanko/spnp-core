package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class HypoExponentialTransitionDistribution extends FourValuesTransitionDistributionBase<Integer, Double, Double, Double> {

    /**
     * Creates new {@link HypoExponentialTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param numberOfStages    number of stages of hypo-exponential distribution
     * @param firstRateValue    first rate value of hypo-exponential distribution
     * @param secondRateValue   second rate value of hypo-exponential distribution
     * @param thirdRateValue    third rate value of hypo-exponential distribution
     */
    public HypoExponentialTransitionDistribution(int numberOfStages,
                                                 double firstRateValue,
                                                 double secondRateValue,
                                                 double thirdRateValue) {
        super(numberOfStages, firstRateValue, secondRateValue, thirdRateValue);
    }

    /**
     * Creates new {@link HypoExponentialTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param numberOfStagesFunction    reference to a function which calculates number of stages of hypo-exponential distribution
     * @param firstRateValueFunction    reference to a function which calculates first rate value of hypo-exponential distribution
     * @param secondRateValueFunction   reference to a function which calculates second rate value of hypo-exponential distribution
     * @param thirdRateValueFunction    reference to a function which calculates third rate value of hypo-exponential distribution
     */
    public HypoExponentialTransitionDistribution(Function<Integer> numberOfStagesFunction,
                                                 Function<Double> firstRateValueFunction,
                                                 Function<Double> secondRateValueFunction,
                                                 Function<Double> thirdRateValueFunction) {
        super(numberOfStagesFunction, firstRateValueFunction, secondRateValueFunction, thirdRateValueFunction);
    }

    /**
     * Creates new {@link HypoExponentialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param numberOfStages    number of stages of hypo-exponential distribution
     * @param firstRateValue    first rate value of hypo-exponential distribution
     * @param secondRateValue   second rate value of hypo-exponential distribution
     * @param thirdRateValue    third rate value of hypo-exponential distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public HypoExponentialTransitionDistribution(int numberOfStages,
                                                 double firstRateValue,
                                                 double secondRateValue,
                                                 double thirdRateValue,
                                                 StandardPlace dependentPlace) {
        super(numberOfStages, firstRateValue, secondRateValue, thirdRateValue, dependentPlace);
    }

    public int getNumberOfStages() {
        return this.getFirstValue();
    }

    public void setNumberOfStages(int numberOfStages) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Number of stages cannot be set on Functional Transition Distribution type.");
        if (numberOfStages != 2 && numberOfStages != 3)
            throw new IllegalArgumentException("There can be only 2 or 3 stages.");

        this.setFirstValue(numberOfStages);
    }

    public double getFirstRateValue() {
        return this.getSecondValue();
    }

    public void setFirstRateValue(double firstRateValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("First rate value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(firstRateValue);
    }

    public double getSecondRateValue() {
        return this.getThirdValue();
    }

    public void setSecondRateValue(double secondRateValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Second rate value cannot be set on Functional Transition Distribution type.");

        this.setThirdValue(secondRateValue);
    }

    public double getThirdRateValue() {
        return this.getFourthValue();
    }

    public void setThirdRateValue(double thirdRateValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Third rate value cannot be set on Functional Transition Distribution type.");

        this.setFourthValue(thirdRateValue);
    }

    public Function<Integer> getNumberOfStagesFunction() {
        return this.getFirstFunction();
    }

    public void setNumberOfStagesFunction(Function<Integer> numberOfStagesFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Number of stages function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(numberOfStagesFunction);
    }

    public Function<Double> getFirstRateValueFunction() {
        return this.getSecondFunction();
    }

    public void setFirstRateValueFunction(Function<Double> firstRateValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("First rate value function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(firstRateValueFunction);
    }

    public Function<Double> getSecondRateValueFunction() {
        return this.getThirdFunction();
    }

    public void setSecondRateValueFunction(Function<Double> secondRateValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Second rate value function can be set ONLY on Functional Transition Distribution type.");

        this.setThirdFunction(secondRateValueFunction);
    }

    public Function<Double> getThirdRateValueFunction() {
        return this.getFourthFunction();
    }

    public void setThirdRateValueFunction(Function<Double> thirdRateValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Third rate value function can be set ONLY on Functional Transition Distribution type.");

        this.setFourthFunction(thirdRateValueFunction);
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
                return String.format("void hypoval(\"%s\", %d, %f, %f, %f);",
                        transition.getName(),
                        this.getNumberOfStages(),
                        this.getFirstRateValue(),
                        this.getSecondRateValue(),
                        this.getThirdRateValue());

            case Functional:
                return String.format("void hypofun(\"%s\", %s, %s, %s, %s);",
                        transition.getName(),
                        this.getNumberOfStagesFunction().getName(),
                        this.getFirstRateValueFunction().getName(),
                        this.getSecondRateValueFunction().getName(),
                        this.getThirdRateValueFunction().getName());

            case PlaceDependent:
                return String.format("void hypodep(\"%s\", %d, %f, %f, %f, \"%s\");",
                        transition.getName(),
                        this.getNumberOfStages(),
                        this.getFirstRateValue(),
                        this.getSecondRateValue(),
                        this.getThirdRateValue(),
                        this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Hypo-exponential transition distribution has unknown type.");
        }
    }
}
