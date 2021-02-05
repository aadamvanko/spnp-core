package cz.muni.fi.spnp.core.models;

import cz.muni.fi.spnp.core.models.visitors.DefineVisitor;

import java.util.Objects;

public class Define {

    private final String name;
    private Number numberValue;
    private String stringValue;

    public Define(String name, Number numberValue) {
        this(name);

        if (numberValue == null)
            throw new IllegalArgumentException("Number value is not defined");

        this.numberValue = numberValue;
    }

    public Define(String name, String stringValue) {
        this(name);

        if (stringValue == null)
            throw new IllegalArgumentException("String value is not defined");

        this.stringValue = stringValue;
    }

    private Define(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name is not defined");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Number getNumberValue() {
        return numberValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Define define = (Define) o;
        return Objects.equals(name, define.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void accept(DefineVisitor defineVisitor) {
        defineVisitor.visit(this);
    }
}
