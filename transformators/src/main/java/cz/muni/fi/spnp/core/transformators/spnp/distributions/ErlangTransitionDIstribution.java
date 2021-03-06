package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

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
    public ErlangTransitionDIstribution(FunctionSPNP<Double> rateFunction, FunctionSPNP<Integer> numberOfPhasesFunction) {
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

    public FunctionSPNP<Double> getRateFunction() {
        return this.getFirstFunction();
    }

    public void setRateFunction(FunctionSPNP<Double> rateFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(rateFunction);
    }

    public FunctionSPNP<Integer> getNumberOfPhasesFunction() {
        return this.getSecondFunction();
    }

    public void setNumberOfPhasesFunction(FunctionSPNP<Integer> numberOfPhasesFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Rate function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(numberOfPhasesFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if(transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
