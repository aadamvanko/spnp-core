package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;

public class ParetoTransitionDistribution extends TwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link ParetoTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param scale scale value of Pareto distribution
     * @param alpha alpha value of Pareto distribution
     */
    public ParetoTransitionDistribution(Double scale, Double alpha) {
        super(scale, alpha);
    }

    /**
     * Creates new {@link ParetoTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param scaleFunction reference to a function which calculates scale value of Pareto distribution
     * @param alphaFunction reference to a function which calculates alpha value of Pareto distribution
     */
    public ParetoTransitionDistribution(Function<Double> scaleFunction, Function<Double> alphaFunction) {
        super(scaleFunction, alphaFunction);
    }

    /**
     * Creates new {@link ParetoTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param scale             scale value of Pareto distribution
     * @param alpha             alpha value of Pareto distribution
     * @param dependentPlace    reference to a {@link Place} object which is used for distribution
     */
    public ParetoTransitionDistribution(Double scale, Double alpha, Place dependentPlace) {
        super(scale, alpha, dependentPlace);
    }

    public double getScale() {
        return this.getFirstValue();
    }

    public void setScale(double scale) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Scale value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(scale);
    }

    public double getAlpha() {
        return this.getSecondValue();
    }

    public void setAlpha(double alpha) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(alpha);
    }

    public Function<Double> getScaleFunction() {
        return this.getFirstFunction();
    }

    public void setScaleFunction(Function<Double> scaleFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Scale function can be set ONLY on Functional Transition Distribution type.");
    }

    public Function<Double> getAlphaFunction() {
        return this.getSecondFunction();
    }

    public void setAlphaFunction(Function<Double> alphaFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(alphaFunction);
    }
}
