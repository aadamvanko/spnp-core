package cz.muni.fi.spnp.core.transformators.spnp.parameters;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.InputParameterVisitor;

public class DoubleInputParameter extends InputParameter {

    public DoubleInputParameter(String parameterName, String userPromptText) {
        super(parameterName, userPromptText);
    }

    @Override
    public void accept(InputParameterVisitor inputParameterVisitor) {
        inputParameterVisitor.visit(this);
    }
}
