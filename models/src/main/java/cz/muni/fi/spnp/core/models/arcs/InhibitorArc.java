package cz.muni.fi.spnp.core.models.arcs;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;

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

    /**
     * Gets the {@link String} representation of the arc and its parameters.
     *
     * @return representation of the arc and its parameters
     */
    @Override
    public String getDefinition() {
        String prefix = "";

        if (this.getPlace() instanceof FluidPlace) {
            prefix += "d";
        }

        if (this.getCalculateMultiplicityFunction() != null) {
            return prefix + String.format("vharc(\"%s\", \"%s\", %s);",
                                          this.getTransition().getName(),
                                          this.getPlace().getName(),
                                          this.getCalculateMultiplicityFunction().getName());
        } else if (this.getMultiplicity() > 1) {
            return prefix + String.format("mharc(\"%s\", \"%s\", %d);",
                                          this.getTransition().getName(),
                                          this.getPlace().getName(),
                                          this.getMultiplicity());
        } else {
            return prefix + String.format("harc(\"%s\", \"%s\");",
                                          this.getTransition().getName(),
                                          this.getPlace().getName());
        }
    }
}
