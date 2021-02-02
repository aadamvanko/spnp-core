package cz.muni.fi.spnp.core.models.arcs;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;

public class InhibitorArc extends Arc {

    public InhibitorArc(int id,
                        Place place,
                        Transition transition) {
        super(id, place, transition, 1);
    }

    public InhibitorArc(int id,
                        Place place,
                        Transition transition,
                        int multiplicity) {
        super(id, place, transition, multiplicity);
    }

    public InhibitorArc(int id,
                        Place place,
                        Transition transition,
                        Function<Integer> calculateMultiplicityFunction) {
        super(id, place, transition, calculateMultiplicityFunction);
    }

    @Override
    public void accept(ArcVisitor arcVisitor) {
        arcVisitor.visit(this);
    }
}
