package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;

import java.util.function.Supplier;

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
    public GeometricTransitionDistribution(Supplier<Double> firstFunction, Supplier<Double> secondFunction) {
        super(firstFunction, secondFunction);
    }

    /**
     * Creates new {@link GeometricTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param firstValue        first value of geometric distribution
     * @param secondValue       second value of geometric distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public GeometricTransitionDistribution(Double firstValue, Double secondValue, Place dependentPlace) {
        super(firstValue, secondValue, dependentPlace);
    }
}