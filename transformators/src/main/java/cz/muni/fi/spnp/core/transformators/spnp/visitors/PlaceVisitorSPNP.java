package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.visitors.PlaceVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPFluidPlace;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPStandardPlace;

public interface PlaceVisitorSPNP extends PlaceVisitor {

    void visit(SPNPFluidPlace spnpFluidPlace);

    void visit(SPNPStandardPlace spnpStandardPlace);

}
