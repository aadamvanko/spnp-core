package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.variables.DoubleVariable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.IntegerVariable;

public class VariableVisitorImpl extends Visitor implements VariableVisitor {
    @Override
    public void visit(DoubleVariable doubleVariable) {
        stringBuilder.append(String.format("double %s = %s;%n", doubleVariable.getName(), formatDouble(doubleVariable.getValue())));
    }

    @Override
    public void visit(IntegerVariable integerVariable) {
        stringBuilder.append(String.format("int %s = %d;%n", integerVariable.getName(), integerVariable.getValue()));
    }
}
