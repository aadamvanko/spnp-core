package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.visitors.FunctionDeclarationVisitor;

public class FunctionDeclarationVisitorImpl extends Visitor implements FunctionDeclarationVisitor {
    @Override
    public <TReturnType> void visit(Function<TReturnType> function) {
        stringBuilder.append(String.format("%s %s();%n",
                function.getReturnTypeString(),
                function.getName()));
    }
}
