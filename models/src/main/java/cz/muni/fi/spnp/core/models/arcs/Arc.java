package cz.muni.fi.spnp.core.models.arcs;

import com.google.common.base.Objects;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;

public abstract class Arc {

    private int id;
    private int multiplicity;
    private Place place;
    private Transition transition;
    private Function<Integer> calculateMultiplicityFunction;

    protected Arc(int id,
                  Place place,
                  Transition transition,
                  int multiplicity) {
        this(id, place, transition);

        if (multiplicity <= 0)
            throw new IllegalArgumentException("Arc multiplicity must be greater than zero.");

        this.multiplicity = multiplicity;
    }

    protected Arc(int id,
                  Place place,
                  Transition transition,
                  Function<Integer> calculateMultiplicityFunction) {
        this(id, place, transition);

        if (calculateMultiplicityFunction == null)
            throw new IllegalArgumentException("Multiplicity function must be defined.");

        this.calculateMultiplicityFunction = calculateMultiplicityFunction;
    }

    private Arc(int id,
                Place place,
                Transition transition) {
        if (place == null)
            throw new IllegalArgumentException("Place cannot be null");
        if (transition == null)
            throw new IllegalArgumentException("Transition cannot be null");

        this.id = id;
        this.place = place;
        this.transition = transition;
    }

    /**
     * Gets the {@link String} representation of the arc and its parameters.
     *
     * @return  representation of the arc and its parameters
     */
    public abstract String getDefinition();

    public int getId() {
        return id;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        if (place == null)
            throw new IllegalArgumentException("Place cannot be null");

        this.place = place;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition cannot be null");

        this.transition = transition;
    }

    public Function<Integer> getCalculateMultiplicityFunction() {
        return calculateMultiplicityFunction;
    }

    public void setCalculateMultiplicityFunction(Function<Integer> calculateMultiplicityFunction) {
        this.calculateMultiplicityFunction = calculateMultiplicityFunction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Arc arc = (Arc) o;

        return id == arc.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
