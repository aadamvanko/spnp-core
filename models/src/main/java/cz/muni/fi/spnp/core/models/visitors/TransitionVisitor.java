package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;

public interface TransitionVisitor {

    void visit(ImmediateTransition immediateTransition);

    void visit(TimedTransition timedTransition);

}
