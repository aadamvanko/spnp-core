package cz.muni.fi.spnp.core.models.options;

public class DoubleTypeOption extends Option {

    private double value;

    public DoubleTypeOption(OptionKey key, double value) {
        super(key);

        if (!key.toString().startsWith("FOP"))
            throw new IllegalArgumentException("Invalid Option key for double type Option.");

        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String getDefinition() {
        return String.format("fopt(%s, %f);%n", this.getKey().toString(), this.getValue());
    }
}
