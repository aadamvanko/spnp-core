package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public abstract class ThreeValuesTransitionDistributionBase<TFirstValue, TSecondValue, TThirdValue>
        extends TransitionDistributionBase {

    protected TFirstValue firstValue;
    protected TSecondValue secondValue;
    protected TThirdValue thirdValue;

    public ThreeValuesTransitionDistributionBase(TFirstValue firstValue,
                                                 TSecondValue secondValue,
                                                 TThirdValue thirdValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public ThreeValuesTransitionDistributionBase(FunctionSPNP<TFirstValue> firstFunction,
                                                 FunctionSPNP<TSecondValue> secondFunction,
                                                 FunctionSPNP<TThirdValue> thirdFunction) {
        super(TransitionDistributionType.Functional, null);

        if (firstFunction == null)
            throw new IllegalArgumentException("First function must be set.");
        if (firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");
        if (secondFunction == null)
            throw new IllegalArgumentException("Second function must be set.");
        if (secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");
        if (thirdFunction == null)
            throw new IllegalArgumentException("Third function must be set.");
        if (thirdFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
        this.functions[1] = secondFunction;
        this.functions[2] = thirdFunction;
    }

    @Override
    protected Function[] createFunctionsArray(){
        Function[] newFunctions = {null, null, null};
        return newFunctions;
    }
    
    public ThreeValuesTransitionDistributionBase(TFirstValue firstValue,
                                                 TSecondValue secondValue,
                                                 TThirdValue thirdValue,
                                                 StandardPlace dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public TFirstValue getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(TFirstValue firstValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.firstValue = firstValue;
    }

    public TSecondValue getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(TSecondValue secondValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.secondValue = secondValue;
    }

    public TThirdValue getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(TThirdValue thirdValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.thirdValue = thirdValue;
    }

    public FunctionSPNP<TFirstValue> getFirstFunction() {
        return (FunctionSPNP<TFirstValue>) functions[0];
    }

    public void setFirstFunction(FunctionSPNP<TFirstValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
    }

    public FunctionSPNP<TSecondValue> getSecondFunction() {
        return (FunctionSPNP<TSecondValue>) functions[1];
    }

    public void setSecondFunction(FunctionSPNP<TSecondValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (secondFunction != null && secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[1] = secondFunction;
    }

    public FunctionSPNP<TThirdValue> getThirdFunction() {
        return (FunctionSPNP<TThirdValue>) functions[2];
    }

    public void setThirdFunction(FunctionSPNP<TThirdValue> thirdFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (thirdFunction != null && thirdFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[2] = thirdFunction;
    }

    @Override
    public void accept(TransitionDistributionFunctionsVisitor transitionDistributionFunctionsVisitor) {
        transitionDistributionFunctionsVisitor.visit(this);
    }
    
}
