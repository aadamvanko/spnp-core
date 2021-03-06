package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.visitors.FunctionDeclarationVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;

public class FunctionDeclarationVisitorImpl extends Visitor implements FunctionDeclarationVisitor {
    @Override
    public void visit(Function function) {
        stringBuilder.append(String.format("%s%n",
                function.getName()));
    }
    
    public <TReturnType> void visit(FunctionSPNP<TReturnType> function) {
        stringBuilder.append(String.format("%s %s();%n",
                function.getReturnTypeString(),
                function.getName()));
    }
}
