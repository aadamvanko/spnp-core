package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;

public class ConstantTransitionProbability implements TransitionProbability {

    private double value;

    public ConstantTransitionProbability() {
        this(0.0);
    }

    public ConstantTransitionProbability(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public void accept(TransitionProbabilityVisitor transitionProbabilityVisitor) {
        transitionProbabilityVisitor.visit(this);
    }
}
