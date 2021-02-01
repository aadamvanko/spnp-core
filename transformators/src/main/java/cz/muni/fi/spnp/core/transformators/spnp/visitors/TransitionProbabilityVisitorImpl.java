package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.FunctionalTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.PlaceDependentTransitionProbability;
import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;

public class TransitionProbabilityVisitorImpl extends Visitor implements TransitionProbabilityVisitor {

    private final Transition transition;

    public TransitionProbabilityVisitorImpl(Transition transition) {
        this.transition = transition;
    }

    @Override
    public void visit(ConstantTransitionProbability constantTransitionProbability) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        String definition = String.format("probval(\"%s\", %s);",
                transition.getName(), formatDouble(constantTransitionProbability.getValue()));
        stringBuilder.append(definition);
    }

    @Override
    public void visit(FunctionalTransitionProbability functionalTransitionProbability) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        String definition = String.format("probfun(\"%s\", %s);",
                transition.getName(), functionalTransitionProbability.getFunction().getName());
        stringBuilder.append(definition);
    }

    @Override
    public void visit(PlaceDependentTransitionProbability placeDependentTransitionProbability) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        String definition = String.format("probdep(\"%s\", %s, \"%s\");",
                transition.getName(), formatDouble(placeDependentTransitionProbability.getValue()), placeDependentTransitionProbability.getDependentPlace().getName());
        stringBuilder.append(definition);
    }
}
