package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.StandardPlace;

public abstract class SingleValueTransitionDistributionBase<TValue> extends TransitionDistributionBase {

    private TValue value;
    private Function<TValue> function;

    public SingleValueTransitionDistributionBase(TValue value) {
        super(TransitionDistributionType.Constant, null);

        this.value = value;
    }

    public SingleValueTransitionDistributionBase(Function<TValue> function) {
        super(TransitionDistributionType.Functional, null);

        if (function == null)
            throw new IllegalArgumentException("Function must be set.");

        this.function = function;
    }

    public SingleValueTransitionDistributionBase(TValue value, StandardPlace dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.value = value;
    }

    protected TValue getValue() {
        return value;
    }

    protected void setValue(TValue value) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.value = value;
    }

    protected Function<TValue> getFunction() {
        return function;
    }

    protected void setFunction(Function<TValue> function) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");

        this.function = function;
    }

    @Override
    protected String getFunctionsStringRepresentation() {
        return this.getFunction().getFullDefinition();
    }
}
