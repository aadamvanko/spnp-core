package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.variables.DoubleVariable;
import cz.muni.fi.spnp.core.models.variables.IntegerVariable;

public interface VariableVisitor {

    void visit(DoubleVariable doubleVariable);

    void visit(IntegerVariable integerVariable);

}
