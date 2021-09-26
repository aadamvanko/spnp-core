package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPTimedTransition;

import java.util.Collection;

public class TransitionVisitorImpl extends Visitor implements TransitionVisitorSPNP {

    private final Collection<Transition> transitions;

    public TransitionVisitorImpl(Collection<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public void visit(ImmediateTransition immediateTransition) {
        StringBuilder definition = new StringBuilder();

        definition.append(immediateTransition.getCommentary().getLineCommentary());
        definition.append(String.format("imm(\"%s\");", immediateTransition.getName()));
        definition.append(System.lineSeparator());

        definition.append(String.format("priority(\"%s\", %d);", immediateTransition.getName(), immediateTransition.getPriority()));
        definition.append(System.lineSeparator());

        if (immediateTransition.getGuardFunction() != null) {
            definition.append(String.format("guard(\"%s\", %s);", immediateTransition.getName(), immediateTransition.getGuardFunction().getName()));
            definition.append(System.lineSeparator());
        }

        if (immediateTransition.getTransitionProbability() != null) {
            definition.append(getTransitionProbabilityDefinition(immediateTransition));
            definition.append(System.lineSeparator());
        }

        stringBuilder.append(definition);
    }

    @Override
    public void visit(TimedTransition timedTransition) {
        String definition = timedTransition.getCommentary().getLineCommentary()
                + getTransitionDistributionDefinition(timedTransition)
                + String.format("priority(\"%s\", %d);", timedTransition.getName(), timedTransition.getPriority())
                + System.lineSeparator();

        if (timedTransition.getGuardFunction() != null) {
            definition += String.format("guard(\"%s\", %s);", timedTransition.getName(), timedTransition.getGuardFunction().getName())
                    + System.lineSeparator();
        }

        stringBuilder.append(definition);
    }

    @Override
    public void visit(SPNPTimedTransition spnpTimedTransition) {
        String definition = spnpTimedTransition.getCommentary().getLineCommentary()
                + getTransitionDistributionDefinition(spnpTimedTransition)
                + String.format("priority(\"%s\", %d);", spnpTimedTransition.getName(), spnpTimedTransition.getPriority())
                + System.lineSeparator();

        definition += String.format("policy(\"%s\", %s);%n", spnpTimedTransition.getName(), spnpTimedTransition.getPolicy().getAbbreviation());
        definition += generateAffected(spnpTimedTransition);

        if (spnpTimedTransition.getGuardFunction() != null) {
            definition += String.format("guard(\"%s\", %s);", spnpTimedTransition.getName(), spnpTimedTransition.getGuardFunction().getName())
                    + System.lineSeparator();
        }

        stringBuilder.append(definition);
    }

    private String generateAffected(SPNPTimedTransition spnpTimedTransition) {
        var affectedLines = new StringBuilder();
        transitions.forEach(transition -> {
            if (transition == spnpTimedTransition) {
                return;
            }
            affectedLines.append(String.format("affected(\"%s\", \"%s\", %s);%n",
                    spnpTimedTransition.getName(), transition.getName(), spnpTimedTransition.getAffected().getAbbreviation()));
        });
        return affectedLines.toString();
    }

    private String getTransitionProbabilityDefinition(ImmediateTransition immediateTransition) {
        TransitionProbabilityVisitorImpl transitionProbabilityVisitor = new TransitionProbabilityVisitorImpl(immediateTransition);
        immediateTransition.getTransitionProbability().accept(transitionProbabilityVisitor);
        return transitionProbabilityVisitor.getResult();
    }

    private String getTransitionDistributionDefinition(TimedTransition timedTransition) {
        TransitionDistributionVisitorImpl transitionDistributionVisitorImpl = new TransitionDistributionVisitorImpl(timedTransition);
        timedTransition.getTransitionDistribution().accept(transitionDistributionVisitorImpl);
        return transitionDistributionVisitorImpl.getResult();
    }
}
