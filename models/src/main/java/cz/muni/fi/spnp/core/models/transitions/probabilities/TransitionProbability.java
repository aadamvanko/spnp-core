package cz.muni.fi.spnp.core.models.transitions.probabilities;

import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;

public interface TransitionProbability {
    void accept(TransitionProbabilityVisitor transitionProbabilityVisitor);
}
