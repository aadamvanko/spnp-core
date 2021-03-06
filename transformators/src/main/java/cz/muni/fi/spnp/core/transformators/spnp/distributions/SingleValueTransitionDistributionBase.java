package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public abstract class SingleValueTransitionDistributionBase<TValue> extends TransitionDistributionBase {

    private TValue value;

    public SingleValueTransitionDistributionBase(TValue value) {
        super(TransitionDistributionType.Constant, null);

        this.value = value;
    }
    
    @Override
    protected Function[] createFunctionsArray(){
        Function[] newFunctions = {null};
        return newFunctions;
    }

    public SingleValueTransitionDistributionBase(FunctionSPNP<TValue> function) {
        super(TransitionDistributionType.Functional, null);

        if (function == null)
            throw new IllegalArgumentException("Function must be set.");
        if (function.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = function;
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

    public FunctionSPNP<TValue> getFunction() {
        return (FunctionSPNP<TValue>) functions[0];
    }

    protected void setFunction(FunctionSPNP<TValue> function) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (function != null && function.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = function;
    }

    @Override
    public void accept(TransitionDistributionFunctionsVisitor transitionDistributionFunctionsVisitor) {
        transitionDistributionFunctionsVisitor.visit(this);
    }
    
}
