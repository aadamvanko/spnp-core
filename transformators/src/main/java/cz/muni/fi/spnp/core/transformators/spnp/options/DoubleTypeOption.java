package cz.muni.fi.spnp.core.transformators.spnp.options;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.OptionVisitor;

public class DoubleTypeOption extends Option {

    private final double value;

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
    public void accept(OptionVisitor optionVisitor) {
        optionVisitor.visit(this);
    }
}
