package cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities;

import cz.muni.fi.spnp.core.models.transitions.probabilities.TransitionProbability;
import cz.muni.fi.spnp.core.models.visitors.TransitionProbabilityVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionProbabilityVisitorSPNP;

public class SPNPConstantTransitionProbability implements TransitionProbability {

    private String value;

    public SPNPConstantTransitionProbability() {
        this("0.0");
    }

    public SPNPConstantTransitionProbability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void accept(TransitionProbabilityVisitor transitionProbabilityVisitor) {
        if (transitionProbabilityVisitor instanceof TransitionProbabilityVisitorSPNP) {
            ((TransitionProbabilityVisitorSPNP) transitionProbabilityVisitor).visit(this);
        }
    }

}
