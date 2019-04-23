package cz.muni.fi.spnp.core.models;

import java.util.Objects;

public class Include {

    private String path;

    public Include(String path) {
        if (path == null)
            throw new IllegalArgumentException("Path is not defined.");

        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public String getDefinition() {
        return String.format("#include %s%n", this.getPath());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Include include = (Include) o;

        return Objects.equals(path, include.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
