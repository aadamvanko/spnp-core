package cz.muni.fi.spnp.core.models;

public class ConstantTransitionProbability implements TransitionProbability {

    private double value;

    public ConstantTransitionProbability(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
