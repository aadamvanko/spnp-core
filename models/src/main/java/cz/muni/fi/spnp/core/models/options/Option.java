package cz.muni.fi.spnp.core.models.options;

import java.util.Objects;

public abstract class Option {

    private OptionKey key;

    protected Option(OptionKey key) {
        if (key == null)
            throw new IllegalArgumentException("Key is not defined.");

        this.key = key;
    }

    public abstract String getDefinition();

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
}
