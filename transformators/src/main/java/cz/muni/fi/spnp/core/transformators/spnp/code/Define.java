package cz.muni.fi.spnp.core.transformators.spnp.code;

import cz.muni.fi.spnp.core.transformators.spnp.visitors.DefineVisitor;

import java.util.Objects;

public class Define {

    private final String name;
    private final String expression;

    public Define(String name, String expression) {
        if (expression == null)
            throw new IllegalArgumentException("Expression is not defined");

        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public String getExpression() {
        return expression;
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
