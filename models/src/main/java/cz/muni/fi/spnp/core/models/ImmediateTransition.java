package cz.muni.fi.spnp.core.models;

import java.util.function.Supplier;

public class ImmediateTransition extends Transition {

    private double priority;
    private Supplier<Double> guardFunction;
    private TransitionProbability transitionProbability;

    public ImmediateTransition(int id,
                               String name,
                               TransitionProbability transitionProbability) {
        this(id, name, 0.0, null, transitionProbability);
    }

    public ImmediateTransition(int id,
                               String name,
                               double priority,
                               Supplier<Double> guardFunction,
                               TransitionProbability transitionProbability) {
        super(id, name);

        this.priority = priority;
        this.guardFunction = guardFunction;
        this.transitionProbability = transitionProbability;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Supplier<Double> getGuardFunction() {
        return guardFunction;
    }

    public void setGuardFunction(Supplier<Double> guardFunction) {
        this.guardFunction = guardFunction;
    }

    public TransitionProbability getTransitionProbability() {
        return transitionProbability;
    }

    public void setTransitionProbability(TransitionProbability transitionProbability) {
        this.transitionProbability = transitionProbability;
    }
}
