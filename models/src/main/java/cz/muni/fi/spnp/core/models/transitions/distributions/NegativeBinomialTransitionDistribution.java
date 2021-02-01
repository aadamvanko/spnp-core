package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;

public class NegativeBinomialTransitionDistribution extends ThreeValuesTransitionDistributionBase<Double, Double, Double> {

    /**
     * Creates new {@link NegativeBinomialTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param numberValue       number value of negative binomial distribution
     * @param probabilityValue  probability value of negative binomial distribution
     * @param tValue            T value of negative binomial distribution
     */
    public NegativeBinomialTransitionDistribution(Double numberValue,
                                                  Double probabilityValue,
                                                  Double tValue) {
        super(numberValue, probabilityValue, tValue);
    }

    /**
     * Creates new {@link NegativeBinomialTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param numberValueFunction       reference to a function which calculates number value of negative binomial distribution
     * @param probabilityValueFunction  reference to a function which calculates probability value of negative binomial distribution
     * @param tValueFunction            reference to a function which calculates T value of negative binomial distribution
     */
    public NegativeBinomialTransitionDistribution(Function<Double> numberValueFunction,
                                                  Function<Double> probabilityValueFunction,
                                                  Function<Double> tValueFunction) {
        super(numberValueFunction, probabilityValueFunction, tValueFunction);
    }

    /**
     * Creates new {@link BinomialTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param numberValue       number value of negative binomial distribution
     * @param probabilityValue  probability value of negative binomial distribution
     * @param tValue            T value of negative binomial distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public NegativeBinomialTransitionDistribution(Double numberValue,
                                                  Double probabilityValue,
                                                  Double tValue,
                                                  StandardPlace dependentPlace) {
        super(numberValue, probabilityValue, tValue, dependentPlace);
    }

    public double getNumberValue() {
        return this.getFirstValue();
    }

    public void setNumberValue(double numberValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Number value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(numberValue);
    }

    public double getProbabilityValue() {
        return this.getSecondValue();
    }

    public void setProbabilityValue(double probabilityValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Probability value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(probabilityValue);
    }

    public double getTValue() {
        return this.getThirdValue();
    }

    public void setTValue(double tValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("T value cannot be set on Functional Transition Distribution type.");

        this.setThirdValue(tValue);
    }

    public Function<Double> getNumberValueFunction() {
        return this.getFirstFunction();
    }

    public void setNumberValueFunction(Function<Double> numberValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Number value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(numberValueFunction);
    }

    public Function<Double> getProbabilityValueFunction() {
        return this.getSecondFunction();
    }

    public void setProbabilityValueFunction(Function<Double> probabilityValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Probability value function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(probabilityValueFunction);
    }

    public Function<Double> getTValueFunction() {
        return this.getThirdFunction();
    }

    public void setTValueFunction(Function<Double> tValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("T value function can be set ONLY on Functional Transition Distribution type.");

        this.setThirdFunction(tValueFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        transitionDistributionVisitor.visit(this);
    }
}
