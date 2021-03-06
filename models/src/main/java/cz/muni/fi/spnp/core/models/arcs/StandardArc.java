package cz.muni.fi.spnp.core.models.arcs;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;

public class StandardArc extends Arc {

    private final ArcDirection direction;
    private boolean isFluid;

    public StandardArc(int id,
                       ArcDirection direction,
                       Place place,
                       Transition transition) {
        super(id, place, transition, 1);

        this.direction = direction;
    }

    public StandardArc(int id,
                       ArcDirection direction,
                       Place place,
                       Transition transition,
                       int multiplicity) {
        super(id, place, transition, multiplicity);

        this.direction = direction;
    }

    public StandardArc(int id,
                       ArcDirection direction,
                       Place place,
                       Transition transition,
                       Function calculateMultiplicityFunction) {
        super(id, place, transition, calculateMultiplicityFunction);

        this.direction = direction;
    }

    public ArcDirection getDirection() {
        return direction;
    }

    public boolean isFluid() {
        return isFluid;
    }

    public void setFluid(boolean isFluid) {
        this.isFluid = isFluid;
    }

    @Override
    public void accept(ArcVisitor arcVisitor) {
        arcVisitor.visit(this);
    }
}
