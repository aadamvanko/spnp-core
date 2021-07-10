package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.arcs.ArcDirection;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.ArcVisitorSPNP;

public class SPNPStandardArc extends StandardArc {

    private final String multiplicityExpression;

    public SPNPStandardArc(int id, ArcDirection direction, Place place, Transition transition) {
        super(id, direction, place, transition);

        this.multiplicityExpression = "1";
    }

    public SPNPStandardArc(int id, ArcDirection direction, Place place, Transition transition, String multiplicity) {
        super(id, direction, place, transition, 1);

        this.multiplicityExpression = multiplicity;
    }

    public SPNPStandardArc(int id, ArcDirection direction, Place place, Transition transition, Function calculateMultiplicityFunction) {
        super(id, direction, place, transition, calculateMultiplicityFunction);

        this.multiplicityExpression = "";
    }


    public String getMultiplicityExpression() {
        return multiplicityExpression;
    }

    @Override
    public void accept(ArcVisitor arcVisitor) {
        if (arcVisitor instanceof ArcVisitorSPNP) {
            ((ArcVisitorSPNP) arcVisitor).visit(this);
        } else {
            super.accept(arcVisitor);
        }
    }
}
