package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.visitors.TransitionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPTimedTransition;

public interface TransitionVisitorSPNP extends TransitionVisitor {

    void visit(SPNPTimedTransition spnpTimedTransition);

}
