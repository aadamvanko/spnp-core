package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.Include;
import cz.muni.fi.spnp.core.models.visitors.IncludeVisitor;

public class IncludeVisitorImpl extends Visitor implements IncludeVisitor {
    @Override
    public void visit(Include include) {
        stringBuilder.append(String.format("#include %s%n", include.getPath()));
    }
}
