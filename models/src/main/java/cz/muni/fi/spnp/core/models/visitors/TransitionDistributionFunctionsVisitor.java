package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;


public interface TransitionDistributionFunctionsVisitor {
    void visit(TransitionDistributionBase transitionDistributionBase);
}
