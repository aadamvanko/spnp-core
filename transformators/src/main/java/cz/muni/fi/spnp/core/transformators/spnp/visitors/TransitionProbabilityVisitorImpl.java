package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.FunctionalTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.PlaceDependentTransitionProbability;
import cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities.SPNPConstantTransitionProbability;
import cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities.SPNPPlaceDependentTransitionProbability;

public class TransitionProbabilityVisitorImpl extends Visitor implements TransitionProbabilityVisitorSPNP {

    private final ImmediateTransition immediateTransition;

    public TransitionProbabilityVisitorImpl(ImmediateTransition immediateTransition) {
        if (immediateTransition == null) {
            throw new IllegalArgumentException("Immediate transition is not specified.");
        }

        this.immediateTransition = immediateTransition;
    }

    @Override
    public void visit(ConstantTransitionProbability constantTransitionProbability) {
        String definition = String.format("probval(\"%s\", %s);",
                immediateTransition.getName(), formatDouble(constantTransitionProbability.getValue()));
        stringBuilder.append(definition);
    }

    @Override
    public void visit(FunctionalTransitionProbability functionalTransitionProbability) {
        String definition = String.format("probfun(\"%s\", %s);",
                immediateTransition.getName(), functionalTransitionProbability.getFunction().getName());
        stringBuilder.append(definition);
    }

    @Override
    public void visit(PlaceDependentTransitionProbability placeDependentTransitionProbability) {
        String definition = String.format("probdep(\"%s\", %s, \"%s\");",
                immediateTransition.getName(), formatDouble(placeDependentTransitionProbability.getValue()), placeDependentTransitionProbability.getDependentPlace().getName());
        stringBuilder.append(definition);
    }

    @Override
    public void visit(SPNPConstantTransitionProbability spnpConstantTransitionProbability) {
        String definition = String.format("probval(\"%s\", %s);",
                immediateTransition.getName(), spnpConstantTransitionProbability.getValue());
        stringBuilder.append(definition);
    }

    @Override
    public void visit(SPNPPlaceDependentTransitionProbability spnpPlaceDependentTransitionProbability) {
        String definition = String.format("probdep(\"%s\", %s, \"%s\");",
                immediateTransition.getName(), spnpPlaceDependentTransitionProbability.getValue(), spnpPlaceDependentTransitionProbability.getDependentPlace().getName());
        stringBuilder.append(definition);
    }

}
