package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public abstract class TwoValuesTransitionDistributionBase<TFirstValue, TSecondValue> extends TransitionDistributionBase {

    protected TFirstValue firstValue;
    protected TSecondValue secondValue;

    public TwoValuesTransitionDistributionBase(TFirstValue firstValue, TSecondValue secondValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public TwoValuesTransitionDistributionBase(FunctionSPNP<TFirstValue> firstFunction, FunctionSPNP<TSecondValue> secondFunction) {
        super(TransitionDistributionType.Functional, null);

        if (firstFunction == null)
            throw new IllegalArgumentException("First function must be set.");
        if (firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");
        if (secondFunction == null)
            throw new IllegalArgumentException("Second function must be set.");
        if (secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
        this.functions[1] = secondFunction;
    }

    @Override
    protected Function[] createFunctionsArray(){
        Function[] newFunctions = {null, null};
        return newFunctions;
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

    public FunctionSPNP<TFirstValue> getFirstFunction() {
        return (FunctionSPNP<TFirstValue>) functions[0];
    }

    protected void setFirstFunction(FunctionSPNP<TFirstValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
    }

    public FunctionSPNP<TSecondValue> getSecondFunction() {
        return (FunctionSPNP<TSecondValue>) functions[1];
    }

    protected void setSecondFunction(FunctionSPNP<TSecondValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (secondFunction != null && secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[1] = secondFunction;
    }

    @Override
    public void accept(TransitionDistributionFunctionsVisitor transitionDistributionFunctionsVisitor) {
        transitionDistributionFunctionsVisitor.visit(this);
    }
    
}
