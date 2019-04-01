package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;

public interface TransitionProbability {

    String getDefinition(ImmediateTransition transition);
}
