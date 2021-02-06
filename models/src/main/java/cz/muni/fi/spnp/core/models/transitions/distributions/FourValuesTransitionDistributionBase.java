package cz.muni.fi.spnp.core.models.transitions.distributions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsDeclarationsVisitor;
import cz.muni.fi.spnp.core.models.visitors.TransitionDistributionFunctionsDefinitionsVisitor;

public abstract class FourValuesTransitionDistributionBase<TFirstValue, TSecondValue, TThirdValue, TFourthValue>
        extends TransitionDistributionBase {

    protected TFirstValue firstValue;
    protected TSecondValue secondValue;
    protected TThirdValue thirdValue;
    protected TFourthValue fourthValue;
    protected Function<TFirstValue> firstFunction;
    protected Function<TSecondValue> secondFunction;
    protected Function<TThirdValue> thirdFunction;
    protected Function<TFourthValue> fourthFunction;

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

    public FourValuesTransitionDistributionBase(Function<TFirstValue> firstFunction,
                                                Function<TSecondValue> secondFunction,
                                                Function<TThirdValue> thirdFunction,
                                                Function<TFourthValue> fourthFunction) {
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

        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
        this.thirdFunction = thirdFunction;
        this.fourthFunction = fourthFunction;
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

    public Function<TFirstValue> getFirstFunction() {
        return firstFunction;
    }

    public void setFirstFunction(Function<TFirstValue> firstFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (firstFunction != null && firstFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.firstFunction = firstFunction;
    }

    public Function<TSecondValue> getSecondFunction() {
        return secondFunction;
    }

    public void setSecondFunction(Function<TSecondValue> secondFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (secondFunction != null && secondFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.secondFunction = secondFunction;
    }

    public Function<TThirdValue> getThirdFunction() {
        return thirdFunction;
    }

    public void setThirdFunction(Function<TThirdValue> thirdFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (thirdFunction != null && thirdFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.thirdFunction = thirdFunction;
    }

    public Function<TFourthValue> getFourthFunction() {
        return fourthFunction;
    }

    public void setFourthFunction(Function<TFourthValue> fourthFunction) {
        if (getDistributionType() != TransitionDistributionType.Functional)
            throw new IllegalStateException("Function can be set ONLY on Functional Transition Distribution type.");
        if (fourthFunction != null && fourthFunction.getFunctionType() != FunctionType.Distribution)
            throw new IllegalArgumentException("Function has incompatible type.");

        this.fourthFunction = fourthFunction;
    }

    @Override
    public void accept(TransitionDistributionFunctionsDefinitionsVisitor transitionDistributionFunctionsDefinitionsVisitor) {
        transitionDistributionFunctionsDefinitionsVisitor.visit(this);
    }

    @Override
    public void accept(TransitionDistributionFunctionsDeclarationsVisitor transitionDistributionFunctionsDeclarationsVisitor) {
        transitionDistributionFunctionsDeclarationsVisitor.visit(this);
    }
}
