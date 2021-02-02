package cz.muni.fi.spnp.core.models.arcs;

import com.google.common.base.Objects;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;

public abstract class Arc implements Comparable<Arc> {

    private final int id;
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
        if (calculateMultiplicityFunction.getFunctionType() != FunctionType.ArcCardinality)
            throw new IllegalArgumentException("Multiplicity function has incompatible type.");

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
        if (calculateMultiplicityFunction != null && calculateMultiplicityFunction.getFunctionType() != FunctionType.ArcCardinality)
            throw new IllegalArgumentException("Multiplicity function has incompatible type.");

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

    public int compareTo(Arc other) {
        return Integer.compare(id, other.getId());
    }

    public abstract void accept(ArcVisitor arcVisitor);
}
