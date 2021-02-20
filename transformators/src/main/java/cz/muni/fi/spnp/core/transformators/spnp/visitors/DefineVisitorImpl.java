package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.code.Define;

public class DefineVisitorImpl extends Visitor implements DefineVisitor {
    @Override
    public void visit(Define define) {
        if (define.getExpression() != null) {
            stringBuilder.append(String.format("#define %s %s%n", define.getName(), define.getExpression()));
        }
    }
}
