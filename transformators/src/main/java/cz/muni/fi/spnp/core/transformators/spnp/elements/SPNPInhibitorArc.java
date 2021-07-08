package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.arcs.InhibitorArc;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;

public class SPNPInhibitorArc extends InhibitorArc {

    private final String multiplicityExpression;

    public SPNPInhibitorArc(int id, Place place, Transition transition) {
        super(id, place, transition);

        this.multiplicityExpression = "1";
    }

    public SPNPInhibitorArc(int id, Place place, Transition transition, String multiplicity) {
        super(id, place, transition, 1);

        this.multiplicityExpression = multiplicity;
    }

    public SPNPInhibitorArc(int id, Place place, Transition transition, Function calculateMultiplicityFunction) {
        super(id, place, transition, calculateMultiplicityFunction);

        this.multiplicityExpression = "unused";
    }

    public String getMultiplicityExpression() {
        return multiplicityExpression;
    }

}
