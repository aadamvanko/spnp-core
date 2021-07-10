package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.arcs.InhibitorArc;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.ArcVisitorSPNP;

public class SPNPInhibitorArc extends InhibitorArc {

    private final String multiplicityExpression;

    public SPNPInhibitorArc(int id, Place place, Transition transition) {
        super(id, place, transition);

        this.multiplicityExpression = "1";
    }

    public SPNPInhibitorArc(int id, Place place, Transition transition, String multiplicityExpression) {
        super(id, place, transition, 1);

        this.multiplicityExpression = multiplicityExpression;
    }

    public SPNPInhibitorArc(int id, Place place, Transition transition, Function calculateMultiplicityFunction) {
        super(id, place, transition, calculateMultiplicityFunction);

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
