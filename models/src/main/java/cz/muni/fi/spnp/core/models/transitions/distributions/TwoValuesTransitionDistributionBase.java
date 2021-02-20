package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;

public abstract class TwoValuesTransitionDistributionBase<TFirstValue, TSecondValue> extends TransitionDistributionBase {

    protected TFirstValue firstValue;
    protected TSecondValue secondValue;
    protected Function<TFirstValue> firstFunction;
    protected Function<TSecondValue> secondFunction;

    public TwoValuesTransitionDistributionBase(TFirstValue firstValue, TSecondValue secondValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public TwoValuesTransitionDistributionBase(Function<TFirstValue> firstFunction, Function<TSecondValue> secondFunction) {
        super(TransitionDistributionType.Functional, null);

        if (firstFunction == null)
            throw new IllegalArgumentException("First function must be set.");
        if (firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");
        if (secondFunction == null)
            throw new IllegalArgumentException("Second function must be set.");
        if (secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    public TwoValuesTransitionDistributionBase(TFirstValue firstValue, TSecondValue secondValue, StandardPlace dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public TFirstValue getFirstValue() {
        return firstValue;
    }

    protected void setFirstValue(TFirstValue firstValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.firstValue = firstValue;
    }

    public TSecondValue getSecondValue() {
        return secondValue;
    }

    protected void setSecondValue(TSecondValue secondValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.secondValue = secondValue;
    }

    public Function<TFirstValue> getFirstFunction() {
        return firstFunction;
    }

    protected void setFirstFunction(Function<TFirstValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.firstFunction = firstFunction;
    }

    public Function<TSecondValue> getSecondFunction() {
        return secondFunction;
    }

    protected void setSecondFunction(Function<TSecondValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (secondFunction != null && secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.secondFunction = secondFunction;
    }

    @Override
    public void accept(TransitionDistributionFunctionsVisitor transitionDistributionFunctionsVisitor) {
        transitionDistributionFunctionsVisitor.visit(this);
    }
}
