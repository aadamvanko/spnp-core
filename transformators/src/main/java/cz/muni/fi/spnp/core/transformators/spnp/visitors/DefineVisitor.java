package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.code.Define;

public interface DefineVisitor {
    void visit(Define define);
}
