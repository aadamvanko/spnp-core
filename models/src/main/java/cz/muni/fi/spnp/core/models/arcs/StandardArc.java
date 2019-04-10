package cz.muni.fi.spnp.core.models.arcs;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.Transition;

public class StandardArc extends Arc {

    private ArcDirection direction;
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
                       Function<Integer> calculateMultiplicityFunction) {
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

    /**
     * Gets the {@link String} representation of the arc and its parameters.
     *
     * @return representation of the arc and its parameters
     */
    @Override
    public String getDefinition() {
        switch (this.getDirection()) {
            case Input:
                String prefix = "";
                if (this.isFluid()) {
                    prefix = "f";
                } else if (this.getPlace() instanceof FluidPlace) {
                    prefix = "d";
                }

                if (this.getCalculateMultiplicityFunction() != null) {
                    return prefix + String.format("viarc(\"%s\", \"%s\", %s);",
                                                  this.getTransition().getName(),
                                                  this.getPlace().getName(),
                                                  this.getCalculateMultiplicityFunction().getName());
                } else if (this.getMultiplicity() > 1) {
                    return prefix + String.format("miarc(\"%s\", \"%s\", %d);",
                                                  this.getTransition().getName(),
                                                  this.getPlace().getName(),
                                                  this.getMultiplicity());
                } else {
                    return prefix + String.format("iarc(\"%s\", \"%s\");",
                                                  this.getTransition().getName(),
                                                  this.getPlace().getName());
                }

            case Output:
                prefix = "";
                if (this.isFluid()) {
                    prefix = "f";
                } else if (this.getPlace() instanceof FluidPlace) {
                    prefix = "d";
                }

                if (this.getCalculateMultiplicityFunction() != null) {
                    return prefix + String.format("voarc(\"%s\", \"%s\", %s);",
                                                  this.getTransition().getName(),
                                                  this.getPlace().getName(),
                                                  this.getCalculateMultiplicityFunction().getName());
                } else if (this.getMultiplicity() > 1) {
                    return prefix + String.format("moarc(\"%s\", \"%s\", %d);",
                                                  this.getTransition().getName(),
                                                  this.getPlace().getName(),
                                                  this.getMultiplicity());
                } else {
                    return prefix + String.format("oarc(\"%s\", \"%s\");",
                                                  this.getTransition().getName(),
                                                  this.getPlace().getName());
                }

            default:
                throw new IllegalStateException("Unknown Arc direction.");
        }
    }
}
