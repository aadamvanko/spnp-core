package cz.muni.fi.spnp.core.transformators.spnp.options;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.OptionVisitor;

import java.util.Objects;

public abstract class Option {

    private final OptionKey key;

    protected Option(OptionKey key) {
        if (key == null)
            throw new IllegalArgumentException("Key is not defined.");

        this.key = key;
    }

    public OptionKey getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Option option = (Option) o;

        return key == option.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public abstract void accept(OptionVisitor optionVisitor);

}
