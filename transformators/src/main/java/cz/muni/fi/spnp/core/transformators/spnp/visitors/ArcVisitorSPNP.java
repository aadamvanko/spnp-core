package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPInhibitorArc;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPStandardArc;

public interface ArcVisitorSPNP extends ArcVisitor {

    void visit(SPNPInhibitorArc spnpInhibitorArc);

    void visit(SPNPStandardArc spnpStandardArc);

}
