package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.places.FluidPlace;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SPNPFluidPlace extends FluidPlace {

    private final Set<String> breakValuesExpressions;
    private String initialValueExpression;
    private String boundValueExpression;

    public SPNPFluidPlace(int id, String name) {
        this(id, name, "0.0", "0.0", null);
    }

    public SPNPFluidPlace(int id,
                          String name,
                          String initialValue,
                          String boundValue,
                          Set<String> breakValues) {
        super(id, name);

        this.initialValueExpression = initialValue;
        this.boundValueExpression = boundValue;
        this.breakValuesExpressions = Objects.requireNonNullElseGet(breakValues, HashSet::new);
    }

    public String getInitialValueExpression() {
        return initialValueExpression;
    }

    public void setInitialValueExpression(String initialValueExpression) {
        this.initialValueExpression = initialValueExpression;
    }

    public String getBoundValueExpression() {
        return boundValueExpression;
    }

    public void setBoundValue(String boundValueExpression) {
        this.boundValueExpression = boundValueExpression;
    }

    public Set<String> getBreakValuesExpressions() {
        return Collections.unmodifiableSet(breakValuesExpressions);
    }

    public void addBreakValue(String breakValue) {
        this.breakValuesExpressions.add(breakValue);
    }

    public void removeBreakValue(String breakValue) {
        this.breakValuesExpressions.remove(breakValue);
    }

}
