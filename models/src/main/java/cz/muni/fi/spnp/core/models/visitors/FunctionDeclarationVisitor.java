package cz.muni.fi.spnp.core.models.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;

public interface FunctionDeclarationVisitor {
    <TReturnType> void visit(Function<TReturnType> function);
}
