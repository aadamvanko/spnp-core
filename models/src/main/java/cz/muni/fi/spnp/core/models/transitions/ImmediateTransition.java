package cz.muni.fi.spnp.core.models.transitions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.TransitionProbability;

public class ImmediateTransition extends Transition {

    private TransitionProbability transitionProbability;

    public ImmediateTransition(int id,
                               String name) {
        this(id, name, 0.0, null, new ConstantTransitionProbability());
    }

    public ImmediateTransition(int id,
                               String name,
                               double priority,
                               Function<Integer> guardFunction,
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
}
