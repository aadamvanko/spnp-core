package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;

public interface FunctionDeclarationVisitor {
    void visit(Function function);
}
