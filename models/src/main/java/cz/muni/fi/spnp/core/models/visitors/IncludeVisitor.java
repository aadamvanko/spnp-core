package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.Include;

public interface IncludeVisitor {
    void visit(Include include);
}
