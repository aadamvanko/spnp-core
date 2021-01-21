package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.visitors.TransitionVisitor;

public class TransitionVisitorImpl extends Visitor implements TransitionVisitor {

    @Override
    public void visit(ImmediateTransition immediateTransition) {
        StringBuilder definition = new StringBuilder();

        definition.append(String.format("imm(\"%s\");", immediateTransition.getName()));
        definition.append(System.lineSeparator());

        definition.append(String.format("priority(\"%s\", %d);", immediateTransition.getName(), immediateTransition.getPriority()));
        definition.append(System.lineSeparator());

        if (immediateTransition.getGuardFunction() != null) {
            definition.append(String.format("guard(\"%s\", %s);", immediateTransition.getName(), immediateTransition.getGuardFunction().getName()));
            definition.append(System.lineSeparator());
        }

        if (immediateTransition.getTransitionProbability() != null) {
            definition.append(immediateTransition.getTransitionProbability().getDefinition(immediateTransition));
            definition.append(System.lineSeparator());
        }

        stringBuilder.append(definition);
    }

    @Override
    public void visit(TimedTransition timedTransition) {
        String definition = String.format("priority(\"%s\", %d);", timedTransition.getName(), timedTransition.getPriority())
                + System.lineSeparator()
                + timedTransition.getTransitionDistribution().getDefinition(timedTransition);
        stringBuilder.append(definition);
    }
}
