package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorSPNP;

public class SPNPParetoTransitionDistribution extends SPNPTwoValuesTransitionDistributionBase<Double, Double> {

    /**
     * Creates new {@link SPNPParetoTransitionDistribution} object with {@link TransitionDistributionType#Constant} distribution type.
     *
     * @param scale scale value of Pareto distribution
     * @param alpha alpha value of Pareto distribution
     */
    public SPNPParetoTransitionDistribution(String scale, String alpha) {
        super(scale, alpha);
    }

    /**
     * Creates new {@link SPNPParetoTransitionDistribution} object with {@link TransitionDistributionType#Functional} distribution type.
     *
     * @param scaleFunction reference to a function which calculates scale value of Pareto distribution
     * @param alphaFunction reference to a function which calculates alpha value of Pareto distribution
     */
    public SPNPParetoTransitionDistribution(FunctionSPNP<Double> scaleFunction, FunctionSPNP<Double> alphaFunction) {
        super(scaleFunction, alphaFunction);
    }

    /**
     * Creates new {@link SPNPParetoTransitionDistribution} object with {@link TransitionDistributionType#PlaceDependent} distribution type.
     *
     * @param scale          scale value of Pareto distribution
     * @param alpha          alpha value of Pareto distribution
     * @param dependentPlace reference to a {@link StandardPlace} object which is used for distribution
     */
    public SPNPParetoTransitionDistribution(String scale, String alpha, StandardPlace dependentPlace) {
        super(scale, alpha, dependentPlace);
    }

    public String getScale() {
        return this.getFirstValue();
    }

    public void setScale(String scale) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Scale value cannot be set on Functional Transition Distribution type.");

        this.setFirstValue(scale);
    }

    public String getAlpha() {
        return this.getSecondValue();
    }

    public void setAlpha(String alpha) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha value cannot be set on Functional Transition Distribution type.");

        this.setSecondValue(alpha);
    }

    public FunctionSPNP<Double> getScaleFunction() {
        return this.getFirstFunction();
    }

    public void setScaleFunction(FunctionSPNP<Double> scaleFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Scale function can be set ONLY on Functional Transition Distribution type.");

        this.setFirstFunction(scaleFunction);
    }

    public FunctionSPNP<Double> getAlphaFunction() {
        return this.getSecondFunction();
    }

    public void setAlphaFunction(FunctionSPNP<Double> alphaFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Alpha function can be set ONLY on Functional Transition Distribution type.");

        this.setSecondFunction(alphaFunction);
    }

    @Override
    public void accept(TransitionDistributionVisitor transitionDistributionVisitor) {
        if (transitionDistributionVisitor instanceof TransitionDistributionVisitorSPNP)
            ((TransitionDistributionVisitorSPNP) transitionDistributionVisitor).visit(this);
        else
            transitionDistributionVisitor.visit(this);
    }
}
