package cz.muni.fi.spnp.core.models.variables;

public class DoubleVariable extends Variable {

    private double value;

    public DoubleVariable(String name, double value) {
        super(name);

        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String getDefinition() {
        return String.format("double %s = %f;%n", this.getName(), this.getValue());
    }
}
