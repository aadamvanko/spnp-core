package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPGeometricTransitionDistribution extends SPNPTwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link SPNPGeometricTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstValue  first value of geometric distribution
     * @param secondValue second value of geometric distribution
     */
    public SPNPGeometricTransitionDistribution(String firstValue, String secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Creates new {@link SPNPGeometricTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstFunction  reference to a function which calculates first value of uniform distribution
     * @param secondFunction reference to a function which calculates second value of uniform distribution
     */
    public SPNPGeometricTransitionDistribution(FunctionSPNP<Double> firstFunction, FunctionSPNP<Double> secondFunction) {
        super(firstFunction, secondFunction);
    }

    /**
     * Creates new {@link SPNPGeometricTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue     first value of geometric distribution
     * @param secondValue    second value of geometric distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPGeometricTransitionDistribution(String firstValue, String secondValue, StandardPlace dependentPlace) {
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
