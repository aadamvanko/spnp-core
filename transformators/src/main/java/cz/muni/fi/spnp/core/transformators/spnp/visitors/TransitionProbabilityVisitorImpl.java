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

        String definition = String.format("void probval(\"%s\", %f);",
                transition.getName(), constantTransitionProbability.getValue());
        stringBuilder.append(definition);
    }

    @Override
    public void visit(FunctionalTransitionProbability functionalTransitionProbability) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        String definition = String.format("void probfun(\"%s\", %s);",
                transition.getName(), functionalTransitionProbability.getFunction().getName());
        stringBuilder.append(definition);
    }

    @Override
    public void visit(PlaceDependentTransitionProbability placeDependentTransitionProbability) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is not specified.");

        String definition = String.format("void probdep(\"%s\", %f, \"%s\");",
                transition.getName(), placeDependentTransitionProbability.getValue(), placeDependentTransitionProbability.getDependentPlace().getName());
        stringBuilder.append(definition);
    }
}
