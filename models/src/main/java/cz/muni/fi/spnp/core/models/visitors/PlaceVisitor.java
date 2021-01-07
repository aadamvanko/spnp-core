package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;

public interface PlaceVisitor {

    void visit(FluidPlace fluidPlace);

    void visit(StandardPlace standardPlace);

}
