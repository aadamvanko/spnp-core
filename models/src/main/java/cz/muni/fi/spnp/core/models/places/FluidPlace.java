package cz.muni.fi.spnp.core.models.places;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FluidPlace extends Place {

    private double initialValue;
    private double boundValue;
    private Set<Double> breakValues;

    public FluidPlace(int id, String name) {
        this(id, name, 0.0, 0.0, null);
    }

    public FluidPlace(int id,
                      String name,
                      double initialValue,
                      double boundValue,
                      Set<Double> breakValues) {
        super(id, name);

        this.initialValue = initialValue;
        this.boundValue = boundValue;
        this.breakValues = Objects.requireNonNullElseGet(breakValues, HashSet::new);
    }

    /**
     * Gets the {@link String} representation of the place and its parameters.
     *
     * @return representation of the place and its parameters
     */
    @Override
    public String getDefinition() {
        StringBuilder definition = new StringBuilder();

        definition.append(String.format("fplace(\"%s\");", this.getName()));
        definition.append(System.lineSeparator());

        if (this.getInitialValue() > 0) {
            definition.append(String.format("finit(\"%s\", %f);", this.getName(), this.getInitialValue()));
            definition.append(System.lineSeparator());
        }
        if (this.getBoundValue() > 0.0) {
            definition.append(String.format("fbound(\"%s\", %f);", this.getName(), this.getBoundValue()));
            definition.append(System.lineSeparator());
        }
        if (!this.getBreakValues().isEmpty()) {
            for (var breakValue : this.getBreakValues()) {
                definition.append(String.format("fbreak(\"%s\", %f);", this.getName(), breakValue));
                definition.append(System.lineSeparator());
            }
        }

        return definition.toString();
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public double getBoundValue() {
        return boundValue;
    }

    public void setBoundValue(double boundValue) {
        this.boundValue = boundValue;
    }

    public Set<Double> getBreakValues() {
        return Collections.unmodifiableSet(breakValues);
    }

    public void addBreakValue(double breakValue) {
        this.breakValues.add(breakValue);
    }

    public void removeBreakValue(double breakValue) {
        this.breakValues.remove(breakValue);
    }
}
