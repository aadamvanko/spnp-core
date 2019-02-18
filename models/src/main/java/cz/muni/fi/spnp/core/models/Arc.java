package cz.muni.fi.spnp.core.models;

import com.google.common.base.Objects;

import java.util.function.Supplier;

public class Arc {

    private int id;
    private int multiplicity;
    private Supplier<Integer> calculateMultiplicityFunction;
    private boolean isFluid;

    public Arc(int id) {
        this.id = id;
    }

    public Arc(int id, int multiplicity) {
        this(id);

        if (multiplicity <= 0)
            throw new IllegalArgumentException("Arc multiplicity must be greater than zero.");
        this.multiplicity = multiplicity;
    }

    public Arc(int id, Supplier<Integer> calculateMultiplicityFunction) {
        this(id);

        if (calculateMultiplicityFunction == null)
            throw new IllegalArgumentException("Calculate multiplicity function must be defined.");

        this.calculateMultiplicityFunction = calculateMultiplicityFunction;
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

    public Supplier<Integer> getCalculateMultiplicityFunction() {
        return calculateMultiplicityFunction;
    }

    public void setCalculateMultiplicityFunction(Supplier<Integer> calculateMultiplicityFunction) {
        if (calculateMultiplicityFunction == null)
            throw new IllegalArgumentException("Calculate multiplicity function is null.");

        this.calculateMultiplicityFunction = calculateMultiplicityFunction;
    }

    public boolean isFluid() {
        return isFluid;
    }

    public void setFluid(boolean isFluid) {
        this.isFluid = isFluid;
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
