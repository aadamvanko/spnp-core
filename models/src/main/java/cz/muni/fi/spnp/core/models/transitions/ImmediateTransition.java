package cz.muni.fi.spnp.core.models.transitions;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.TransitionProbability;

public class ImmediateTransition extends Transition {

    private TransitionProbability transitionProbability;

    public ImmediateTransition(int id,
                               String name) {
        this(id, name, 0, null, new ConstantTransitionProbability());
    }

    public ImmediateTransition(int id,
                               String name,
                               int priority,
                               Function<Integer> guardFunction,
                               TransitionProbability transitionProbability) {
        super(id, name, priority, guardFunction);

        if (transitionProbability == null)
            throw new IllegalArgumentException("Transition probability must be set.");

        this.transitionProbability = transitionProbability;
    }

    /**
     * Gets the {@link String} representation of the transition and its parameters.
     *
     * @return representation of the transition and its parameters
     */
    @Override
    public String getDefinition() {
        StringBuilder definition = new StringBuilder();

        definition.append(String.format("imm(\"%s\");", this.getName()));
        definition.append(System.lineSeparator());

        definition.append(String.format("priority(\"%s\", %d);", this.getName(), this.getPriority()));
        definition.append(System.lineSeparator());

        if (this.getGuardFunction() != null) {
            definition.append(String.format("guard(\"%s\", %s);", this.getName(), this.getGuardFunction().getName()));
            definition.append(System.lineSeparator());
        }

        if (this.getTransitionProbability() != null) {
            definition.append(this.getTransitionProbability().getDefinition(this));
            definition.append(System.lineSeparator());
        }

        return definition.toString();
    }

    public TransitionProbability getTransitionProbability() {
        return transitionProbability;
    }

    public void setTransitionProbability(TransitionProbability transitionProbability) {
        this.transitionProbability = transitionProbability;
    }
}
