package cz.muni.fi.spnp.core.models.transitions;

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
}
