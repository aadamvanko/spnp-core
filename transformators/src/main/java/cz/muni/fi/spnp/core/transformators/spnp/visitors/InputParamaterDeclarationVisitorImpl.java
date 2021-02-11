package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.parameters.DoubleInputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.IntegerInputParameter;

public class InputParamaterDeclarationVisitorImpl extends Visitor implements InputParameterVisitor {
    @Override
    public void visit(DoubleInputParameter doubleInputParameter) {
        stringBuilder.append(String.format("double %s;%n", doubleInputParameter.getParameterName()));
    }

    @Override
    public void visit(IntegerInputParameter integerInputParameter) {
        stringBuilder.append(String.format("int %s;%n", integerInputParameter.getParameterName()));
    }
}
