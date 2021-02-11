package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.parameters.DoubleInputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.IntegerInputParameter;

public interface InputParameterVisitor {

    void visit(DoubleInputParameter doubleInputParameter);

    void visit(IntegerInputParameter integerInputParameter);
}
