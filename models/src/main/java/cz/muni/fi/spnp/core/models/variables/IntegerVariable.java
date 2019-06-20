package cz.muni.fi.spnp.core.models.variables;

public class IntegerVariable extends Variable {

    private int value;

    protected IntegerVariable(String name, VariableType type, int value) {
        super(name, type);

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getDefinition() {
        return String.format("int %s = %d;%n", this.getName(), this.getValue());
    }
}
