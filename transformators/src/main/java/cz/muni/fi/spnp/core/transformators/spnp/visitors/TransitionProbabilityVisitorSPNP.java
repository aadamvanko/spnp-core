package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities.SPNPConstantTransitionProbability;
import cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities.SPNPPlaceDependentTransitionProbability;

public interface TransitionProbabilityVisitorSPNP extends TransitionProbabilityVisitor {

    void visit(SPNPConstantTransitionProbability spnpConstantTransitionProbability);

    void visit(SPNPPlaceDependentTransitionProbability spnpPlaceDependentTransitionProbability);

}
