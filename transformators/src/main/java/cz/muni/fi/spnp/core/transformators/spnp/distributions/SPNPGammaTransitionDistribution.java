package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPGammaTransitionDistribution extends SPNPTwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link SPNPGammaTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstValue  first value of gamma distribution
     * @param secondValue second value of gamma distribution
     */
    public SPNPGammaTransitionDistribution(String firstValue, String secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Creates new {@link SPNPGammaTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstValueFunction  reference to a function which calculates first value of gamma distribution
     * @param secondValueFunction reference to a function which calculates second value of gamma distribution
     */
    public SPNPGammaTransitionDistribution(FunctionSPNP<Double> firstValueFunction, FunctionSPNP<Double> secondValueFunction) {
        super(firstValueFunction, secondValueFunction);
    }

    /**
     * Creates new {@link SPNPGammaTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue     first value of gamma distribution
     * @param secondValue    second value of gamma distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPGammaTransitionDistribution(String firstValue, String secondValue, StandardPlace dependentPlace) {
        super(firstValue, secondValue, dependentPlace);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            transitionDistributionVisitor.visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
