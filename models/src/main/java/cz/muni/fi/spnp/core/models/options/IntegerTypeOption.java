package cz.muni.fi.spnp.core.models.options;

public class IntegerTypeOption extends Option {

    private int value;

    public IntegerTypeOption(OptionKey key, int value) {
        super(key);

        if (!key.toString().startsWith("IOP"))
            throw new IllegalArgumentException("Invalid Option key for integer type Option.");

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getDefinition() {
        return String.format("iopt(%s, %d);%n", this.getKey().toString(), this.getValue());
    }
}
