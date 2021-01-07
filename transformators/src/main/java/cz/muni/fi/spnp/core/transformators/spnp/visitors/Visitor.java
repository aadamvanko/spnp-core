package cz.muni.fi.spnp.core.transformators.spnp.visitors;

public class Visitor {

    protected StringBuilder stringBuilder = new StringBuilder();

    public String getResult() {
        return stringBuilder.toString();
    }

}
