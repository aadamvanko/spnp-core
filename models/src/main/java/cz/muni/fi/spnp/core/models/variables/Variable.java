package cz.muni.fi.spnp.core.models.variables;

import java.util.Objects;

public abstract class Variable {

    private String name;
    private VariableType type;

    protected Variable(String name, VariableType type) {
        if (name == null)
            throw new IllegalArgumentException("Name is not defined.");
        if (type == null)
            throw new IllegalArgumentException("Variable type is not defined.");

        this.name = name;
        this.type = type;
    }

    public abstract String getDefinition();

    public String getName() {
        return name;
    }

    public VariableType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Variable variable = (Variable) o;

        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
