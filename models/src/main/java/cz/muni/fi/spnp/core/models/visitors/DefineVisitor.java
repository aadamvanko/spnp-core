package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.Define;

public interface DefineVisitor {
    void accept(Define define);
}
