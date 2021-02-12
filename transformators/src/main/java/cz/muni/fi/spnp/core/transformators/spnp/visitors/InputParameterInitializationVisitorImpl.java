package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.parameters.DoubleInputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.IntegerInputParameter;

public class InputParameterInitializationVisitorImpl extends Visitor implements InputParameterVisitor {
    @Override
    public void visit(DoubleInputParameter doubleInputParameter) {
        stringBuilder.append(String.format("%s = finput(\"%s\");%n",
                doubleInputParameter.getParameterName(),
                doubleInputParameter.getUserPromptText()));
    }

    @Override
    public void visit(IntegerInputParameter integerInputParameter) {
        stringBuilder.append(String.format("%s = input(\"%s\");%n",
                integerInputParameter.getParameterName(),
                integerInputParameter.getUserPromptText()));
    }
}
