package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.variables.DoubleVariable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.IntegerVariable;

public interface VariableVisitor {

    void visit(DoubleVariable doubleVariable);

    void visit(IntegerVariable integerVariable);

}
