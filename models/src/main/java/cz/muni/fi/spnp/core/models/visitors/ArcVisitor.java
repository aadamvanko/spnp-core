package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.arcs.InhibitorArc;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;

public interface ArcVisitor {
    void visit(InhibitorArc inhibitorArc);

    void visit(StandardArc standardArc);
}
