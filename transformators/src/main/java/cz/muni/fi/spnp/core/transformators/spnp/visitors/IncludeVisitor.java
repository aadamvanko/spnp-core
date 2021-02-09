package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.code.Include;

public interface IncludeVisitor {
    void visit(Include include);
}
