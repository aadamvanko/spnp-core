package cz.muni.fi.spnp.core.transformators.spnp.parameters;

public class DoubleInputParameter extends InputParameter {

    public DoubleInputParameter(String parameterName, String userPromptText) {
        super(parameterName, userPromptText);
    }

    @Override
    public String getDeclaration() {
        return String.format("double %s;%n", this.getParameterName());
    }

    @Override
    public String getDefinition() {
        return String.format("%s = finput(\"%s\");%n", this.getParameterName(),
                                                       this.getUserPromptText());
    }
}
