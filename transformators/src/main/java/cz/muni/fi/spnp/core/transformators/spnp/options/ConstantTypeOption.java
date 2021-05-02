package cz.muni.fi.spnp.core.transformators.spnp.options;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.OptionVisitor;

public class ConstantTypeOption extends Option {

    private final ConstantValue value;

    public ConstantTypeOption(OptionKey key, ConstantValue value) {
        super(key);

        if (value == null)
            throw new IllegalArgumentException("Value is not defined.");

        this.value = value;
    }

    public ConstantValue getValue() {
        return value;
    }

    @Override
    public void accept(OptionVisitor optionVisitor) {
        optionVisitor.visit(this);
    }
}
