package cz.muni.fi.spnp.core.models.variables;

import cz.muni.fi.spnp.core.models.visitors.VariableVisitor;

public class DoubleVariable extends Variable {

    private final double value;

    public DoubleVariable(String name, VariableType type, double value) {
        super(name, type);

        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(VariableVisitor variableVisitor) {
        variableVisitor.visit(this);
    }
}
