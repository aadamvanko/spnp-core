package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.places.Place;

import java.util.function.Supplier;

public abstract class TwoValuesTransitionDistributionBase<TFirstValue, TSecondValue> extends TransitionDistributionBase {

    protected TFirstValue firstValue;
    protected TSecondValue secondValue;
    protected Supplier<TFirstValue> firstFunction;
    protected Supplier<TSecondValue> secondFunction;

    public TwoValuesTransitionDistributionBase(TFirstValue firstValue, TSecondValue secondValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public TwoValuesTransitionDistributionBase(Supplier<TFirstValue> firstFunction, Supplier<TSecondValue> secondFunction) {
        super(TransitionDistributionType.Functional, null);

        if (firstFunction == null)
            throw new IllegalArgumentException("First function must be set.");
        if (secondFunction == null)
            throw new IllegalArgumentException("Second function must be set.");

        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    public TwoValuesTransitionDistributionBase(TFirstValue firstValue, TSecondValue secondValue, Place dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    protected TFirstValue getFirstValue() {
        return firstValue;
    }

    protected void setFirstValue(TFirstValue firstValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.firstValue = firstValue;
    }

    protected TSecondValue getSecondValue() {
        return secondValue;
    }

    protected void setSecondValue(TSecondValue secondValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.secondValue = secondValue;
    }

    protected Supplier<TFirstValue> getFirstFunction() {
        return firstFunction;
    }

    protected void setFirstFunction(Supplier<TFirstValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");

        this.firstFunction = firstFunction;
    }

    protected Supplier<TSecondValue> getSecondFunction() {
        return secondFunction;
    }

    protected void setSecondFunction(Supplier<TSecondValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");

        this.secondFunction = secondFunction;
    }
}
