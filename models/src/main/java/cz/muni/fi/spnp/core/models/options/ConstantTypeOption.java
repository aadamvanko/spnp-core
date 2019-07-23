package cz.muni.fi.spnp.core.models.options;

public class ConstantTypeOption extends Option {

    private ConstantValue value;

    public ConstantTypeOption(OptionKey key, ConstantValue value) {
        super(key);

        if (!key.toString().startsWith("FOP"))
            throw new IllegalArgumentException("Invalid Option key for constant type Option.");
        if (value == null)
            throw new IllegalArgumentException("Value is not defined.");

        this.value = value;
    }

    public ConstantValue getValue() {
        return value;
    }

    @Override
    public String getDefinition() {
        return String.format("iopt(%s, %s);%n", this.getKey().toString(), this.getValue().toString());
    }
}
