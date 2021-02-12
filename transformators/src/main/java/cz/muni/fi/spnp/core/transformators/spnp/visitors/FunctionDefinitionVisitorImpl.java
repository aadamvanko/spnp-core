package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.visitors.FunctionDefinitionVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.utility.Utils;

public class FunctionDefinitionVisitorImpl extends Visitor implements FunctionDefinitionVisitor {
    @Override
    public <TReturnType> void visit(Function<TReturnType> function) {
        stringBuilder.append(String.format("%s %s() {%n%s}%n",
                function.getReturnTypeString(),
                function.getName(),
                Utils.tabify(function.getBody())));
    }
}
