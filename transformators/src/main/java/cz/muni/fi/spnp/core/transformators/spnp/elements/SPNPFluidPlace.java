package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.visitors.PlaceVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.PlaceVisitorSPNP;

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
                          String initialValueExpression,
                          String boundValueExpression,
                          Set<String> breakValuesExpressions) {
        super(id, name);

        this.initialValueExpression = initialValueExpression;
        this.boundValueExpression = boundValueExpression;
        this.breakValuesExpressions = Objects.requireNonNullElseGet(breakValuesExpressions, HashSet::new);
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

    public void setBoundValueExpression(String boundValueExpression) {
        this.boundValueExpression = boundValueExpression;
    }

    public Set<String> getBreakValuesExpressions() {
        return Collections.unmodifiableSet(breakValuesExpressions);
    }

    public void addBreakValueExpression(String breakValue) {
        this.breakValuesExpressions.add(breakValue);
    }

    public void removeBreakValueExpression(String breakValue) {
        this.breakValuesExpressions.remove(breakValue);
    }

    @Override
    public void accept(PlaceVisitor placeVisitor) {
        if (placeVisitor instanceof PlaceVisitorSPNP) {
            ((PlaceVisitorSPNP) placeVisitor).visit(this);
        } else {
            super.accept(placeVisitor);
        }
    }
}
