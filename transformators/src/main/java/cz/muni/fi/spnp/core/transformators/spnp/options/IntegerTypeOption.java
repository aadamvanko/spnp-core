package cz.muni.fi.spnp.core.transformators.spnp.options;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.OptionVisitor;

public class IntegerTypeOption extends Option {

    private final int value;

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
    public void accept(OptionVisitor optionVisitor) {
        optionVisitor.visit(this);
    }
}
