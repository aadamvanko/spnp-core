package cz.muni.fi.spnp.core.transformators.spnp.parameters;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.InputParameterVisitor;

import java.util.Objects;

public abstract class InputParameter implements Comparable<InputParameter> {

    private final String parameterName;
    private final String userPromptText;

    protected InputParameter(String parameterName, String userPromptText) {
        if (parameterName == null)
            throw new IllegalArgumentException("Parameter name is not defined.");
        if (userPromptText == null)
            throw new IllegalArgumentException("User prompt text is not defined.");

        this.parameterName = parameterName;
        this.userPromptText = userPromptText;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getUserPromptText() {
        return userPromptText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        InputParameter that = (InputParameter) o;

        return Objects.equals(parameterName, that.parameterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterName);
    }

    @Override
    public int compareTo(InputParameter other) {
        return parameterName.compareTo(other.parameterName);
    }

    public abstract void accept(InputParameterVisitor inputParameterVisitor);
}
