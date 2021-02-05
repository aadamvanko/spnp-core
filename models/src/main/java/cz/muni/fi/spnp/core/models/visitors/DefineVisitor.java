package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.Define;

public interface DefineVisitor {
    void visit(Define define);
}
