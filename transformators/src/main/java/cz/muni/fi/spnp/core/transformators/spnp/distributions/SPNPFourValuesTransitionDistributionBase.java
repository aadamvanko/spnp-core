package cz.muni.fi.spnp.core.transformators.spnp.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public abstract class SPNPFourValuesTransitionDistributionBase<TFirstFunctionReturnValue, TSecondFunctionReturnValue,
        TThirdFunctionReturnValue, TFourthFunctionReturnValue>
        extends TransitionDistributionBase {

    protected String firstValue;
    protected String secondValue;
    protected String thirdValue;
    protected String fourthValue;

    public SPNPFourValuesTransitionDistributionBase(String firstValue,
                                                    String secondValue,
                                                    String thirdValue,
                                                    String fourthValue) {
        super(TransitionDistributionType.Constant, null);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    public SPNPFourValuesTransitionDistributionBase(FunctionSPNP<TFirstFunctionReturnValue> firstFunction,
                                                    FunctionSPNP<TSecondFunctionReturnValue> secondFunction,
                                                    FunctionSPNP<TThirdFunctionReturnValue> thirdFunction,
                                                    FunctionSPNP<TFourthFunctionReturnValue> fourthFunction) {
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

    public SPNPFourValuesTransitionDistributionBase(String firstValue,
                                                    String secondValue,
                                                    String thirdValue,
                                                    String fourthValue,
                                                    StandardPlace dependentPlace) {
        super(TransitionDistributionType.PlaceDependent, dependentPlace);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    @Override
    protected Function[] createFunctionsArray() {
        Function[] newFunctions = {null, null, null, null};
        return newFunctions;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.secondValue = secondValue;
    }

    public String getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(String thirdValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.thirdValue = thirdValue;
    }

    public String getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(String fourthValue) {
        if (getDistributionType() == TransitionDistributionType.Functional)
            throw new IllegalStateException("Value cannot be set on Functional Transition Distribution type.");

        this.fourthValue = fourthValue;
    }

    public FunctionSPNP<TFirstFunctionReturnValue> getFirstFunction() {
        return (FunctionSPNP<TFirstFunctionReturnValue>) this.functions[0];
    }

    public void setFirstFunction(FunctionSPNP<TFirstFunctionReturnValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[0] = firstFunction;
    }

    public FunctionSPNP<TSecondFunctionReturnValue> getSecondFunction() {
        return (FunctionSPNP<TSecondFunctionReturnValue>) this.functions[1];
    }

    public void setSecondFunction(FunctionSPNP<TSecondFunctionReturnValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (secondFunction != null && secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[1] = secondFunction;
    }

    public FunctionSPNP<TThirdFunctionReturnValue> getThirdFunction() {
        return (FunctionSPNP<TThirdFunctionReturnValue>) this.functions[2];
    }

    public void setThirdFunction(FunctionSPNP<TThirdFunctionReturnValue> thirdFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (thirdFunction != null && thirdFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.functions[2] = thirdFunction;
    }

    public FunctionSPNP<TFourthFunctionReturnValue> getFourthFunction() {
        return (FunctionSPNP<TFourthFunctionReturnValue>) this.functions[3];
    }

    public void setFourthFunction(FunctionSPNP<TFourthFunctionReturnValue> fourthFunction) {
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
