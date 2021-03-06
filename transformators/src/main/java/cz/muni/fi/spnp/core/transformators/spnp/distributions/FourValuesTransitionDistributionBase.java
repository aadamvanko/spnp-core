package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public abstract class FourValuesTransitionDistributionBase<TFirstValue, TSecondValue, TThirdValue, TFourthValue>
        extends TransitionDistributionBase {

    protected TFirstValue firstValue;
    protected TSecondValue secondValue;
    protected TThirdValue thirdValue;
    protected TFourthValue fourthValue;

    public FourValuesTransitionDistributionBase(TFirstValue firstValue,
                                                TSecondValue secondValue,
                                                TThirdValue thirdValue,
                                                TFourthValue fourthValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    public FourValuesTransitionDistributionBase(FunctionSPNP<TFirstValue> firstFunction,
                                                FunctionSPNP<TSecondValue> secondFunction,
                                                FunctionSPNP<TThirdValue> thirdFunction,
                                                FunctionSPNP<TFourthValue> fourthFunction) {
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
        if (fourthFunction == null)
            throw new IllegalArgumentException("Fourth function must be set.");
        if (fourthFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
        this.functions[1] = secondFunction;
        this.functions[2] = thirdFunction;
        this.functions[3] = fourthFunction;
    }

    @Override
    protected Function[] createFunctionsArray(){
        Function[] newFunctions = {null, null, null, null};
        return newFunctions;
    }
    
    public FourValuesTransitionDistributionBase(TFirstValue firstValue,
                                                TSecondValue secondValue,
                                                TThirdValue thirdValue,
                                                TFourthValue fourthValue,
                                                StandardPlace dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
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

    public TFourthValue getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(TFourthValue fourthValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.fourthValue = fourthValue;
    }

    public FunctionSPNP<TFirstValue> getFirstFunction() {
        return (FunctionSPNP<TFirstValue>) this.functions[0];
    }

    public void setFirstFunction(FunctionSPNP<TFirstValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
    }

    public FunctionSPNP<TSecondValue> getSecondFunction() {
        return (FunctionSPNP<TSecondValue>) this.functions[1];
    }

    public void setSecondFunction(FunctionSPNP<TSecondValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (secondFunction != null && secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[1] = secondFunction;
    }

    public FunctionSPNP<TThirdValue> getThirdFunction() {
        return (FunctionSPNP<TThirdValue>) this.functions[2];
    }

    public void setThirdFunction(FunctionSPNP<TThirdValue> thirdFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (thirdFunction != null && thirdFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[2] = thirdFunction;
    }

    public FunctionSPNP<TFourthValue> getFourthFunction() {
        return (FunctionSPNP<TFourthValue>) this.functions[3];
    }

    public void setFourthFunction(FunctionSPNP<TFourthValue> fourthFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (fourthFunction != null && fourthFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[3] = fourthFunction;
    }

    @Override
    public void accept(TransitionDistributionFunctionsVisitor transitionDistributionFunctionsVisitor) {
        transitionDistributionFunctionsVisitor.visit(this);
    }
    
}
