package cz.muni.fi.spnp.core.models.transitions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistribution;

public class TimedTransition extends Transition {

    private TransitionDistribution transitionDistribution;

    public TimedTransition(int id,
                           String name,
                           TransitionDistribution transitionDistribution) {
        this(id, name, 0, null, transitionDistribution);
    }

    public TimedTransition(int id,
                           String name,
                           int priority,
                           Function<Integer> guardFunction,
                           TransitionDistribution transitionDistribution) {
        super(id, name, priority, guardFunction);

        if (transitionDistribution == null)
            throw new IllegalArgumentException("Transition distribution is null");

        this.transitionDistribution = transitionDistribution;
    }

    /**
     * Gets the {@link String} representation of the transition and its parameters.
     *
     * @return representation of the transition and its parameters
     */
    @Override
    public String getDefinition() {
        return String.format("priority(\"%s\", %d);", this.getName(), this.getPriority())
                + System.lineSeparator()
                + this.getTransitionDistribution().getDefinition(this);
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
