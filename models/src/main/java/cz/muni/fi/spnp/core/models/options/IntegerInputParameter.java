package cz.muni.fi.spnp.core.models.options;

public class IntegerInputParameter extends InputParameter {

    public IntegerInputParameter(String parameterName, String userPromptText) {
        super(parameterName, userPromptText);
    }

    @Override
    public String getDeclaration() {
        return String.format("int %s;%n", this.getParameterName());
    }

    @Override
    public String getDefinition() {
        return String.format("%s = input(\"%s\");%n", this.getParameterName(),
                                                      this.getUserPromptText());
    }
}
