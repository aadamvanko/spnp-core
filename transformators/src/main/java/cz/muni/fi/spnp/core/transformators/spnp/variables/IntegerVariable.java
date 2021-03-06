package cz.muni.fi.spnp.core.transformators.spnp.variables;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.VariableVisitor;

public class IntegerVariable extends Variable {

    private final int value;

    public IntegerVariable(String name, VariableType type, int value) {
        super(name, type);

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(VariableVisitor variableVisitor) {
        variableVisitor.visit(this);
    }
}
