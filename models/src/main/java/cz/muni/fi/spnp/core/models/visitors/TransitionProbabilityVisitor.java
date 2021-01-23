package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.FunctionalTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.PlaceDependentTransitionProbability;

public interface TransitionProbabilityVisitor {

    void visit(ConstantTransitionProbability constantTransitionProbability);

    void visit(FunctionalTransitionProbability functionalTransitionProbability);

    void visit(PlaceDependentTransitionProbability placeDependentTransitionProbability);
}
