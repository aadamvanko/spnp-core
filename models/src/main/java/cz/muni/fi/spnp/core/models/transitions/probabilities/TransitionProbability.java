package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;

public interface TransitionProbability {

    /**
     * Gets the {@link String} representation of the immediate transition probability.
     *
     * @param transition    {@link ImmediateTransition} on which the probability is applied.
     * @return  representation of the immediate transition probability
     */
    String getDefinition(ImmediateTransition transition);
}
