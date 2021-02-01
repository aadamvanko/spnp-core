package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;

public class GeometricTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link GeometricTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param firstValue    first value of geometric distribution
     * @param secondValue   second value of geometric distribution
     */
    public GeometricTransitionDistribution(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Creates new {@link GeometricTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param firstFunction     reference to a function which calculates first value of uniform distribution
     * @param secondFunction    reference to a function which calculates second value of uniform distribution
     */
    public GeometricTransitionDistribution(Function<Double> firstFunction, Function<Double> secondFunction) {
        super(firstFunction, secondFunction);
    }

    /**
     * Creates new {@link GeometricTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue        first value of geometric distribution
     * @param secondValue       second value of geometric distribution
     * @param dependentPlace    reference to a {@link StandardPlace} object which is used for distribution
     */
    public GeometricTransitionDistribution(Double firstValue, Double secondValue, StandardPlace dependentPlace) {
        super(firstValue, secondValue, dependentPlace);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        transitionDistributionVisitor.visit(this);
    }
}
