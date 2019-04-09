package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public class ErlangTransitionDIstribution extends TwoValuesTransitionDistributionBase<Double, Integer> {

    /**
     * Creates new {@link ErlangTransitionDIstribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param rate              rate value of Erlang distribution
     * @param numberOfPhases    number of phases of Erlang distribution
     */
    public ErlangTransitionDIstribution(Double rate, Integer numberOfPhases) {
        super(rate, numberOfPhases);
    }

    /**
     * Creates new {@link ErlangTransitionDIstribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param rateFunction              reference to a function which calculates rate value of Erlang distribution
     * @param numberOfPhasesFunction    reference to a function which calculates number of phases of Erlang distribution
     */
    public ErlangTransitionDIstribution(Function<Double> rateFunction, Function<Integer> numberOfPhasesFunction) {
        super(rateFunction, numberOfPhasesFunction);
    }

    /**
     * Creates new {@link ErlangTransitionDIstribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param rate              rate value of Erlang distribution
     * @param numberOfPhases    number of phases of Erlang distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public ErlangTransitionDIstribution(Double rate, Integer numberOfPhases, StandardPlace dependentPlace) {
        super(rate, numberOfPhases, dependentPlace);
    }

    public double getRate() {
        return this.getFirstValue();
    }

    public void setRate(double rate) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(rate);
    }

    public int getNumberOfPhases() {
        return this.getSecondValue();
    }

    public void setNumberOfPhases(int numberOfPhases) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Number of phases cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(numberOfPhases);
    }

    public Function<Double> getRateFunction() {
        return this.getFirstFunction();
    }

    public void setRateFunction(Function<Double> rateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(rateFunction);
    }

    public Function<Integer> getNumberOfPhasesFunction() {
        return this.getSecondFunction();
    }

    public void setNumberOfPhasesFunction(Function<Integer> numberOfPhasesFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(numberOfPhasesFunction);
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
                return String.format("void erlval(\"%s\", %f, %d);",
                        transition.getName(), this.getRate(), this.getNumberOfPhases());

            case Functional:
                return String.format("void erlfun(\"%s\", %s, %s);",
                        transition.getName(), this.getRateFunction().getName(), this.getNumberOfPhasesFunction().getName());

            case PlaceDependent:
                return String.format("void erldep(\"%s\", %f, %d, %s);",
                        transition.getName(), this.getRate(), this.getNumberOfPhases(), this.getDependentPlace().getName());

            default:
                throw new IllegalStateException("Erlang transition distribution has unknown type.");
        }
    }
}
