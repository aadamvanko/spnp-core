package cz.muni.fi.spnp.core.models.transitions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistribution;

public class TimedTransition extends Transition {

    private TransitionDistribution transitionDistribution;

    public TimedTransition(int id,
                           String name,
                           TransitionDistribution transitionDistribution) {
        this(id, name, 0.0, null, transitionDistribution);
    }

    public TimedTransition(int id,
                           String name,
                           double priority,
                           Function<Integer> guardFunction,
                           TransitionDistribution transitionDistribution) {
        super(id, name, priority, guardFunction);

        if (transitionDistribution == null)
            throw new IllegalArgumentException("Transition distribution is null");

        this.transitionDistribution = transitionDistribution;
    }

    public TransitionDistribution getTransitionDistribution() {
        return transitionDistribution;
    }

    public void setTransitionDistribution(TransitionDistribution transitionDistribution) {
        if (transitionDistribution == null)
            throw new IllegalArgumentException("Transition distribution is null");

        this.transitionDistribution = transitionDistribution;
    }
}
