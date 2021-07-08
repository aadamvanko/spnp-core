package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public abstract class SPNPTwoValuesTransitionDistributionBase<TFirstFunctionReturnValue, TSecondFunctionReturnValue> extends TransitionDistributionBase {

    protected String firstValue;
    protected String secondValue;

    public SPNPTwoValuesTransitionDistributionBase(String firstValue, String secondValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public SPNPTwoValuesTransitionDistributionBase(FunctionSPNP<TFirstFunctionReturnValue> firstFunction, FunctionSPNP<TSecondFunctionReturnValue> secondFunction) {
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

    public SPNPTwoValuesTransitionDistributionBase(String firstValue, String secondValue, StandardPlace dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    protected Function[] createFunctionsArray() {
        Function[] newFunctions = {null, null};
        return newFunctions;
    }

    public String getFirstValue() {
        return firstValue;
    }

    protected void setFirstValue(String firstValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    protected void setSecondValue(String secondValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.secondValue = secondValue;
    }

    public FunctionSPNP<TFirstFunctionReturnValue> getFirstFunction() {
        return (FunctionSPNP<TFirstFunctionReturnValue>) functions[0];
    }

    protected void setFirstFunction(FunctionSPNP<TFirstFunctionReturnValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
    }

    public FunctionSPNP<TSecondFunctionReturnValue> getSecondFunction() {
        return (FunctionSPNP<TSecondFunctionReturnValue>) functions[1];
    }

    protected void setSecondFunction(FunctionSPNP<TSecondFunctionReturnValue> secondFunction) {
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
