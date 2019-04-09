package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class CauchyTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link CauchyTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param alphaValue alpha value of Cauchy distribution
     * @param betaValue  beta value of Cauchy distribution
     */
    public CauchyTransitionDistribution(Double alphaValue, Double betaValue) {
        super(alphaValue, betaValue);
    }

    /**
     * Creates new {@link CauchyTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param alphaValueFunction reference to a function which calculates alpha value of Cauchy distribution
     * @param betaValueFunction  reference to a function which calculates beta value of Cauchy distribution
     */
    public CauchyTransitionDistribution(Function<Double> alphaValueFunction, Function<Double> betaValueFunction) {
        super(alphaValueFunction, betaValueFunction);
    }

    /**
     * Creates new {@link CauchyTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param alphaValue        alpha value of Cauchy distribution
     * @param betaValue         beta value of Cauchy distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public CauchyTransitionDistribution(Double alphaValue, Double betaValue, StandardPlace dependentPlace) {
        super(alphaValue, betaValue, dependentPlace);
    }

    public double getAlphaValue() {
        return this.getFirstValue();
    }

    public void setAlphaValue(double alphaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(alphaValue);
    }

    public double getBetaValue() {
        return this.getSecondValue();
    }

    public void setBetaValue(double betaValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Beta value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(betaValue);
    }

    public Function<Double> getAlphaValueFunction() {
        return this.getFirstFunction();
    }

    public void setAlphaValueFunction(Function<Double> alphaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(alphaValueFunction);
    }

    public Function<Double> getBetaValueFunction() {
        return this.getSecondFunction();
    }

    public void setBetaValueFunction(Function<Double> betaValueFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Beta value function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(betaValueFunction);
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
                return String.format("void cauval(\"%s\", %f, %f);",
                        transition.getName(), this.getAlphaValue(), this.getBetaValue());

            case Functional:
                return String.format("void caufun(\"%s\", %s, %s);",
                        transition.getName(), this.getAlphaValueFunction(), this.getBetaValueFunction());

            case PlaceDependent:
                return String.format("void cauval(\"%s\", %f, %f, \"%s\");",
                        transition.getName(), this.getAlphaValue(), this.getBetaValue(), this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Cauchy transition distribution has unknown type.");
        }
    }
}
