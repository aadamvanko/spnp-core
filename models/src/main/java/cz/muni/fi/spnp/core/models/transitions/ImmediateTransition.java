package cz.muni.fi.spnp.core.models.transitions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.TransitionProbability;
import cz.muni.fi.spnp.core.models.visitors.TransitionVisitor;

public class ImmediateTransition extends Transition {

    private TransitionProbability transitionProbability;

    public ImmediateTransition(int id,
                               String name) {
        this(id, name, 0, null, new ConstantTransitionProbability(1.0));
    }

    public ImmediateTransition(int id,
                               String name,
                               int priority,
                               Function guardFunction,
                               TransitionProbability transitionProbability) {
        super(id, name, priority, guardFunction);

        if (transitionProbability == null)
            throw new IllegalArgumentException("Transition probability must be set.");

        this.transitionProbability = transitionProbability;
    }

    public TransitionProbability getTransitionProbability() {
        return transitionProbability;
    }

    public void setTransitionProbability(TransitionProbability transitionProbability) {
        this.transitionProbability = transitionProbability;
    }

    @Override
    public void accept(TransitionVisitor transitionVisitor) {
        transitionVisitor.visit(this);
    }
}
